class Dog{

    String name;
    
    public static void main(String[] args){
        //Short can be bit extended in the method. But Long cant.So we can pass
        //short into a method that expects int, but not long.
        //This is because short is shorter than int and long longer than int.
        short k = 5;
        //Make a Dog object and access it
        Dog dog1 = new Dog();
        dog1.bark(k);
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
            dogs[x].bark(k);
            x = x+1;
        }
    }
    
    void bark(int k){
        System.out.println(k);
        System.out.println(name + " says Ruff!");
    }

}
