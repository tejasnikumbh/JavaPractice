class Player{

    int guess = 0;
    
    void guessNum(){
    
        guess = (int)(Math.random()*10);
        System.out.println("Player 1 Guessed : " + guess);
        
    }

}
