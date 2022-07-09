package com.interview.trigram.app;

import java.util.Map;

import com.interview.trigram.TriGramGenarator;
import com.interview.trigram.TriGramVO;

/**
 * Application to invoke the Trigram
 * @author vanibr
 *
 */
public class TriGramApplication {

	public static void main(String args[]) {
		String input  = "I wish I may I wish I might";
		TriGramGenarator generator = new TriGramGenarator(input);
        
        Map<String,TriGramVO> trigrams = generator.getTrigrams();
	}
}
