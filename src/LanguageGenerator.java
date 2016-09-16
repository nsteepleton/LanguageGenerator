
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LanguageGenerator {
	public static HashMap occurances = new HashMap(); 
	public static ArrayList<String> startWords = new ArrayList<String>(); 
	static double numOfWords = 0;
	static String[] words;
	int numOfNextWords;
	ArrayList<String> nextWordOccurances = new ArrayList<String>();
	static String corpus;
	static String[] corpusWords;
	
	/**
	 * Consumes an int n representing the n to be used for the n gram model and a string s 
	 * representing whatever is on the random string (initially null), and an int length
	 * representing how long the final randomly generated language should be
	 * This method will call n-gram recursively until a random string is generated
	 * 
	 * @param int n, String s, int length
	*/ 
	public void generateSentance(int n, String s, int length) {
		String startString =s;
		// just for testing
		startWords.add("I");
		startWords.add("The");
		
		//add the fist word to the random string
		Random random = new Random();
		String startWord = startWords.get(random.nextInt(startWords.size()));
		startString = startWord;
		
		// now that we have a start word send it to the n-gram generator o generate the rest of the string
		nGram(n, startString, length);		
	}
	

	/**
	 * n-gram sentence generator Consumes an n for which to create the n gram
	 * a string containing the first word of the random string and an integer 
	 * representing the length of the generated string
	 * the method will will call itself recursively to create a random string using an n gram model to predict words in the String
	 * @param int n the n used for n gram model, String s the random string, int l the length of the random string to create
	 * @return String the random string
	 */
	public String nGram(int n, String s, int l) {
		String randomSentence = s;
		String [] randomS = randomSentence.split("\\s+");
		String nSubstring ="";
		String [] nString;
		nextWordOccurances.clear();
		// nSubstring will be a string of n or less words
		if (randomS.length < n){
		 nSubstring = randomSentence;
		}
		else{
			// create a substring to use for n gram model: this will be the last n words of the random string
			for(int i= randomS.length-1; nSubstring.length() < n; i-- ){
				nSubstring = randomS[i]+ " " + nSubstring;
			}
		}
		
		System.out.println("nSubstring: "+ nSubstring);
		// split the substring into an array
		if(nSubstring.contains(" ")){
		nString = nSubstring.split(" ");
		}
		else{
			nString = new String [1];
			nString[0] = nSubstring;
		}
		for(int i = 0; i < nString.length; i++){
			System.out.println("nStrig: "+ nString[i]);
		}
		System.out.println("random string: "+ s);
		System.out.println("corpusWords.length "+corpusWords.length);
		int length = l; // how long the final string should be
		//nString
		// go through the corpus and find a point where the corpus contains the first word of the random sentence
		System.out.println("nString[0]" + nString[0]);
		for (int i = 0; i < corpusWords.length; i++) {
			if (corpusWords[i].equals(nString[0])) {
				System.out.println("corpus words[i]: "+corpusWords[i]);
				// call the compare method to add some words to the next words array
				// the index in corpus, 0 the index in the random sentence array, String random Sentence
				compare(i,0,nSubstring);
			}
		}
		System.out.println("the next word occurances size" + nextWordOccurances.size() );
		// at this point there should be a list of next words
		// choose the next word based off of the probabilities/ randomly choose from the array
		for (int i = 0; i< nextWordOccurances.size(); i++){
			System.out.println("next Word occurance: "+ nextWordOccurances.get(i));
		}
		System.out.println("next word occurances: "+ nextWordOccurances.size());
		
		Random randomGenerator = new Random();
		int randomI = randomGenerator.nextInt(nextWordOccurances.size());
        String nextWord = nextWordOccurances.get(randomI);
        randomSentence += " " + nextWord;
        System.out.println("random sentence: "+ randomSentence);
        
        String rs [] = randomSentence.split(" ");
        if (rs.length<length){
        	// need to take into account that we are only comparing for the alst n words
        	nGram(n, randomSentence,length);
        }
        
		return randomSentence;
	}
	
	
	/**
	 * consumes the index in the corpus that matches the index in our random text and then Searches through the two 
	 * arrays and sees if they are equal up to the length of the random string if they are then the word following 
	 * the random string in the corpus will be added to the hash of next words
	 * 
	 * @param corpusI the current index in the corpus 
	 * @param randomI the current index in the random string (originally 0)
	 * @return void
	 */
		public void compare(int corpusI, int randomI, String randomSentence){
		
		int cI = corpusI;
		int rI = randomI;
		String rs [] = randomSentence.split(" ");
		boolean nextTrue = true;
		// loop through the random sentence comparing each word to the corresponding index in the corpus
		for(int i = rI; i<rs.length ; i++){
			System.out.println("the index for random sentance: "+ i);
			System.out.println("the index for corpus: "+ cI);
			System.out.println("corpusWords[cI]: "+ corpusWords[cI]);
			System.out.println("rs[i]: "+rs[i] );
			int lmone = rs.length-1;
			System.out.println("rs.length-1: "+ lmone);
			// if the last word in the random string is equal to the index in the corpus then add the next word in the corpus to next word occurrences
			if(i == rs.length-1){
				// if the last element in the random sentence == whatever is in the corpus then add the next word in the corpus to next word occurrences
				if(rs[i].equals(corpusWords[cI])){
					int next = cI+1;
					String key = corpusWords[next];
					numOfNextWords++;
					nextWordOccurances.add(key);
					System.out.println("key "+key);
			}

			if(rs[i]!= corpusWords[cI]){
				nextTrue = false;
			}
			}
			cI++;
		}
	}
	
		
	/**
	 * will prompt the reader to enter the path of the directory of the file
	 * that they want to use to begin generating their language
	 * 
	 * @param args
	 */
	public static void main(String[]args){
		
		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader("C:\\Users\\natalie\\Desktop\\textcorpus\\CrimeAndPunishment.txt"));
			while ((sCurrentLine = br.readLine()) != null) {
				String line = br.readLine();
				line = line.replaceAll(",", "");
				line = line.replaceAll("/", "");
				line = line.replaceAll("\"", "");
				corpus += line;

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		corpusWords = corpus.split(" ");
		for(int i=0; i<corpusWords.length; i++){
			System.out.println(corpusWords[i]);
		}
		
		LanguageGenerator l = new LanguageGenerator();
		// pass in an n to generate sentence
		int n = 3;
		String s = null;
		int len = 20;
		l.generateSentance(n, s, len);
		}
	}
	

