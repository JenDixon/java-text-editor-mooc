package document;

import java.util.List;

/** 
 * A naive implementation of the Document abstract class. 
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class BasicDocument extends Document 
{
	/** Create a new BasicDocument object
	 * 
	 * @param text The full text of the Document.
	 */
	public BasicDocument(String text)
	{
		super(text);
	}
	
	
	/**
	 * Get the number of words in the document.
	 * "Words" are defined as contiguous strings of alphabetic characters
	 * i.e. any upper or lower case characters a-z or A-Z
	 * 
	 * @return The number of words in the document.
	 */
	@Override
	public int getNumWords()
	{
		List<String> tokens = getTokens("([a-zA-Z]+)");
	    return tokens.size();
	}
	
	/**
	 * Get the number of sentences in the document.
	 * Sentences are defined as contiguous strings of characters ending in an 
	 * end of sentence punctuation (. ! or ?) or the last contiguous set of 
	 * characters in the document, even if they don't end with a punctuation mark.
	 * 
	 * @return The number of sentences in the document.
	 */
	@Override
	public int getNumSentences()
	{
	    List<String> tokens = getTokens("[^!?.]+");	    
	    System.out.println(tokens.size());	    
        return tokens.size();
	}
	
	/**
	 * Get the number of sentences in the document.
	 * Words are defined as above.  Syllables are defined as:
	 * a contiguous sequence of vowels, except for an "e" at the 
	 * end of a word if the word has another set of contiguous vowels, 
	 * makes up one syllable.   y is considered a vowel.
	 * @return The number of syllables in the document.
	 */
	
	
	public static boolean hasLetter(String word, char letter) {
		for (char c : word.toCharArray()){
			if(c == letter){
				return true;
			}
		}
		return false;
	}
	
	public static int countLetter(String word, char letter) {
		int count = 0;
		for (char c : word.toCharArray()){
			if(c == letter){
				count++;
			}
		}
		return count;
	}
	
	public static int countVowels(String word) {
		int count = 0;
		word.toLowerCase();
		for (char c : word.toCharArray()){
			if (c == 'a' || c == 'e' || c == 'i'
	                || c == 'o' || c == 'u'
	                || c == 'y'){
				count++;
			}
		}
		return count;
	}
	
	public static boolean isVowel(char c){
			if (c == 'a' || c == 'e' || c == 'i'
	                || c == 'o' || c == 'u'
	                || c == 'y'){
				return true;
			}		
		return false;
	}
	
	@Override
	public int getNumSyllables()
	{
		int count = 0;
		int numWords = getNumWords();

		List<String> wordTokens = getTokens("([a-zA-Z]+)");	    
	    List<String> tokensConstWithoutE = getTokens(("[aeiouyAEIOUY]+"));
	    
	    for(int i = 0; i < numWords; i++){
	    	String word = wordTokens.get(i);
	    	
	    	if(word.charAt(word.length() - 1) == 'e' && countVowels(word) > 1 && word.length() > 2 && isVowel(word.charAt(word.length() - 2)) != true){
		    	count++;
	    	}
	    }
	    
	    String test ="abc 90iom sktit";
	    
	    char[] letters = test.toCharArray();
	    int n = 0;
	    
	    for (int i = 0; i < letters.length; i++) {
	        if (letters[i] == ' ') {
	           letters[i] = '_';
	           n++;
	        }
	    }
	    
	    System.out.println(n);
	    System.out.println(letters);
	    
		//System.out.println("tokensConstWithoutE " + tokensConstWithoutE);
		//System.out.println("tokensConstWithoutESize " + tokensConstWithoutE.size());
		
        return tokensConstWithoutE.size() - count;
	}
	
	
	/* The main method for testing this class. 
	 * You are encouraged to add your own tests.  */
	public static void main(String[] args)
	{
		testCase(new BasicDocument("This is a test.  How many???  "
		        + "Senteeeeeeeeeences are here... there should be 5!  Right?"),
				16, 13, 5);
		testCase(new BasicDocument(""), 0, 0, 0);
		testCase(new BasicDocument("sentence, with, lots, of, commas.!  "
		        + "(And some paren)).  The output is: 7.5."), 15, 11, 4);
		testCase(new BasicDocument("many???  Senteeeeeeeeeences are"), 6, 3, 2);
	}
	
}
