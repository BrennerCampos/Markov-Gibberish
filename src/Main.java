import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileReader;

public class Main {

    static int order = 0;


    public static void main (String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the file you wish to use: ");
        String inputFile = sc.next();


            // PREPROCESS THE FILE ------V
            String processedFile = Preprocessing(inputFile);        // remove all unwanted tokens/characters
            //---------------------------^

        // now, take new preprocessed file and read it in word by word to our new KeyWord list

        System.out.println(processedFile);



        try {

            FileReader filereader = new FileReader(processedFile+".txt");      // make a new reader to read the new processed file
            Scanner sc2 = new Scanner(filereader);          // scanner for the new processed file we just took in with FileReader
            Scanner sc3 = new Scanner(System.in);

            KeywordList keywordlist = new KeywordList();    // make new KeyList object

            System.out.println("What order?: ");
            order = sc3.nextInt();
            String[] word = new String[order+1];





                for (int i = 0; i < order; i++)     // same thing, change below to word[i], word[n].....
                {
                     if (sc2.hasNext())
                     word[i] = sc2.next();
                }

            //String word1 = sc2.next();  // takes in first 2 word
           // String word2 = sc2.next();

           // System.out.println(Arrays.toString(word));

               // start with empty string
            String keyword = word[0];

            while (sc2.hasNext())
            {

                // keyword = "";

               // if (order == 1)
               // {
                //    keyword = word[0];
              //  }
                if (order > 1) {
                    keyword = "";
                    for (int i = 0; i < order; i++) {
                        keyword = keyword + word[i] + " ";     // String is now the two words together with a space in between
                    }
                }


                keyword = keyword.trim();
              /*
                String buildWord = "";      //  system for stopping at every space and building each word char by char

                for (int i = 0; i < keyword.length() ; i++)     //for as long as the next keyword is...
                {
                    char currentChar = keyword.charAt(i);  //copy every character to build new word until it gets to a space

                    if ((currentChar != ' '))
                    {
                        buildWord = buildWord+currentChar;
                    }
                }

                keyword = buildWord;        // now we have a new processed keyword

*/
                //NextWordList nextWordList = new NextWordList();

               // System.out.println("keyword: "+keyword);

               //if (sc2.hasNext())
              // {
                String nextWord = sc2.next();       // word immediately after the one we are examining
              // }

               //keywordlist.addUnique(word);

                keywordlist.foundWordSequence(keyword, nextWord);


                if (order==1)
                {
                    keyword = nextWord;  // moves on to the next word and does process over again
                }

                else
                {
                    for (int i = 0; i < order - 1; i++)   //make word[i], word[n]....
                    {
                        word[i] = word[i + 1];
                        if ((i + 1) < (order)) {
                            word[i + 1] = nextWord;
                        }// switches the words to the following word
                    }
                    // word[order] = nextWord;
                }

            }



            keywordlist.print();    // first, print out the list with all the words' NextWords


        ////   GOING ON A WALK   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            /////////////////////////////////////////////////////////////////////////////


            System.out.println("How many words? ");
            int numOfWords = sc.nextInt();

            String firstWord = keywordlist.getRandomKeyword();  // chooses a random word from the txt through the getRandomKeyword method


            System.out.print(firstWord + " ");      // prints out the first randomly picked word



            if (order==1)   // if it's an order of only one, print and switch firstword for nextword
            {
                for (int i = 0; i < numOfWords; i++)
                {
                    String nextWord = keywordlist.getRandomNextWord(firstWord);

                    firstWord = nextWord;

                    System.out.print(firstWord + " ");
                }
            }

           if (order>1) // if it's anything bigger than 1, print a special way (n-order)
            {
                for (int i = 0; i < numOfWords; i++)    // now go through for as many words as the user picked and pick random NextWords for all of them
                {
                    String nextWord = keywordlist.getRandomNextWord(firstWord);    // randomly choose next word in a weighted fashion with getRandomNextWord method

                    firstWord = firstWord.substring(firstWord.indexOf(" ") + 1, firstWord.length());      // cut out everything before the first space

                    firstWord = firstWord + " " + nextWord;   // makes "nextWord" the current word now, and do it all again until we hit the user's max number count

                    System.out.print(nextWord + " ");  // print the nextWord

                }

            }

            System.out.println(".");    // prints out a nice little period at the end.

            /*


            nextWordList.foundNextWord("you");
            nextWordList.foundNextWord("you");
            nextWordList.foundNextWord("do");
            nextWordList.foundNextWord("get");
            nextWordList.foundNextWord("you");
            nextWordList.foundNextWord("goodbye");

            //nextWordList.add("you");
            // nextWordList.add("you");
            // nextWordList.add("do");
            // nextWordList.add("get");
            // nextWordList.add("goodbye");
            nextWordList.print();

*/
            /*
            keywordlist.add("word");
            keywordlist.add("because");
            keywordlist.add("Josh");
            keywordlist.set(1, "now");
            keywordlist.print();

            filereader.close();

            */
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }


    }


    public static String Preprocessing(String fileName) throws FileNotFoundException
    {

        // PREPROCESSING---------------------------------------------------------------------
        // Removing unwanted tokens/characters

        FileReader filereader = new FileReader(fileName);
        PrintWriter printwriter = new PrintWriter(fileName+"_preprocessed.txt");

        try
        {
        while (true)
        {
            int charInt = filereader.read();      // setting up INT variable to check characters with read() function
            if (charInt == -1)        // if it still has a next char to read in
            {
                break;      // ...don't print that character
            }

            char currentChar = (char) charInt;

            if (currentChar >='A'   &&   currentChar <= 'Z')     // another way of writing, "is this uppercase?"
            {
                currentChar = (char)(currentChar - 'A' + 'a');          //   How this works:  'C' - 'A' == 2,  'a'+ 2 == 'c'
            }
            if (currentChar=='\n')      // if it's a new line (we'd want to preserve)
            {
                currentChar = ' ';        // set it to a space, which is also going to be separated by the next if command
            }


            if ((currentChar>='a'   &&   currentChar<= 'z')  || (currentChar == ' ') || (currentChar == '-') || (currentChar == '—')|| (currentChar == '\'') || (currentChar == '’'))  //only lowercase letters, spaces and certain characters  are transfered, everything else ignored
                printwriter.print(currentChar);     // if current character checked under read() passes our "tests", print it to the new "_preprocessed" file
        }
        printwriter.close();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }


        fileName = fileName+"_preprocessed";        // add tag to end of our file to indicate it's been preprocessed
        return fileName;                            //return that new file name



    }     // -----------------------------------------------------------------------------------------------



}
