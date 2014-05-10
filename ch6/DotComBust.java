/* This file is basically the Game Player Class */

import java.util.*;

class DotComBust{

    // Declare and initialize the variabls we will need
    private GameHelper helper = new GameHelper();
    private ArrayList<DotCom> dotComList = new ArrayList<DotCom>();
    private int numGuesses = 0;

    //Setting up the Game Board
    public void setUpGame(){
        
        //Make three Dot Com Objects, and stick em in the array List.
        DotCom one = new DotCom();
        one.setName("go2.com");
        DotCom two = new DotCom();
        two.setName("Pets.com");
        DotCom three = new DotCom();
        three.setName("eToys.com");
        
        dotComList.add(one);
        dotComList.add(two);
        dotComList.add(three);
        
        //Helper instructions for the player to start playing the game
        System.out.println("Hi, Welcome to Sink a Dot Com!");
        System.out.println("The computer maintains a 7X7 grid.");
        System.out.println("Inside the grid, there are three Dot Com Arrays\n" +
                           "of size 3 each. ");
        System.out.println("Your Job is to make guesses about their position");
        System.out.println("And sink them in the minimum possible no of guesses");   
        
        //Setting location for each DotCom object.
        
        for(DotCom dotComToSet : dotComList){
            //placeDotCom does maintain a set of bounds on the array. (7X7)
            //calls the setter method to set the location on dotComToSet.
            ArrayList<String> pos = helper.placeDotCom(3);
            dotComToSet.setCellLocation(pos);
        }                
        
    }
    
    public void startPlaying(){
        //Loop until dotComList is empty 
        while(!dotComList.isEmpty()){
            // Get user input string.
            String inputGuess = helper.getUserInput("Enter a guess : ");       
            // Check the user input guess.
            checkUserGuess(inputGuess);
        }
        //Finish the game in case the dotComList is empty, viz all have sunk
        finishGame();
    }
    
    public void checkUserGuess(String inputGuess){
 
        //Incrementing the number of guesses.
        numGuesses++;
        // Set the result to You Miss,
        String result = "You Miss!";
        // Loop and check for occurence in any of the three dot coms.
        for(DotCom dotComToTest : dotComList){
            // Checking the user input versus each dotCom.
            String temp = dotComToTest.checkGuess(inputGuess);
            if(temp.equals("You Hit!")){
                result = temp;
                break;
            }
            
            if(temp.equals("You Kill!")){
                result = temp + " DotCom Sunk : " + dotComToTest.getName();
                dotComList.remove(dotComToTest);
                break;
            }                      
        }
        
        //Printing out the result
        System.out.println(result);
        
    }
    
    public void finishGame(){
        //Finishing the game.
        System.out.println("You sunk all the dot coms!");   
        //Printing out feedback based on performance.
        if(numGuesses<18){
            System.out.println("You took " + numGuesses + " guesses");
            System.out.println("Well, you sure are intelligent!");
        }else{
            System.out.println("You took " + numGuesses + " guesses");
            System.out.println("You're a dumb fuck.");       
        }    
    }

    // The main launcher class.    
    public static void main(String[] args){
        DotComBust game = new DotComBust();
        game.setUpGame();
        game.startPlaying();
    }
    
}
