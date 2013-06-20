package org.coder36.wordchain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Find chains between 2 words
 * Usage:    java org.coder36.wordchain.FindMyWordChain <fromWord> <toWord>
 * User: Mark Middleton
 */
public class FindMyWordChain {

    public static void main( String [] args ) {
        if ( args.length != 2 ) {
            System.err.println( "Usage: java org.coder36.wordchain.FindMyWordChain <fromWord> <toWord>" );
            System.exit( 1 );
        }

        Wordchain wc = new WordChainImpl( loadDict(), 10 );
        for( String s: wc.getChain( args[0], args[1]) ) {
            System.out.println( s );
        }
    }

    public static String [] loadDict() {
        try ( BufferedReader reader = new BufferedReader( new InputStreamReader(  new Object().getClass().getResourceAsStream( "/dictionary.txt") )  ) ) {
            List<String> l = new ArrayList<>();
            String s = null;
            while ( (s = reader.readLine() ) !=null ) {
                l.add(s);
            }
            return l.toArray(new String[0]);
        }
        catch( IOException e ) {
            throw new RuntimeException(e);
        }
    }

}
