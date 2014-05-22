public class QuizCard{

    // Instance Variables
    private String question;
    private String answer;
    
    // Constructor
    public QuizCard(String question,String answer){
        this.question = question;
        this.answer = answer;
    }
    
    // Getter Setter Encapsulator Methods
    public String getQuestion(){
        return question;
    }
    
    public String getAnswer(){
        return answer;
    }
    
    public void setQuestion(String question){
        this.question = question;
    }
    
    public void setAnswer(String answer){
        this.answer = answer;
    }
}
