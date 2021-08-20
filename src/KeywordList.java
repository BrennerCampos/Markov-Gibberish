public class KeywordList
{


    private KeywordListElement first;       //setting up our list pointers
    private KeywordListElement last;
    private int size;




    private static class KeywordListElement     // "element" private class for initializing vars and constructor


    {
        String word;
        KeywordListElement next;
        NextWordList nextWordList;

        public KeywordListElement(String str)          // constructor
        {
            word = str;         // contains a word in question
            next = null;        // no pointer to its successor yet
            nextWordList = new NextWordList();     // also has a NextWord list containing all words after it
        }
    }


    public KeywordList()
    {
        first = null;
        last = null;
        size = 0;   // keeping track of if the list is empty
    }


    // O(1)
    public void add(String str)     // adds "str" to the Linked List
    {
        // two cases: 1.) list is empty   2.) list has 1 or more elements
        KeywordListElement element = new KeywordListElement(str);       // creates a "capsule", or new element for "str" coming in,  to be able to use "first" and "last" because they are KeywordList elements

        // make element new end of the list, have to move the pointer to new element otherwise
        //if (size == 0)
        if (first==null)
        {                // if the list is still empty...
            first = element;            // ...beginning of the list and end of the list both point to same capsule
        } else
            {
            last.next = element;        // connect element to end of array, refers to the previously last element in the list before new element came in
            last = element;
            }

        last = element;
        size++;

    }

    public void addUnique(String str)       // uses the "find" function and add words to the list if they are not currently present
    {

        if ( find(str) == -1)      // if it returns - 1, it couldn't find another word of the same kind through the whole thing
        {
            add(str);       // then you can add it to the list
        }

    }


    public String get(int index)        // returns the string at index "index", or element we want to find
    {
        KeywordListElement current = first;     // dummy element, keeps track of where you are, start at first element

        for (int i = 0; i < index; i++)     // do this for each time to move pointer along to "index", our target
        {
            current = current.next;         // move to next element in list
        }

        // now current is at the element I want

        return current.word;
    }

    public String getRandomKeyword()
    {
       // KeywordListElement current = first;

       int randomNum = (int)(Math.random() * (size));

       String randomWord = get(randomNum);

       return randomWord;

    }



    public String getRandomNextWord(String word)
    {

        KeywordListElement current = referenceSearch(word); // find matching keyword element

        String nextword = current.nextWordList.getRandomWord();

        return nextword;
    }

    public void set(int index, String newWord) {

        KeywordListElement current = first;     // dummy element, keeps track of where you are, start at first element

        for (int i = 0; i < index; i++)     // do this for each time to move pointer along to "index", our target
        {
            current = current.next;         // move to next element in list
        }


        current.word = newWord;       // instead of returning it, we're changing current word's value, only difference between  this and set

    }


    public int length()           //  returns size of current KeyWordList
    {
        return size;
    }


    public int find(String str) {

        KeywordListElement current = first;     // dummy element, keeps track of where you are, start at first element

        for (int i = 0; i < size; i++)     // move through potentially all of the steps on the list
        {
            if (current.word.equals(str))      // checks to see if it's the thing I'm looking for
            {
                return i;
            }
            else
                {
                current = current.next;
                }
        }
        return -1;     // means we couldn't find it
    }


    public void foundWordSequence(String keyword, String nextword)
    {

        try
        {
            addUnique(keyword);     // check that first word, see if we have it in our list yet. If not, add it.
           // NextWordList nextWordList = new NextWordList();
            // nxl.add(nextword);
            KeywordListElement current = referenceSearch(keyword);   //  current needs to be pointed to the element that has our keyword
            // KeywordListElement current = first;
            // current.nextWordList.add(nextword);
           // current.nextWordList.add(nextword);


            // System.out.println(current.nextWordList);

            // NULL POINTER EXCEPTION under NEXTWORDLIST...
            current.nextWordList.foundNextWord(nextword);  // now add the following word to that first word's nextWordList
        }
        catch (NullPointerException e)
        {
            System.out.println("Null pointer exception caught.");
        }

        //KeywordListElement current = first;     // dummy element, keeps track of where you are, start at first element

       // keyword = current.word;
        //nextword = current.next.word;

    }


    public KeywordListElement referenceSearch(String str)       // have to search through the keywordList and check which element contains the word we want
    {

        KeywordListElement current = first;     // dummy element, keeps track of where you are, start at first element
        //NextWordList current2 = first.nextWordList;

        for (int i = 0; i < size; i++)     // move through all of the words in the list
        {
           if (current.word.equals(str))     // if it finds it..
           {
               return current;              // return current element
           }
           current = current.next;          // otherwise, check the next element
        }

        return null;        // if can't find it or list is empty, return null
    }


    public void print()     // prints out the entire list to the console
    {

        KeywordListElement current = first;     // dummy element, keeps track of where you are, start at first element
      // NextWordListElement nxCurrent; //

        for (int i = 0; i < size; i++)     // move through all of the steps on the list
        {
            System.out.println(current.word + ": ");   // print out each element's index
          //  System.out.println("\t" +current.nextWordList +" " +current.nextWordList.count);
           current.nextWordList.print();
            // .print();

            current = current.next;         // move to next element in list
        }
        System.out.println();

    }


}

