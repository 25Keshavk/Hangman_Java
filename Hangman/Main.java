import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
import java.util.*;
import java.util.List;
/*
Create a "Hangman"-like game

JAVA will read from a text file called "words.txt"
The file contains a collection of words.

Example of how to read a text file using Scanner: https://www.w3schools.com/java/java_files_read.asp

JAVA selects one of the words at random.

JAVA presents a line with one underscore for each letter.

The user guesses at the letters one at a time.

With each guess, the template is re-presented showing correctly guessed letters in the correct locations and underscores for the others.

The player can get five guesses incorrect before ending the game.

At the end, ask the player if he would like to play again.

Keep track of the guesses and warn the user if he guesses the same letter again.

*/


class Main { 

    public static void main(String args[]) {

       // Create an empty array list
        ArrayList<String> words = new ArrayList<String>();
        Scanner lettercheck = new Scanner(System.in);
        // As words are read from file, add to array list....
        try {
            File myObj = new File("Words.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()){
                String data = myReader.nextLine();
                
                // System.out.println(data);
                // trim whitespace off of the string
                
                words.add(data); // add the string to the words list
                //Scanner split = new Scanner(data);
                
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        
        //Split String into a list
        // pick random integer between 0 and size of list
        // Use index of random integer to pick random word
      String continuePlaying = "";
      do {
        ArrayList<String> underscores = new ArrayList<String>();
        Random rd = new Random();
        int range = words.size();

          int randomnum = rd.nextInt(range);
          String hangmanword = words.get(randomnum);
          int seccounter = 0;
          int counter = 5;
          int numofletters = hangmanword.length();
          for (int i = 0; i<numofletters; i++ ){
              underscores.add ("_");
          }
          //System.out.println(underscores);
          showCurrent(underscores); // dispaly the word with underscores
          String[] letters = hangmanword.split("");
          List<String> letterlist = new ArrayList<String>();
          letterlist = Arrays.asList(letters);
          boolean rightGuess;
          boolean same;
          boolean duplicate;
          // MAIN GAME LOOP...
          List<String> Guesses = new ArrayList<String>();

          while (counter > 0 ){
              
              rightGuess = false;
              duplicate = false;
              //Adding underscores for each letter in word
              System.out.println(" Guess a letter ");
              String check = lettercheck.nextLine();
              //int sizeguesseslist = Guesses.size();

              // Check for duplicate guesses. If the guess is a duplicate, get input again

              for (String previous_guess : Guesses) {
                  if (check.equals(previous_guess)) {
                      duplicate = true;
                      System.out.println("You've already guessed that!");
                  }
              }
              if (duplicate == true){
                continue;
              }
            
              for (int ja = 0; ja < numofletters; ja++){
                  //System.out.println(check);
                  //System.out.println(letterlist.get(ja));

                  // Skip letters that have already been guessed
                  String letterforloop = letterlist.get(ja);
                  same = check.toUpperCase().equals(letterforloop.toUpperCase());
                  if (same == true){
                      rightGuess = true;
                      //if (underscores.get(ja).equals("_"){
                      underscores.set(ja,letterlist.get(ja));
                      seccounter+=1;
                      //}
                  }
                  // ///else {
                  //     ///for (int guessesrunner =0;
                  //     //  guessesrunner < sizeguesseslist; guessesrunner += 1)
                  //       {
                  //        /// if (Guesses.get(guessesrunner).equals(check)){
                  //         //  duplicate = true;
                  //         }
                          

                  //       } 
                  // }
                      
                
              }
              //if ( samecheck == true){
                //System.out.println("You've already guessed that");
                //continue;
              // if (duplicate == true){
              //   System.out.println("You've already guessed that letter");
              //}
                
              if (rightGuess == true){
                  System.out.print("You're right: ");
                  showCurrent(underscores);
                  System.out.println();
              }  
              else{
                  System.out.println("That was not one of the letters");
                  counter -= 1;
              }
              if (seccounter == letterlist.size()){
                  System.out.println("You won!");
                  counter = 0;
                  
              }
              if (counter == 0){
                System.out.print("You lost, The word was " + hangmanword + ". Do you want to keep playing?:");
                continuePlaying = lettercheck.nextLine();
              }
              Guesses.add(check);
              System.out.println("You have " +counter + " guesses left");
          } // closing main while loop

      } while (continuePlaying.toUpperCase().equals("YES") );
 
    }

    // [static] public/private return-object-type name (arguments/parameters) {}

    public static void showCurrent(ArrayList<String> str) {
        // loop through str and print each one out
        for (String letter: str){
          System.out.print(letter);
        }
    }


}    
   


/*

FOR NEXT TIME...

Try to finish up this program so that all parts are working properly.

(Handling guessing letters that have already been guessed.)

Add some more words to the text file.

Try/test it without showing the word at the beginning.

Add: Shows the number of guesses remaining....

*/
