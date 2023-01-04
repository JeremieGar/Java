//ChainHashMaps due to buckets being useful for larger amounts of doubles, higher chance of collisions vs. 
//ProbeHashMaps which give each double an individual index more easily referenced, but longer execution in case of collisions
//
//Polynomial Hashing Function - useful for changing word doubles into key integers

/**

 IFT2810 - A20
 Classe TextDict.java

 Jeremie Garzon 20101031
 Labib Ben Nasr 20167268

 Cette classe représente un texte sous la forme
 d'un dictionnaire fréquences de doublets de mots
 permettant de le comparer avec d'autres avec la
 méthode distBetweenDs (fournie plus bas)

 */
//On importe les outils necessaires pour faire marcher les Maps, les Listes et le Scanner
import java.util.*;
import java.io.*;
import java.util.Scanner;

// Implementation of ADT WordDoubletStats using HashMap

class TextDict {
    // Represent a text in a dictionary of word-doublet-frequencies

    // Class variable for punctuation marks (to be ignored)
    private static Set<Character> PUNC = new HashSet<Character>();

    static {
		PUNC.add( '!' );
		PUNC.add( '"' );
		PUNC.add( '\'' );
		PUNC.add( ')' );
		PUNC.add( '(' );
		PUNC.add( ',' );
		PUNC.add( '.' );
		PUNC.add( ';' );
		PUNC.add( ':' );
		PUNC.add( '?' );
		PUNC.add( '-' );
		PUNC.add( ',' );
		PUNC.add( '_' );
    }

	private String auteur;
	private Map<String, Integer> WDF;

	//Ce constructeur utilise treatText afin de directement creer un dictionnaire
	//quand un filename est appele
	public TextDict(String filename){
		this.auteur = filename;
		this.WDF = treatText(filename);
	}

	//constructeur vide
	public TextDict(){
		this.auteur = "";
		this.WDF = new HashMap<>();
	}

	//deux fonctions accesseurs dont dict() qui est necessaire pour distBetweenDs
	public String getAuteur(){
		return auteur;
	}
	public Map<String, Integer> dict(){
		return WDF;
	}

    // treatText read a text from a file
    // Return a dictionnary of its frequencies of word doublets
    //   the word doublets are stored in a single String which
    //   serves as the key, and the frequencies are Integer values.
    private Map<String,Integer> treatText( String filename ) {
	// Represent text with doublet frequencies.
		String prevWord = ""; // last word of the previous line of text
		Map<String,Integer> WDF = new HashMap<>();
		try {
		    File myText = new File( filename );
		    Scanner myReader = new Scanner( myText );
		    String line;
		    ArrayList<String> words = new ArrayList<>();
		    String pair;
		    while( myReader.hasNextLine()) {
				line = myReader.nextLine();
				line = line.trim();
				if( line == "" ) continue;

				words = treatLine( line );

				//On construit et rempli un Hashmap simple avec les doublets du ArrayList words
				//si le Hashmap contient deja un doublet, on incremente sa valeur
				for(int i=0; i<words.size()-1 ; i++) {
					pair = words.get(i) + words.get(i + 1);
					if (!WDF.containsKey(pair))
						WDF.put(pair, 1);
					else
						WDF.replace(pair, WDF.get(pair) + 1);
				}
		    }

		    myReader.close();
		} catch( FileNotFoundException e ) {
		    System.out.println( "File not found " + filename );
		}
		return WDF;
    }

    public ArrayList<String> treatLine( String line ) {
	// Separate words and removes punctuation
	// Return an ArrayList of lowercase words
		String noPonc = "";
		char c;
		for( int i = 0; i < line.length(); i++ ) {
		    c = line.charAt( i );
		    if( PUNC.contains( c ) )
				noPonc += " ";
		    else
				noPonc += c;
		}
		String[] words = noPonc.split(" ");
		ArrayList<String> wLower = new ArrayList<String>();
		String w;
		for( int i = 0; i < words.length; i++ )
		    if( words[i].length() > 2 )
				wLower.add( words[i].toLowerCase() );

		return wLower;
    }

    // Inner class for a Pair of int
    private class Pair {
		int x, y;
		Pair( int x, int y ) {
			this.x = x;
			this.y = y;
		}
		private int getX() { return this.x; }
		private int getY() { return this.y; }
    }

    // Return the distance between this TextDict and another (other)
    // Cette méthode fonctionne mais pourrait être optimisée
    public double distBetweenDs( TextDict other ) {
		Map<String,Pair> common = new HashMap<>();
		Map<String,Integer> otherDict = other.dict();
		int xSum = 0;
		int ySum = 0;
		double dist = 0.0;
		
		// Iterate through this TextDict
		int x, y;
		String key;
		for( Map.Entry<String,Integer> doublet : WDF.entrySet() ) {
		    key = doublet.getKey();
		    if( otherDict.containsKey( key ) ) {
				x = WDF.get( key );
				y = otherDict.get( key );
				common.put( key, new Pair( x, y ) );
				xSum += x;
		        ySum += y;
			}
		}
		// Iterate through the common doublets
		Pair xy;
		for( Map.Entry<String,Pair> pair : common.entrySet() ) {
	    	xy = pair.getValue();
	    	x = xy.getX();
	    	y = xy.getY();
	    	dist += Math.pow( ( (double)x / xSum ) - ( (double)y / ySum ), 2.0 );
		}
		return Math.sqrt( (double)( dist / common.size() ) );
    }
}
