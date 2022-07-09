package com.interview.trigram;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Trigram algorithm implementer/ generator class
 * @author vanibr
 *
 */
public class TriGramGenarator {
	
	private Logger logger = LoggerFactory.getLogger(TriGramGenarator.class);
	private Map<String,TriGramVO> trigrams;

    /**
     * Generator constructor with input string
     * @param input
     */
    public TriGramGenarator(String input) {
        if (null == input || input.trim().length() == 0) {
        	logger.error("Invalid Input,Given input is null/empty");
            throw new IllegalArgumentException("Invalid Input, please check input string");
        }
        
        this.trigrams = generateTrigrams(input);
    }

    /** Get method to feth genrated Trigram **/
    public Map<String, TriGramVO> getTrigrams() {
        return this.trigrams;
    }
    
    /**
     * Trigram genrator algorith implemenatation
     * @param input input string
     * @return
     */
    private Map<String, TriGramVO> generateTrigrams(String input) {
    	logger.error("Start Genrating trigram for given input string");
        Map<String,TriGramVO> trigrams = new HashMap<String,TriGramVO>();
        
        String[] words = input.split(" ");
        
        int last = words.length - 3;
        
        for (int i = 0; i <= last; i++) {
            String[] subset = new String[3];
            subset[0] = words[i];
            subset[1] = words[i+1];
            subset[2] = words[i+2];
            
            TriGramVO trigram = new TriGramVO(subset);
            
            String key = trigram.getKey();
            
            if (trigrams.containsKey(key)) {
                trigrams.get(key).addNext(trigram.getNextValue());
            } else {
                trigrams.put(key, trigram);
            }
        }
        
        return trigrams;
    }
    

    public String generateText() {
        StringBuilder s = new StringBuilder();
        
        String key = getStartKey();
        
        while(this.trigrams.containsKey(key)) {
        	TriGramVO trigram = this.trigrams.get(key);
            String word = trigram.getNextValue();
            
            s.append(word).append(" ");
            
            key = trigram.nextKey(word);
        }
        
        return s.toString();
    }
    
    private String getStartKey() {
        Set<String> keys = this.trigrams.keySet();
        
        int randIndex = new Random().nextInt(keys.size());
        int i = 0;
        
        for (String key: keys) {
            if (i == randIndex) {
                return key;
            }
            
            i++;
        }
        
        return null;
    }
}
