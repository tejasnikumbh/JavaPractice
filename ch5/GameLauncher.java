import java.util.ArrayList;
class GameLauncher{

    public static void main(String[] args){
        // Initiatializing the Game instance.
        SimpleDotGame game = new SimpleDotGame();    
        
        // Player guesses
        int numGuesses = 0;
        
        //Setting a random combination to be the game entry.
        int start = (int)(Math.random()*5);
        
        //Initializing a new array for cell locations.
        ArrayList<String> locs = new ArrayList<String>();
        
        locs.add(start + "");
        locs.add((start + 1) + "");
        locs.add((start + 2) + "");
        
        //Setting the location to be the grid location
        game.setCellLocation(locs);
        
        //Initializing object for user Input
        GuessHelper help = new GuessHelper();
        
        //Taking user input for guessing. Running in loop until game ends
        boolean isAlive = true;
        while(isAlive){
            
            numGuesses++;
            
            String guess = help.getUserInput("Enter your guess (a number) : ");
            String result = game.checkGuess(guess);
            System.out.println(result);
            if(result == "kill"){
                System.out.println("You won the game!");
                System.out.println("You completed the game in " +
                                    numGuesses + 
                                   " guesses!");
                isAlive = false;
            }
            
            
        }
    }
    
}
