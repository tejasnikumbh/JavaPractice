import java.io.*;
import java.util.*;

class GameHelper{

    private static final String alphabet = "abcdefg";
    private int gridLen = 7;
    private int gridSize = 49;
    private int[] grid = new int[gridSize];
    private int comCount = 0;
   
    public String getUserInput(String prompt){
        String inputLine = null;
        System.out.println(prompt + "");
        try{
            BufferedReader is = new BufferedReader(
            new InputStreamReader(System.in));
            inputLine = is.readLine();
        }catch(IOException e){
            System.out.println("IOException : " + e);
        }
        return inputLine;
    }    

    public ArrayList<String> placeDotCom(int comSize){
        ArrayList<String> alphacells = new ArrayList<String>();
        String[] alphacoords = new String[comSize];
        String temp = null;
        int[] coords = new int[comSize];
        int attempt = 0;
        int location = 0;
        boolean success = false;
        
        comCount++;
        int incr = 1;
        if((comCount%2==1))
            incr = gridLen;
        
        while(!success & attempt++ < 200){
       
            location = (int)(Math.random()*gridSize);
            int x = 0;
            success = true;
            while(success && x<comSize){
                if(grid[location] == 0){
                    coords[x++] = location;
                    location += incr;
                    if(location>=gridSize)
                        success = false;
                    if(x>0 && (location%gridLen == 0))
                        success = false;
                }else{
                    success = false;
                }//End of if else block
            }//End of inner while loop
        }//End of outer while loop
        
        int x = 0;
        int row = 0;
        int column = 0;
        while(x<comSize){
            grid[coords[x]] = 1;
            row = (int) (coords[x]/gridLen);
            column = coords[x] % gridLen;
            temp = String.valueOf(alphabet.charAt(column));
            alphacells.add(temp.concat(Integer.toString(row)));
            x++;
        }
        return alphacells;    
    }    
}
