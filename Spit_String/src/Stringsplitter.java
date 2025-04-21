
public class Stringsplitter {


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String firstSentence = "aaaa bbb cc d";
        String secondSentence = "Hallo Welt";
        String thirdSentence = "Datenstrukturen im Sommersemester 2025";


        String[] wordsFirstSentence = splitString(firstSentence);                   //["aaaa", "bbb", "cc", "d"]
        String[] wordsSecondSentence = splitString(secondSentence);                 //["Hallo", "Welt"]
        String[] wordsThirdSentence = splitString(thirdSentence);                   //["Datenstrukturen", "im", "Sommersemester", "2025"]

        String[] swappedFirstSentence = swapFirstLastWords(wordsFirstSentence);     //Erwartet: d bbb cc aaaa
        String[] swappedSecondSentence = swapFirstLastWords(wordsSecondSentence);   //["Welt", "Hallo"]
        String[] swappedThirdSentence = swapFirstLastWords(wordsThirdSentence);     //["2025", "im", "Sommersemester", "Datenstrukturen"]

        String[] sortFirstSentence = sortWordsByLength(wordsFirstSentence);         //["d", "cc", "bbb", "aaaa"]
        String[] sortSecondSentence = sortWordsByLength(wordsSecondSentence);       //["Welt", "Hallo"]
        String[] sortThirdSentence = sortWordsByLength(wordsThirdSentence);         //["im", "2025", "Sommersemester", "Datenstrukturen"]

        String[] wordLengthTwo = searchWordWithLength(wordsFirstSentence, 2);   //cc
        String[] wordLengthFour = searchWordWithLength(wordsSecondSentence, 4); //Welt
        String[] wordLenghtFourteen = searchWordWithLength(wordsThirdSentence, 14);   //Sommersemester


                                                                            
       
        System.out.println("Split First Sentence: " + String.join(", ", wordsFirstSentence));
        System.out.println("Split Second Sentence: " + String.join(", ", wordsSecondSentence));
        System.out.println("Split Third Sentence: " + String.join(", ", wordsThirdSentence));

        System.out.println("Swapped First Sentence: " + String.join(", ", swappedFirstSentence));
        System.out.println("Swapped Second Sentence: " + String.join(", ", swappedSecondSentence));
        System.out.println("Swapped Third Sentence: " + String.join(", ", swappedThirdSentence));

        System.out.println("Sorted First Sentence: " + String.join(", ", sortFirstSentence));
        System.out.println("Sorted Second Sentence: " + String.join(", ", sortSecondSentence));
        System.out.println("Sorted Third Sentence: " + String.join(", ", sortThirdSentence));

        System.out.println("Word with length 2: " + String.join(", ", wordLengthTwo));
        System.out.println("Word with length 4: " + String.join(", ", wordLengthFour));
        System.out.println("Word with length 14: " + String.join(", ", wordLenghtFourteen));
    }
	public static String[] splitString(String sentence) 
    {
        {
            String[] arrOfStr = sentence.split(" ");
            String output = "";
            for (int i = 0; i < arrOfStr.length; i++) {
                output = output.concat(arrOfStr[i]);
                if (i != arrOfStr.length - 1) {
                    output = output.concat(",");
                }
            }
    
            return arrOfStr;
        }
    }
        
    public static String[] sortWordsByLength(String[] words) {
	    int len = words.length;

	    for (int i = 0; i < len - 1; i++) {
	        for (int j = i + 1; j < len; j++) {
	            if (words[j].length() < words[i].length()) {
	                // Swap words[i] and words[j]
	                String temp = words[i];
	                words[i] = words[j];
	                words[j] = temp;
	            }
	        }
	    }

	    return words;
	}
    public static String[] swapFirstLastWords(String[] words) {
        int len = words.length;

        if (len >= 2) {
            String temp = words[0];
            words[0] = words[len - 1];
            words[len - 1] = temp;
        } else {
            System.out.println("You must give 2 or more words.");
        }

        return words;
    }


    public static String[] searchWordWithLength(String[] words, int length) {
        for (String word : words) {
            if (word.length() == length) {
                return new String[]{ word };  // return the word as array.
            }
        }

        // when no word is found
        return new String[]{ "No word found with this length" };
    }


}
