package org.coder36.wordchain;

/**
 * @author Mark Middleton
 */
public interface Wordchain {

    /**
     * Get chain of words
     * @param from the from word
     * @param to the to word
     * @return list of words linking the "from" word to the "to" word
     */
    public String [] getChain( String from, String to );
}
