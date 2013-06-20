package org.coder36.wordchain;

/**
 * Development process:
 *
 * Start by writing the unit tests - this brings the first decision - should I use List<String>'s or arrays [] to represent the dictionary?
 * Choose [] as it makes the unit tests a bit nicer to read.  I did try List's but it felt a bit clunky.  With the size of the
 * dictionary It's going to be tricky to debug etc.  This triggered the first insight:
 * *** Insight 1: Allow the tests to define the dictionary
 *
 * My first approach is to find a word from the dictionary which has a distance of 1 from the current word, but has a letter
 * in common with the target word.  I'll need to keep track of words I've tried which result in dead ends.
 * What happens if I need to go to a word which has no letters in common with the target word?
 * This is starting to get complicated and I'm not getting anywhere.
 * In the back of my mind I'm thinking that the most reliable solution would be to brute force this, ie. work out every
 * possible word chain.  This will produce some long chains.  Some of the chains would be dead ends, but I could backup and try
 * different chin.  Visually this would look like a tree.
 * *** Insight 2: Think of the word chains as a tree to which we can prune dead branches.
 * *** Insight 3: Use self recursion
 * *** Insight 4: Use a stack
 * This is looking fairly good now. I'm on the right track as things are getting simpler
 * My tests are passing.  I'll keep on refactoring it whilst running the tests to make sure
 * I haven't broke anything.  The solution is looking super elegant now.  I'm converging on something.
 *
 * I've ot something which works now and is elegant.
 *
 * When I try the full size dictionary, as expected some very long chains are created.  How can I reduce this ?  What about
 * converging from either direction ?  ie dog -> cat and cat -> dog.  Interestingly coming from different direction reduces the chain
 * length.  The size seems to be dependent on the ordering of the dictionary.  How about this for an idea -> Generate a word
 * chain which may contain 30 words.  Randomly shuffle the words then use this list to form the dictionary for another run
 * and so on.  How many times would I try - when the chain has got down to an acceptable length.
 *
 * There must be something better than this.  I'm close as the code is so simple.
 *
 * *** Insight 4:  Backup the chain when the number of words in the chain exceeds some limit
 * In this way the algorithm will do the pruning for me.  This can be done in 1 line of code - this must be it.
 *
 * The algorithm has 10 lines of code!  Can I reduce the line count anymore?  Probably not!
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
