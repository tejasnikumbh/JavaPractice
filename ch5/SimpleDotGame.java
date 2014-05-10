import java.util.ArrayList;

class SimpleDotGame{

    //Some Instance Variables. 
    //Note that default values assigned are the apt ones
    private ArrayList<String> cellLocation;
    private int hits;
    
    //Some Methods
    String checkGuess(String guessStr){
         
        String result = "miss";
        
        for(String cell : cellLocation){
            if(cell.equals(guessStr)){
                cellLocation.remove(cell);
                hits++;
                result = "hit";
                break;
            }
        }
        
        if(hits == 3)
            result = "kill";
            
        return result;    
    }
    
    void setCellLocation(ArrayList<String> locs){
        cellLocation = locs;
    }
    
}
