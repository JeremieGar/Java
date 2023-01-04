//jeremiegarzon_labibbennasr

import answer.Answer;
import answer.Tuple;
import java.util.*;
import java.util.Scanner;

public class Laby {

    
    private String lab;
    private int dim;

    public Laby(String lab, int dim){
    	this.lab = lab;
      	this.dim = dim;
    }
    
    public String getLab(){
    	return lab;
    }
    
    public int getDim(){
    	return dim;
    }	

    //creerTable() transfère le string de Laby en un tableau 2d qui garde les valeurs du string individuellement
	public static String[][] creerTable(Laby l){
		String lab = l.getLab();
        int dim = l.getDim();
		String[][] tabLaby= new String[dim][dim]; 
      	for(int y=0; y<dim; y++)
      		for(int x=0; x<dim; x++)
        		tabLaby[x][y] = lab.substring(x + (dim*y), x + (dim*y)+1);
          
      	return tabLaby;
    }
    
    //Les quatres fonctions "check" regardent une des quatres directions du tableau et évalue si cette direction est un mur,
    //ou si c'est la direction d'où il est venu en regardant la pile.
    public static boolean checkLeft(int x, int y, String[][] tabLaby, Tuple lastPosition){
        if((tabLaby[x-1][y].equals("0")||tabLaby[x-1][y].equals("F")) && lastPosition.x != x-1)
        	return true;
        else
        	return false;       	
    }
    
    public static boolean checkRight(int x, int y, String[][] tabLaby, Tuple lastPosition){
        if((tabLaby[x+1][y].equals("0")||tabLaby[x+1][y].equals("F")) && lastPosition.x != x+1)
        	return true;
        else
        	return false;
    }
      
    public static boolean checkUp(int x, int y, String[][] tabLaby, Tuple lastPosition){
        if((tabLaby[x][y-1].equals("0") || tabLaby[x][y-1].equals("F")) && lastPosition.y != y-1)
        	return true;
        else
        	return false;       	
    }
      
    public static boolean checkDown(int x, int y, String[][] tabLaby, Tuple lastPosition){
        if((tabLaby[x][y+1].equals("0") || tabLaby[x][y+1].equals("F")) && lastPosition.y != y+1)
        	return true;
        else
        	return false;       	
    }
  
      
    //Cherche si la position actuelle est une impasse ou si un chemin inexploré existe encore, et retourne un true/false  
    public static boolean cheminExiste(int x, int y, String[][] tabLaby, Stack<Tuple> pile){
		
		boolean chemin = false;
        if (checkRight(x, y, tabLaby, pile.peek())) 
			chemin = true;

        if (checkLeft(x, y, tabLaby, pile.peek())) 
        	chemin = true;

        if (checkDown(x, y, tabLaby, pile.peek()))
       	 	chemin = true;

        if (checkUp(x, y, tabLaby, pile.peek()))
        	chemin = true;
      	
      	return chemin; 
    } 
    
    //verifie si on est sur le charactere de fin "F"
    public static boolean checkFin(int x, int y, String[][] tabLaby){
        if(tabLaby[x][y].equals("F"))
        	return true;
        else
        	return false;   
        	
    }
    
    //emmure() remplace un ou plusieurs elements "0" d'un chemin qui fini en impasse. Il va rebrousser chemin jusqu'a
    //ce qu'il se retrouve à la derniere intersection visitée. Si aucun bon chemin n'est disponible, il emmurera 
    //tout le labyrinthe jusqu'à D. On saura donc qu'aucun chemin n'est possible.
    public static void emmurer(int x, int y, String[][] tabLaby, Stack<Tuple> pile) {

       	if (!pile.isEmpty() && !checkFin(x,y,tabLaby)){ 
										
        	if(tabLaby[x][y].equals("0"))
        		tabLaby[x][y]="#";
        	//Cette ligne de code aide a voir ce qu'emmure notre programme a chaque étape
        	//Elle a été mise en commentaire pour un affichage plus net.  
        	//System.out.println("On emmure le tuple de coordonnées suivant: "+ x + "," + y + "");
        	Tuple temp = pile.peek();
        	      
        	if(!cheminExiste(temp.x, temp.y, tabLaby, pile))        		
        		emmurer(temp.x, temp.y, tabLaby, pile);
      	}
      	
    }
      
    //Les deux fonctions suivantes utilisent une bouble double qui parcourt le tableau et retourne les coordonées du 
    //point de début D et de fin F 
    public static Tuple trouveD(String[][] tab, int dim, Stack<Tuple> pile){      	 
      	     
      	for(int y=0; y<dim; y++)
        	for(int x=0; x<dim; x++)
          		if(tab[x][y].equals("D")){
          			Tuple start= new Tuple(x,y);
            		pile.push(start);
          		}
          	
      	return pile.peek();
    }
     	 
    public static Tuple trouveF(String[][] tab, int dim){
      
      	int tempX=0, tempY=0;
      	     
      	for(int y=0; y<dim; y++)
        	for(int x=0; x<dim; x++)
          		if(tab[x][y].equals("F")){
          			tempX=x;
          			tempY=y;    		
     	 		}
     	Tuple F= new Tuple(tempX, tempY);
     	return F;
    }
     	
    //En utilisant plusieurs recursions, toutes autres fonctions sont mises en utilité ici pour pouvoir naviguer de 
    //point en point, chercher la fin (valeur F) et finir la recursion. Si tout le labyrinthe est emmuré
    //avant que le navigateur puisse trouver F, il est assumé qu'une solution pour ce labyrinthe n'existe pas.    
    public static boolean navigate(String[][] tabLaby, Tuple actuel, Stack<Tuple> pile, boolean possible){
       		         
        if(checkFin(actuel.x, actuel.y, tabLaby)){        	
          	possible=true;
          	pile.push(actuel);
            return true;            
        }
        
        //Toutes les directions incluent une ligne qui affiche la direction prise à l'étape donnée
        //Cela aide a voir le chemin pris par le navigateur. Il ont été mis en commentaire
        //pour un affichage plus net.  
       	if(cheminExiste(actuel.x, actuel.y, tabLaby, pile)){

       		if(checkRight(actuel.x, actuel.y, tabLaby, pile.peek())){
            	//System.out.print("r"); 
            	pile.push(actuel);
          		navigate(tabLaby, new Tuple (actuel.x+1, actuel.y), pile, possible);
            }
          	
          	else if(checkDown(actuel.x, actuel.y, tabLaby, pile.peek())){
            	//System.out.print("d");
            	pile.push(actuel);
          		navigate(tabLaby, new Tuple (actuel.x, actuel.y+1), pile, possible);
            }
          	
            else if(checkLeft(actuel.x, actuel.y, tabLaby, pile.peek())){
            	//System.out.print("l");
            	pile.push(actuel);
          		navigate(tabLaby, new Tuple(actuel.x-1, actuel.y), pile, possible);
            }

          	else if(checkUp(actuel.x, actuel.y, tabLaby, pile.peek())){
            	//System.out.print("u");
            	pile.push(actuel);
          		navigate(tabLaby, new Tuple (actuel.x, actuel.y-1), pile, possible);
            }
        }
     	
     	else if(pile.isEmpty())
     		possible= false;

        else if(possible==false){

         	emmurer(actuel.x, actuel.y, tabLaby, pile);
         	actuel=pile.peek();
         	if(cheminExiste(actuel.x, actuel.y, tabLaby, pile))         			
         		navigate(tabLaby, pile.pop(), pile, possible);         		         				         	
        }
          
        return possible;
    }
    
    //Quand la pile est completement rempli avec les bonnes coordonnees, on transforme cette pile
    //en tableau, qu'on pourra plus tard ajouter à rebours dans la liste ans.path   
    public static Tuple[] pileToTab(Stack <Tuple> pile){
       
       	Tuple[] tab=new Tuple[pile.size()];
        for(int i=pile.size()-1; i>-1; i--)
        	tab[i] = pile.pop();
          
        return tab;
    }
       
    //On utilise un scanneur pour importer le Laby desiré et ses dimensions. L'objet est ensuite créé et transformer en tableau.
    //Ce tableau est navigué par notre fonction navigate(). Quand ce dernier fini, il aurait rempli notre pile avec toutes les 
    //valeurs traversées sur le chemin vers F si la dernière valeurs de la pile représente les coordonnées de F on peut considérer
    //qu'un chemin a été trouvé, il est affiché à l'utilisateur. Dans le cas ou un chemin n'existe pas, un message à cet effet est affiché. 
    public static Answer solve(){
        	
        Answer ans = new Answer(false);
		boolean possible=false;

		Scanner s=new Scanner(System.in);
		System.out.print("String: ");			
		String stringLaby = s.nextLine();
		System.out.print("dimension: ");						
		int dimLaby=s.nextInt();
			
		Laby l= new Laby(stringLaby, dimLaby);
        String[][] tabLaby = creerTable(l);
        Tuple F=trouveF(tabLaby, dimLaby);
        Stack<Tuple> pile = new Stack<Tuple>();
        Tuple debut = trouveD(tabLaby, l.getDim(), pile);
        Tuple[] tabTemp;

        navigate(tabLaby, debut, pile, possible);
         
        if(pile.peek().compareTo(F)==0)
          	ans.b=true;
        else
          	ans.b=false;
          		       
          
        if(!ans.b)
          	System.out.println("Aucune solution est possible pour ce labyrinthe");
        else{
          	tabTemp = pileToTab(pile);
            for(int i=0; i<tabTemp.length; i++){
            	System.out.println(tabTemp[i].toString());
            	ans.path.add(tabTemp[i]);
          	}
        }

        return ans;
    }
           

    public static void main(String[] args){
      	
      	solve();
    }
}
      