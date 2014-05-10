/* This class is basically the Game Board Class */

import java.util.ArrayList;

class DotCom{

    //Private Instance Varaibles

    private String name;
    private ArrayList<String> cellLocation;
    private int hits;
    
    //Public Methods
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setCellLocation(ArrayList<String> locs){
        cellLocation = locs;
    }
    
    public String checkGuess(String guess){
    
        String result = "You Miss!";
        
        for(String cell : cellLocation){
    
            if(guess.equals(cell)){
                cellLocation.remove(cell);
                result = "You Hit!";
                hits++;
                break;           
            }
                        
        }
        
        if(hits == 3)
            result = "You Kill!";
       
        return result;
        
    }    
   
}
