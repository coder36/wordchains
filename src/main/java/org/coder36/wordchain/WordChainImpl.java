package org.coder36.wordchain;

import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: Mark
 * Date: 20/06/13
 * Time: 16:07
 * To change this template use File | Settings | File Templates.
 */
public class WordChainImpl implements Wordchain {
    private String [] dict;
    private int depth;

    public WordChainImpl( String [] wordList, int depth ) {
        this.dict = wordList;
        this.depth = depth;
    }

    public WordChainImpl( String [] wordList ) {
        this(wordList, 10);
    }


    public String [] getChain( String from, String to ) {
        // check that from and to exists
        if ( ! ( dictHas(from) && dictHas(to) ) ) return new String[0];

        for ( int i=0; i < depth; i++ ) {
            Stack<String> stack = new Stack<>();
            recurse( from, to, stack, i );
            if ( stack.size() != 1 ) return stack.toArray( new String[0] );
        }
        return new String[0];
    }

    private boolean recurse( String from, String to, Stack<String> stack, int maxDepth ) {
        stack.push( from );
        if( stack.size() > maxDepth ) return false;  // prune
        if ( from.equals( to) ) return true;

        for ( String w: dict) {
            if ( w.length() == from.length() && dif( from, w ) == 1 && !stack.contains(w) ) {
                if ( recurse( w, to, stack, maxDepth ) ) return true;
                stack.pop();
            }
        }
        return false;
    }

    private int dif( String a, String b ) {
        int dif = 0;
        for ( int i=0; i< a.length(); i++ ) {
            if ( a.charAt(i) != b.charAt(i) ) dif++;
        }
        return dif;
    }

    private boolean dictHas(String word) {
        for ( String s: dict) {
            if ( s.equals( word ) ) return true;
        }
        return false;
    }

}
