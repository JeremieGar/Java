/**

 IFT2810 - A20
 TP2 Texte mystère

 Jeremie Garzon 20101031
 Labib Ben Nasr 20167268

 Ce programme utilise la classe TextDict.java

 */

import java.util.ArrayList;

public class tp2 {
    public static void main( String[] args ) {

	// List to store the authors' names of the texts
	//   to be compared with mystere
	ArrayList<String> author = new ArrayList<>();
	author.add( "segur" );
	author.add( "balzac" );
	author.add( "voltaire" );
	author.add( "zola" );
	author.add( "verne" );
	author.add( "hugo" );

	// Corresponding array for the TextDict
	TextDict[] dict = new TextDict[author.size()];
	for( int i = 0; i < author.size(); i++ )
	    dict[i] = new TextDict( author.get( i ) + ".txt" );

	// TextDict for the mysterious text
	TextDict mysteriousText = new TextDict( "mystere.txt" );

	// Index of the closest author/text to the mysterious text
	int min = 0;
	// Minimum distance to the mysterious text
	double distmin = 1.0; // distBetweenDs returns values < 1.0
	// Distance buffer
	double d;

	// Iterate over the authors' text to find the minimum distance and text index
	for( int i = 0; i < author.size(); i++ ) {
	    d = mysteriousText.distBetweenDs( dict[i] );
	    System.out.format( "%s %.6f\n", author.get( i ), d );
	    if( d < distmin ) {
			min = i;
			distmin = d;
	    }
	}
	// Report the mysterious author
	System.out.println( "Auteur du texte mystère : " + author.get( min ) );
    }
}
