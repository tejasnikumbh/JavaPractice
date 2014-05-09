/* File : Song.java                                                            *                                         
 * --------------------------------------------------------------------------- *
 * Simple Song class to demonstrate the concept of Encapsulation viz getter and*
 * setter methodology. Encapsulation allows us to better protect the instance  *
 * variables in a sense, allowing only certain ways in which we can change them*
 * and only certain variables being open to access.                            *
 * --------------------------------------------------------------------------- */
 
class Song{

    private String title;
    private String artist;
    private String album;
    
    //Getters : Return the value of the private instance variables.
    String getTitle(){
        return title;
    }
    
    String getArtist(){
        return artist;
    }
    
    String getAlbum(){
        return album;
    }    
    
    //Setters : Allows us to set the private instance variables.
    void setTitle(String title){
        this.title = title;
    }
    
    void setArtist(String artist){
        this.artist = artist;
    }
    
    void setAlbum(String album){
        this.album = album;
    }    
    

}
