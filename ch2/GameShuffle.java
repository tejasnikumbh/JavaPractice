class GameShuffle{

  Player p1 = new Player();
  Player p2 = new Player();
  Player p3 = new Player();      
  
  void gameStart(){
    while(true){
  
        int r = (int)(Math.random()*10);
  
        p1.guessNum();
        p2.guessNum();
        p3.guessNum();
    
        int p1Guess = p1.guess;
        int p2Guess = p2.guess;
        int p3Guess = p3.guess;  
    
        boolean p1Wins = p1Guess == r;
        boolean p2Wins = p2Guess == r;
        boolean p3Wins = p3Guess == r;
    
        if(p1Wins || p2Wins || p2Wins){
            System.out.println("We have a winner(s)!");
            System.out.println("The number was " + r);
            System.out.print("Has player 1 won the game ? " + p1Wins + "\n");
            System.out.print("Has player 2 won the game ? " + p2Wins + "\n");
            System.out.print("Has player 3 won the game ? " + p3Wins + "\n");
            System.out.println("Game Over!");
            break;
        }else{
            System.out.println("Nobody Could guess the correct number. It was " + r);
            System.out.println("Try again!");
        }
    
    }//End of Game Loop
    
  }//End of gameStart method
  
}//End of GameShuffle Class
