package com.interview.trigram;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Scanner;

import org.junit.Test;

/**
 * 
 * @author vanibr
 *
 */
public class TriGramGenaratorTest {

	@Test
    public void InvalidInputTest() {
        try {
            new TriGramGenarator(null);
        } catch(IllegalArgumentException e) {
            return;
        }
        
        fail("Expected an illegal arg exception.");
    }
    
    @Test
    public void genrateTriGramForShortInputTextTest() {
    	TriGramGenarator generator = new TriGramGenarator("I wish I may I wish I might");
        
        Map<String,TriGramVO> trigrams = generator.getTrigrams();
        
        assertEquals(4, trigrams.keySet().size());
        
    }
    
    @Test
    public void genrateTriGramGenratesRightPairOfKeysTest() {
    	TriGramGenarator generator = new TriGramGenarator("I wish I may I wish I might");
        
        Map<String,TriGramVO> trigrams = generator.getTrigrams();
        
        assertTrue(trigrams.containsKey("i wish"));
        assertTrue(trigrams.containsKey("wish i"));
        assertTrue(trigrams.containsKey("i may"));
        assertTrue(trigrams.containsKey("may i"));
        
        assertEquals("I", trigrams.get("i may").getNextValue());
        assertEquals(2, trigrams.get("wish i").getNext().size());
        assertEquals("may", trigrams.get("wish i").getNext().get(0));
        assertEquals("might", trigrams.get("wish i").getNext().get(1));
    }
    
    @Test
    public void should_generate_text_from_trigrams() {
    	TriGramGenarator generator = new TriGramGenarator("I wish I may I wish I might");
        
        assertNotNull(generator.generateText());

    }
    
    @Test
    public void genrateTriGramForLongInputTextTest() {
    	String input = "I was seized with a keen desire to see Holmes"+
    			"again and to know how he was employing his extraordinary powers"+
    			"His rooms were brilliantly lit and even as I looked up I saw"+
    			"his tall spare figure pass twice in a dark silhouette against"+
    			"the blind";
    	TriGramGenarator generator = new TriGramGenarator(input);
        
        Map<String,TriGramVO> trigrams = generator.getTrigrams();

        assertEquals(41, trigrams.keySet().size());
        
        assertTrue(trigrams.containsKey("how he"));
        
        assertEquals("was", trigrams.get("how he").getNextValue());
    }
    
    @Test
    public void genrateTriGramForFileFromOnlineTest() throws IOException {
    	
    	URL gutenBurg = new URL("https://www.gutenberg.org/help/faq.html");
        BufferedReader in = new BufferedReader(
        new InputStreamReader(gutenBurg.openStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
        	TriGramGenarator generator = new TriGramGenarator(inputLine);
            
            Map<String,TriGramVO> trigrams = generator.getTrigrams();

            //assertNotNull(trigrams.keySet().size());
            //assertTrue(trigrams.keySet().size()>0);
        }
           
    }
    
}
