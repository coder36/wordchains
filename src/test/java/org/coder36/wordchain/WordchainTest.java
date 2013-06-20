package org.coder36.wordchain;


import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests
 * User: Mark Middleton
 */
public class WordchainTest {

    @Test
    public void testGetChain() {
         Wordchain testee = new WordChainImpl( new String [] {  "lead", "load", "goad", "gold", "ruby", "rube", "robe", "rode", "code", "cat", "cot", "cog", "dog"} );
         assertArrayEquals( testee.getChain( "cat", "dog" ), new String [] { "cat", "cot", "cog", "dog"} );
         assertArrayEquals( testee.getChain( "ruby", "code" ), new String [] { "ruby", "rube", "robe", "rode", "code"} );
         assertArrayEquals( testee.getChain( "lead", "gold" ), new String [] { "lead", "load", "goad", "gold"} );
    }

    public void testErrorConditions() {
        Wordchain testee = new WordChainImpl( new String [] {  "lead", "load", "goad", "gold", "ruby", "rube", "robe", "rode", "code"} );
        assertTrue( testee.getChain( "cat", "dog" ).length == 0 );
    }

    @Test
    public void testWordchainWithDict() {
        Wordchain testee = new WordChainImpl( FindMyWordChain.loadDict() );
        //print( testee.getChain( "print", "bingo" ) );
        assertArrayEquals( testee.getChain( "cat", "dog" ), new String [] { "cat", "cot", "cog", "dog"} );
        assertArrayEquals( testee.getChain( "ruby", "code" ), new String [] { "ruby", "rube", "robe", "rode", "code"} );
        assertArrayEquals( testee.getChain( "lead", "gold" ), new String [] { "lead", "load", "goad", "gold"} );
    }

    private void print( String [] l ) {
        for ( String s: l ) {
            System.out.println( s );
        }
    }

}
