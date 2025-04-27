public class Stringsplitter {

    public static void main(String[] args) {
        String firstSentence = "aaaa bbb cc d";
        String secondSentence = "Hallo Welt";
        String thirdSentence = "Datenstrukturen im Sommersemester 2025";


        String[] wordsFirstSentence = splitString(firstSentence);                   //["aaaa", "bbb", "cc", "d"]
        String[] wordsSecondSentence = splitString(secondSentence);                 //["Hallo", "Welt"]
        String[] wordsThirdSentence = splitString(thirdSentence);                   //["Datenstrukturen", "im", "Sommersemester", "2025"]

        // OUTPUT: Printing before the call of swapping and sorting functions that are manipulating the strings.
         System.out.println("Split First Sentence: [" + String.join(", ", wordsFirstSentence) + "]");
         System.out.println("Split Second Sentence: [" + String.join(", ", wordsSecondSentence) + "]");
         System.out.println("Split Third Sentence: [" + String.join(", ", wordsThirdSentence) + "]");

        String[] swappedFirstSentence = swapFirstLastWords(wordsFirstSentence);     //Erwartet: d bbb cc aaaa
        String[] swappedSecondSentence = swapFirstLastWords(wordsSecondSentence);   //["Welt", "Hallo"]
        String[] swappedThirdSentence = swapFirstLastWords(wordsThirdSentence);     //["2025", "im", "Sommersemester", "Datenstrukturen"]

        String[] sortFirstSentence = sortWordsByLength(wordsFirstSentence);         //["d", "cc", "bbb", "aaaa"]
        String[] sortSecondSentence = sortWordsByLength(wordsSecondSentence);       //["Welt", "Hallo"]
        String[] sortThirdSentence = sortWordsByLength(wordsThirdSentence);         //["im", "2025", "Sommersemester", "Datenstrukturen"]

        String wordLengthTwo = searchWordWithLength(wordsFirstSentence, 2);   //cc
        String wordLengthFour = searchWordWithLength(wordsSecondSentence, 4); //Welt
        String wordLenghtFourteen = searchWordWithLength(wordsThirdSentence, 14);   //Sommersemester

        
        // OUTPUT
         System.out.println("\n");
         System.out.println("We can notice the changes in the string similar to as the sorting function as it was last to call. ");

         System.out.println("Split First Sentence: [" + String.join(", ", wordsFirstSentence) + "]");
         System.out.println("Split Second Sentence: [" + String.join(", ", wordsSecondSentence) + "]");
         System.out.println("Split Third Sentence: [" + String.join(", ", wordsThirdSentence) + "]");

         System.out.println("\n");
         System.out.println("Swapped First Sentence: [" + String.join(", ", swappedFirstSentence) + "]");
         System.out.println("Swapped Second Sentence: [" + String.join(", ", swappedSecondSentence) + "]");
         System.out.println("Swapped Third Sentence: [" + String.join(", ", swappedThirdSentence) + "]");

         System.out.println("\n");
         System.out.println("Sorted First Sentence: [" + String.join(", ", sortFirstSentence) + "]");
         System.out.println("Sorted Second Sentence: [" + String.join(", ", sortSecondSentence) + "]");
         System.out.println("Sorted Third Sentence: [" + String.join(", ", sortThirdSentence) + "]");

         System.out.println("\n");
         System.out.println("Word with length 2: " + wordLengthTwo);
         System.out.println("Word with length 4: " + wordLengthFour);
         System.out.println("Word with length 14: " + wordLenghtFourteen);
    }

    public static String[] splitString(String sentence) {
        
       // Splitting the sentence into words
       String[] arrOfStr = sentence.split(" ");
       return arrOfStr;
}

    public static String[] sortWordsByLength(String[] words) {
        int len = words.length;

        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                String tempi = words[i]; // storing the adjacent words in tempi and tempj
                String tempj = words[j];

                if (tempj.length() < tempi.length()) {
                    // Swapping words[i] and words[j] according to the length
                    words[i] = words[j];
                    words[j] = tempi;
                }
            }
        }
        
        return words;
    }
    
    public static String[] swapFirstLastWords(String[] words) {
        if (words.length >= 2) {
            String[] swappedWords = new String[words.length];
            for (int i = 0; i < words.length; i++) {
                swappedWords[i] = words[i];
            }
            // Performing bubble sort.
            String temp = swappedWords[0];
            swappedWords[0] = swappedWords[swappedWords.length - 1];
            swappedWords[swappedWords.length - 1] = temp;
            return swappedWords;
        } else {
            System.out.println("You must give 2 or more words.");
            return words;
        }
    }

    public static String searchWordWithLength(String[] words, int length) {
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() == length) {
                return words[i];
            }
        }
        return "No word found with this length";
    }
}


