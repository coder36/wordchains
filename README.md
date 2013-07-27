Word Chains
===========

Taken from http://codekata.pragprog.com/2007/01/kata_nineteen_w.html

Write a program that solves word-chain puzzles:

Find a chain of words starting with one word and ending with another. Successive entries in the chain must all be real words, and each can differ from the previous word by just one letter. For example, you can get from "cat" to "dog" using the following chain.
    
    cat
    cot
    cog
    dog
    
The objective of this kata is to write a program that accepts start and end words and, using words from the dictionary, builds a word chain between them. Find the shortest word chain that solves each puzzle

You can turn lead into gold with the following chain:
        
    lead
    load
    goad
    gold
    
Try to capture the developer mindset and record how you to got to you solution.

Development process
===================

How did I get to my solution ?  It's all about covergence.  I start out and see where it goes, see what ideas pop up as I go along, see what sticks etc.  My generate approach is as follows:

1) Write Tests - this gets me thinking about how should I represent the data, and helps drive the interface.
2) Start writing code
3) Get to a working solution (it's not always pretty at this point)
4) Refactor Refactor Refactor until I hit a universal truth ie. the code becomes so simple it must be true!

To solve a problem I generally have a series of insghts - by this I mean ideas which seems obviously the right thing to do and possibly are a deep truth of the solution.

Definitions:
------------
distance - Given a "from" word and a "target" the distance is defined to be the number letters which would need to be changed in the "from" word to mutate it to the "target" word.  ie.  cat -> bot has a distance of 2

Here we go:
-----------

Write the unit tests - this brings the first decision - should I use List<String>'s or arrays [] to represent the dictionary? Choose [] as it makes the unit tests slightly more readable.

Thought - its going to be difficult testing with a large dictionary having to search through 1000's of words, multiple roots through the wordchain "space".

* Insight 1: Allow the tests to define the dictionary.

Idea - find a word in the dictionary which has a distance of 1 from the current word, but also contains at least 1 letter contained in the "to" word.  ie. for the wordchain cat -> dog then cot is 1 from cat and 2 from dog.  The only downside is that this strategy will only work for some word chains...

   "cat", "cot", "cog", "dog"

coding.....

The code is getting fairly complictated - theres lots of edgecases.
Idea - I'll need to keep track of words I've tried which result in dead ends.  Can I exclude these ?  from future searched or can  I?

coding.....

What happens if I need to go to a word which has no letters in common with the target word?  This is starting to get too complicated.  What I have learned is that I some how need to keep track of what wordchains I've visited which have resulted in deadends.

Idea - I'm thinking that the most reliable solution would be to brute force this, ie. work out every possible word chain.  This will produce some long chains.  Some of the chains would be dead ends and these can be discounted. Visually a brute force approach would look like a word chain tree with branched coming of branches etc.

* Insight 2: Think of the word chains as a tree to which we can prune dead branches.
* Insight 3: Use self recursion
* Insight 4: Use a stack to represent the branches

I'd need to model this tree as a Map<String, Stack<String>> with the key to the map being the root of the branch.

coding ...

This is looking better.  Can I get rid of the Map ?  I think I'm on the right track as the code is getting simpler.  The self recursion is looking good.

Amazing! - I've got something that works, my unit tests are passing.  The sef recursion involves passing a lot of things around which is not ideal, but this a break through.  I can now run refactor run my tests, refactor, run my tests etc. I can now drive hard at the solution and hopefully glean some more insights.

Time to bring in the full size dictionary.  As expected some very long chains are created 50+.  How can I reduce this ?  What about converging from either direction ?  ie dog -> cat and cat -> dog.  Interestingly coming from a different direction reduces the chain length.  The size seems to be dependent on the ordering of the dictionary.

Idea -> Generate a word chain which may contain 30 words.  Randomly shuffle the words then use this list to form the dictionary for another run and so on.  How many times would I need try.  This would by an interesting experiment - I could converge on a word chain by pure luck :)

* Insight 5:  Backup the chain when the number of words in the chain exceeds some limit

In this way the algorithm will do the pruning for me.  1 line of code !!!
    
        
    if( stack.size() > maxDepth ) return false;  // prune
    

Refactor refactor refactor...
-----------------------------

I'm going to split the code up so that the algorithm is concentrated in once class to demonstrate the simplicity of the solution.  The recursive algorithm itself is defined in 10 lines of code.

enough.... :)

Build Instructions
==================

    git checkout https://github.com/coder36/wordchains.git
    cd wordchains
    mvn clean install

    java -cp target/wordchain-1.0-SNAPSHOT.jar org.coder36.wordchain.FindMyWordchain gold lead
    gold
    goad
    load
    lead


