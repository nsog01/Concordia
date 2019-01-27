//********************************************************************
// Author: W. Savitvh (modified by L. Kosseim)
//
//  This program demonstrated simple string manipulation
//********************************************************************


public class StringProcessingDemo
{
    public static void main(String[] args)
    {
        String sentence = "I hate text processing!";  // this is a string
        int position = sentence.indexOf("hate"); // the variable "position" 
            // now contains the position of the first occurrence of "hate" 
            // within the sentence 
        String ending = sentence.substring(position + "hate".length( )); // what does "ending" contains?

        // let's display some things
        System.out.println("01234567890123456789012"); 
        System.out.println(sentence);
        System.out.println("The word \"hate\" starts at index "
                                                      + position);
                                                      
        // the variable sentence now points to a brand new string...
        sentence = sentence.substring(0, position) + "adore" 
                                                    + ending;
        System.out.println("The changed string is:");
        System.out.println(sentence);
    }
}
