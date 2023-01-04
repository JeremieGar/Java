// -----------------------------------------------------
// Assignment 1
// Question: 1
// Written by: Jeremie Garzon 40062316
// -----------------------------------------------------

import java.util.Scanner;
import java.lang.Math;
import java.util.Arrays;

public class Main {

    /**
     * <p> Zilu Mou #40176355</p>
     * <p> Jeremie Garzon #40062316 </p>
     * <p> Assignment #1</p>
     * <p> Due February 8, 2021</p>
     *
     *
     *
     *
     * This program's purpose is to create a simulation of the snake and ladder game.
     * It consists of two to four human users,and computer cannot be a player.
     * At the beginning, the number of players are determined first.
     * Once the number of players is set, the order will be decided by dice roll.
     * If two or more players have duplicates, the dice would be rerolled for those players only until all numbers are unique.
     * Once the order of players is finalized, the game officially starts.
     * Players will play based on the predetermined order,and all players start at position zero.
     * Each player rolls the dice, and they move to the corresponding position based on its value.
     * If players land on a position with the bottom of a ladder, they will move forward to the position with the top of the ladder.
     * If players land on a position with the head of a snake, they will drop backward to the position with the snake's tail.
     * The game is finished when one player lands on the position 100 exactly after his/her move.
     * If a player's dice value takes him beyond 100 mathematically, then he has to go backwards at position 100 to complete his move.
     * If more than one player arrives at 100 in the same round, the winner will be the first player who reaches 100.
     *
     * @author Jeremie Garzon
     * @author Zilu Mou
     */

    public static void main(String[] args) {

        /**
         * This class allows users to determine the number of players,
         * create names for each players, and start the game.
         *
         * @param numPlayers number of players to play the game
         * @param game contains numPlayers and and their respective names
         * @param names array for the names of players
         *
         */

        System.out.println("Welcome to Snakes and Ladders created by Zilu Mou and Jeremie Garzon");

        Scanner s = new Scanner(System.in);
        int numPlayers;
        int attempt=0;
        boolean correct=false;
        System.out.println("Please enter the number of players- Number has to be between 2 and 4");

        do {
            numPlayers= s.nextInt();
            if(attempt==3) {
                System.out.println("Attempts exhausted! Program will terminate!");
                break;
            }
            else if(numPlayers >=2 && numPlayers <=4) {
                correct=true;
                System.out.println("Successful! "+numPlayers+" players will participate!");

                System.out.println("What are their names? Enter them below!");
                String[] names = new String[numPlayers+1];
                for (int i = 0; i < numPlayers+1; i++) {
                    names[i] = s.nextLine();
                }

                LadderAndSnake game = new LadderAndSnake(numPlayers, names);
                s.nextLine();
                play(game.getBoard(),game.getPlayerOrder());

            }
            else {
                System.out.println("Try a number between 2 and 4!");
                attempt++;
            }
        }while(!correct);

    }


    /**
     * This method allows the program to return a random dice value from 1 to 6
     * Uses math random*5 to find a random number between 0 and 5, adding 1 makes the range 1 to 6
     * transforming into an int removes the decimals and gives us a solid int value to use.
     * We subtract 0.01 to exclude 1 from the Math.Random
     *
     * @return int the value of the dice roll
     */
    public static int flipDice(){
        return (int)(Math.random()*6) + 1;
    }

    /**
     * This method checks whether there is a winner.
     * If a player is at position 100, the player wins even if multiple people reach position 100 in the same round.
     * Player with an index closer to 0 gets to 100 quicker, hence the winner.
     * @param playerArr array of players
     * @return int player who wins
     */
    public static int checkWin(Player[] playerArr){
        for (int i = 0; i < playerArr.length; i++) {
            if(playerArr[i].getPosition() == 100)
                return i;
        }
        return -1;
    }

    /**
     * This method is dedicated for the core playing mechanism of the game
     * At each round, a new position is determined for each player based on the dice roll.
     * Declared posPosition for the new position
     * Check tile for any potential ladder or snake, set posPosition as current if board[posPosition]==0
     * If player encounters ladder/snake, a new position is assigned.
     * After new position is determined, player info and position are printed.
     *
     * @param board the positions on the board
     * @param playerOrder the order of dice roll at each round
     */
    public static void play(int[] board, Player[] playerOrder){

        int turn = 1;
        do {
            System.out.println("Square  Turn " + turn + ":");
            for (int i = 0; i < playerOrder.length; i++) {


                int roll = flipDice();
                int posPosition = playerOrder[i].getPosition() + roll; //possible Position before snakes and ladders
                if(posPosition>100) //if posPosition exceeds 100, the position goes backwards
                    posPosition = 100 - (posPosition%100);

                System.out.print(String.format("  %-6s" , playerOrder[i].getPosition()));
                //if the tile has no snake or ladder, set the new position
                if (board[posPosition] == 0) {
                    playerOrder[i].setPosition(posPosition);
                    System.out.println(playerOrder[i].getPlayerName() + " rolls a " + roll
                                        + " and advances to square " + posPosition);
                }
                //if the tile has a positive number in the board array, it is a ladder
                else if (board[posPosition] > 0) {
                    playerOrder[i].setPosition(posPosition + board[posPosition]);
                    System.out.println(playerOrder[i].getPlayerName() + " rolls a " + roll
                                        + " and reaches a ladder at square " + posPosition + "! Lucky! Climb up to square "
                                        + (posPosition + board[posPosition]));
                }
                //if the tile has a negative number in the board array, it is a snake
                else if (board[posPosition] < 0) {
                    playerOrder[i].setPosition(posPosition + board[posPosition]);
                    System.out.println(playerOrder[i].getPlayerName() + " rolls a " + roll
                                        + " and slips on a snake at square " + posPosition + "! Unlucky! Slide down to square "
                                        + (posPosition + board[posPosition]));
                }
            }
            System.out.println();
            turn++;

        }while(checkWin(playerOrder) == -1);
        System.out.println("==========================================================================================");
        System.out.println("| | | | | | | | | | | | "+ playerOrder[checkWin(playerOrder)].getPlayerName() + " has won the game! Congratulations! | | | | | | | | | | | |");
        System.out.println("==========================================================================================");
    }

} // class Main

class LadderAndSnake {

    private final int[] board = {0,37,0,0,10,0,0,0,0,22,0,
                                 0,0,0,0,0,-10,0,0,0,0,
                                 21,0,0,0,0,0,0,56,0,0,
                                 0,0,0,0,0,8,0,0,0,0,
                                 0,0,0,0,0,0,0,-18,0,0,
                                 16,0,0,0,0,0,0,0,0,0,
                                 0,0,-4,0,0,0,0,0,0,0,
                                 20,0,0,0,0,0,0,0,-60,20,
                                 0,0,0,0,0,0,0,0,0,0,
                                 0,0,-25,0,-71,0,-21,-20,0,0};
    private Player[] playerOrder;

    /**
     *
     * This constructor builds the board and uses chooseOrder() to set an order of players, most actions will happen
     * within the two arrays of a LadderAndSnake object.
     * Each value of the board array represents a movement.Positive values are ladders, negatives are snakes
     * and zeros are blank tiles.
     * @param num number of positions
     * @param names names of players
     */
    public LadderAndSnake(int num, String[] names){
        this.playerOrder = orderFinder(num, names);
    }

    /**
     * This method returns the board array
     * @return the current state of board
     */
    public int[] getBoard(){
        return this.board;
    }

    /**
     * @return the current order of player
     */
    public Player[] getPlayerOrder(){
        return this.playerOrder;
    }

    /**
     * This method simulates a dice flip
     * @return int random value between 1 and 6
     */
    public int flipDice(){
        return (int)(Math.random()*6) + 1;
    }

    /**
     *
     * This method allows number of players to be determined.
     * The order of play is decided through dice roll, and comparing the values obtained.
     * If duplicate values are found, then the duplicate players need to reroll until all have unique values.
     * Each time a player gets a dice value, his info is printed with the name and the dice value.
     * @param totalPlayers number of players
     * @param names names of players
     * @return playerOrder the final playing order of the players involved
     */
    public Player[] orderFinder(int totalPlayers, String[] names) {

        Player[] playerOrder = new Player[totalPlayers];//declaring array of players
        int minPlayer = 2; //minimum number if players should be 2

        System.out.println("\nNow deciding the player order:");
        System.out.println("================================");
        for(int i = minPlayer; i <= totalPlayers + 1; i++) //assigning random dice value to each player
        {
            //generating random number between 1 to 6
            int tempDice = flipDice();
            playerOrder[i-minPlayer] = new Player(i-minPlayer+1,tempDice, names[i-minPlayer+1]);
            System.out.println(playerOrder[i-minPlayer].getPlayerName() + " got a dice value of " + tempDice + "\n");
        }

        Arrays.sort(playerOrder);

        int levelIncrement = 0;
        if(duplicateFinder(playerOrder))//if any duplicate is found
        {
            do {
                levelIncrement++;
                playerOrder = ordering(playerOrder, levelIncrement);

            }while(duplicateFinder(playerOrder));
        }

        System.out.println("The player order has been set:");
        for (int i = 0; i < playerOrder.length; i++) {
            System.out.print("| " + playerOrder[i].getPlayerName() + " ");
        }
        System.out.println("|\n\nTime to play! \nPress ENTER when you're ready to go!\n");
        return playerOrder;
    }

    /**
     * This method checks for any duplicate values after a round.
     * @param playerOrder array for playing order.
     * @return true if duplicate value is found, false if no duplicate value is found.
     */
    public  boolean duplicateFinder(Player[] playerOrder) {

        int len = playerOrder.length; //length of array

        for (int i = 0; i < len; i++) {
            for (int j = i + 1 ; j < len; j++) {

                if (playerOrder[i].getDiceRoll() == playerOrder[j].getDiceRoll() &&
                        playerOrder[i].getSortLevel() == playerOrder[j].getSortLevel() ) {
                    return true; //returning true when any duplicate dice value is found
                }
            }
        }
        return false;//when no duplicate is found return false
    }

    /**
     * This method reorders players who have duplicate values after a round.
     * After new values are assigned, the order is then rearranged.
     * @param playerOrder order of play.
     * @param first index of the first duplicate player.
     * @param length to check whether there is more than one duplicate.
     * @param levelIncrement int to count new level.
     * @return playerOrder order of play
     */
    public Player[] reorder(Player playerOrder[], int first, int length, int levelIncrement) {

        System.out.print("Breaking a tie between ");
        for (int i = first; i < length+first; i++) {
            System.out.print(playerOrder[i].getPlayerName() + " ");
            if(i<(length+first-1))
                System.out.print("and ");
        }
        System.out.println();

        for (int i = first; i < length+first; i++) {
            playerOrder[i].setDiceRoll(flipDice());
            playerOrder[i].setSortLevel(levelIncrement);
            System.out.println(playerOrder[i].getPlayerName() + " re-rolls for " + playerOrder[i].getDiceRoll());
        }
        System.out.println();
        Arrays.sort(playerOrder, first, first+length);
        return playerOrder;
    }

    /**
     * This method reorders player's turn in case of duplicates
     * @param playerOrder order of play.
     * @param levelIncrement count new positions.
     * @return playerOrder order of play
     */
    public Player[] ordering(Player playerOrder[], int levelIncrement) {

        int len = playerOrder.length; //length of array
        int dupStart = 0, dupCount = 1;

        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                if(playerOrder[i].getDiceRoll() == playerOrder[j].getDiceRoll()
                    && playerOrder[i].getSortLevel() == playerOrder[j].getSortLevel() ){
                    dupStart = i;
                    dupCount++;
                }
            }
            if(dupCount>1)
                break;
        }

        playerOrder = reorder(playerOrder, dupStart, dupCount, levelIncrement);

        return playerOrder;
    }
} //class LadderAndSnake

/**
 * This class contains all methods for players modification.
 * It allows two players to be compared.
 * All mutating methods and accessor methods are within this class.
 * @author Zilu Mou
 * @author Jeremie Garzon
 */
class Player implements Comparable<Player>{
    private int playerNum;
    private String playerName;
    private int currentPos = 0;
    private int diceRoll;
    private int sortLevel = 0;

    /**
     * A constructor for Player's info
     * @param number order value.
     * @param dice dice value.
     * @param name name of player.
     */
    public Player(int number, int dice, String name) {
        this.playerNum = number;
        this.diceRoll = dice;
        this.playerName = name;
    }

    public Player(Player p){
        this.playerNum = p.playerNum;
        this.diceRoll = p.diceRoll;
    }

    /**
     * All accessor methods
     * @return required information
     */
    public int getPlayerNum() { return this.playerNum; }
    public String getPlayerName() { return this.playerName; }
    public int getPosition() { return this.currentPos; }
    public int getDiceRoll() { return this.diceRoll; }
    public int getSortLevel() { return this.sortLevel;}

    public void setPlayerName(String newName) {
        this.playerName = newName;
    }

    public void setPosition(int newPosition) {
        this.currentPos = newPosition;
    }

    public void setDiceRoll(int newDiceRoll) {
        this.diceRoll = newDiceRoll;
    }

    public void setSortLevel(int newLvl){
        this.sortLevel = newLvl;
    }

    public int compareTo(Player comparePlayer){
        return comparePlayer.getDiceRoll() - this.getDiceRoll();
    }
} //class Player
