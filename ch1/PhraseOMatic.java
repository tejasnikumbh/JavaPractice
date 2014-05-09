class PhraseOMatic{
    public static void main(String[] args){
    
        //Declaring the three sets of words
        String[] pets = {"Cats","Dogs","Hamsters"};
        String[] colors = {"Red","Green","Blue"};
        String[] langs = {"Java","Javascript","C++","Ruby","Python"};
        
        //Find out how many words are there in each list
        int l1 = pets.length;
        int l2 = colors.length;
        int l3 = langs.length;
        
        //Generate three random numbers
        int r1 = (int) (Math.random()*l1);
        int r2 = (int) (Math.random()*l2);
        int r3 = (int) (Math.random()*l3);
        
        //Constructing a random phrase
        String randomPhrase = pets[r1] + " " + colors[r2] + " " + langs[r3];
        
        //Dispaying the random phrase
        System.out.println(randomPhrase);
    }
}
