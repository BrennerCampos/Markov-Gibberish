public class NextWordList
{


    private static class NextWordListElement
    {
        NextWordListElement next;
        String word;
        int count;

        public NextWordListElement(String str) // constructor : will also include a count var
        {
            word = str;
            next = null;
            count = 0;
        }

    }

    private NextWordListElement first;      // initializing pointer vars
    private NextWordListElement last;
    private int size;
    // private int count;


    public NextWordList()
    {
        first = null;
        last = null;
        size = 0;
    }

    public void foundNextWord(String nextWord)      // search for the list for nextWord
    {
        NextWordListElement current = find(nextWord);       // take in word to add to the list, but first check it's not already there


                   if (current==null)   // if the word does NOT exist in our list yet...
                        {
                        current = add(nextWord);        // ...add it to our NextWordList...
                       // current.count = 1;              // ...and set its count to 1  (first of its kind in our list)
                       }
                  //  else {
                    current.count++;     // if it does already exist, up it's counter
                        // }
/*
        for (int i = 0; i < size; i++)     // move through potentially all of the steps on the list
        {
            if (!current.word.equals(nextWord))    // if it does NOT find it, add it and set count to 1
            {
                current=add(nextWord);
                count=1;
            }
            else    // if it does find it, increase the count
            {
                // current = current.next;
                current.count++;
            }

        }
*/

    }


    private NextWordListElement find(String nextWord)        // checking to see if our word is already in the list
    {

        NextWordListElement current = first;    // creating new NextWord element and setting it to the beginning of the list

        for (int i = 0; i < size; i++)     // move through potentially all of the steps on the list
        {
            if (current.word.equals(nextWord))    // if it DOES find it...
            {
                return current;     // return in to follow through with "foundNextWord"
            }
            current=current.next;   // otherwise, set the pointer to the next spot and continue dealing with it in "foundNextWord"
        }
        return null;        // if the size is 0, return that it is null
    }



    public NextWordListElement add(String nextWord)
    {
        NextWordListElement current = new NextWordListElement(nextWord);


        if (first==null)    // if there's nothing in the list yet  (aka null)
      //  if (size==0)
            {
            first=current;      //set both list pointers to current capsule with "next word"
            }
        else
        {
            last.next = current;        // sets new list pointer to after last element
        }
        last=current;

        size++;     // increase size of list
       // current.count++;
        return current;  //return current list element with updated pointers
    }


    public String getRandomWord()       // picks a random word within the NextWordList of current word
    {
        int totalCount= 0;
        int runningCount = 0;

        NextWordListElement current = first;        // start at the beginning of the NextWordList

        for (int i = 0; i < size; i++)      // iterate as long as the size of the NextWordList for word is
        {
           totalCount = current.count + totalCount;     // adds how many times word is used to totalCount

         //  if (totalCount<size)
          // {
               current = current.next;      // then move to next element
          // }
        }


        int randomNum = (int)(Math.random() * (totalCount));  // picks a random number from 0 to number of words in current NextWordList
        current = first;    // set starting position back to 0


        for (int j = 0; j < size; j++)
        {
           runningCount = current.count + runningCount;        // add to the running count

           if (runningCount >= randomNum)   // if we hit the random position or go over it...
            {
                break;                      // break out of the loop and return the current word
                //return current.word;
            }
            runningCount = current.count + runningCount;    // otherwise, add to the running count...
            current = current.next;                         // and go on to the next element
        }


        return current.word;
    }


    public int length()           //  returns index of the first instance of "str"
    {
        return size;
    }


    public void print()     // prints to console
    {

        NextWordListElement current = first;

        for (int i = 0; i < size; i++)     // move through all of the steps on the list
        {
            System.out.println("\t" +current.word +" " +current.count);   // print out each element in NextWordList as well as it's count
            current = current.next;         // then move on to next element in list
        }
        System.out.println();

    }


}
