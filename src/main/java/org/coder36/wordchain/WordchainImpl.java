package org.coder36.wordchain;

import java.util.Stack;

/**
 * @author MArk Middleton
 */
public class WordchainImpl implements Wordchain {
    private String [] dict;
    private int depth;

    public WordchainImpl(String[] wordList, int depth) {
        this.dict = wordList;
        this.depth = depth;
    }

    public WordchainImpl(String[] wordList) {
        this(wordList, 10);
    }


    public String [] getChain( String from, String to ) {
        // check that from and to exists
        if ( ! ( dictHas(from) && dictHas(to) ) ) return new String[0];

        for ( int i=0; i < depth; i++ ) {
            Stack<String> stack = new Stack<>();
            recurse( from, to, stack, i );
            if ( stack.size() != 1 ) return stack.toArray( new String[stack.size()] );
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
