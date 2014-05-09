class Dog{

    String name;
    
    public static void main(String[] args){
        //Make a Dog object and access it
        Dog dog1 = new Dog();
        dog1.bark();
        dog1.name = "Bart";
            
        //Now make a Dog array
        Dog[] dogs = new Dog[3];
            
        //And put some Dogs in it
        dogs[0] = new Dog();
        dogs[1] = new Dog();
        dogs[2] = dog1;
            
        //Now access the Dogs using array references
        dogs[0].name = "Fred";
        dogs[1].name = "Tom";
               
    
        //Hmm.. What is Dog Two's name?
        System.out.println("Last Dog's name is " + dogs[2].name);
        
        //Loop through the array and tell all Dogs to bark    
        int x = 0;
        while(x<dogs.length){
            dogs[x].bark();
            x = x+1;
        }
    }
    
    void bark(){
        System.out.println(name + " says Ruff!");
    }

}
