package com.interview.trigram;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Trigram Object
 * which holds one instance of trigram object and its corresponding values in next list
 * @author vanibr
 *
 */
public class TriGramVO {
	
	private Logger logger = LoggerFactory.getLogger(TriGramVO.class);
	
private static final String NO_ALPHANUM = "[^a-z0-9]";
    
    private List<String> next;
    private String key;
    
    /** constructor **/
    public TriGramVO(String[] words) {
        if (words.length != 3) {
        	logger.error("Invalid Input,Selected window of words should be 3 to considered trigram");
            throw new IllegalArgumentException("Thrigram is three words algorithm, please check curosponding ngram algorithm object	.");
        }
        
        this.key = this.generateKey(words);
        this.next = new ArrayList<String>();
        this.next.add(words[2]);
    }
    
    public String getKey() {
        return this.key;
    }
    public void addNext(String word) {
        this.next.add(word);
    }
    
    public List<String> getNext() {
        return next;
    }
    
    /** get value from trigram map **/
    public String getNextValue() {
        int randomIndex = new Random().nextInt(this.next.size());
        
        return this.next.get(randomIndex);
    }
    
    /** fetch next key from existing key for further processing **/
    public String nextKey(String next) {
        String first = this.key.split(" ")[1];
        String second = next.toLowerCase().replaceAll(NO_ALPHANUM, "");
        
        return first + " " + second;
    }
    
    

    /** retrieve first two values as key **/
    private String generateKey(String[] words) {
    	logger.info("Genrating trigram key from input words");
        return words[0].toLowerCase().replaceAll(NO_ALPHANUM, "") + " " + words[1].toLowerCase().replaceAll(NO_ALPHANUM, "");
    }

}
