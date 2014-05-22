import javax.swing.*; // For all gui methods
import java.awt.*;//For event handling
import java.awt.event.*; // For event handling
import java.util.*; // For utility DS like ArrayList
import java.io.*; //For File I/O

public class QuizCardPlayer{

    // Instance Variables
    private ArrayList<QuizCard> cardList;
    private JFrame frame;
    private JTextArea display;
    private JTextArea answer;
    private QuizCard currentCard;
    private int currentCardIndex;
    private JButton nextButton;
    private boolean isShowAnswer;
    
    
    
    // Main Launcher Method
    public static void main(String[] args){
        QuizCardPlayer app = new QuizCardPlayer();
        app.run();
    }
    
    // Method that sets up the Player App Gui
    public void run(){
    
        frame = new JFrame("Quiz Card Player");
        JPanel mainPanel = new JPanel();
        Font bigFont = new Font("sanserif",Font.BOLD,24);
        
        display = new JTextArea(6,20);
        display.setFont(bigFont);
        display.setLineWrap(true);
        display.setEditable(false);
        
        JScrollPane qScroller = new JScrollPane(display);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        nextButton = new JButton("Show Question");
        mainPanel.add(qScroller);
        mainPanel.add(nextButton);
        nextButton.addActionListener(new NextCardListener());
        
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadSet = new JMenuItem("Load Card Set");
        loadSet.addActionListener(new OpenMenuListener());
        fileMenu.add(loadSet);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);
        
        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640,500);
        frame.setVisible(true);
           
    }
    
    
    public class NextCardListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
        // If this is a question, show the answer, otherwise next Question
        // set a flag(IVAR) for whether this is a question or an answer.
            if(isShowAnswer){
                display.setText(currentCard.getAnswer());
                nextButton.setText("Next Question");
                isShowAnswer = false;
            }else{
                if(currentCardIndex<cardList.size()){
                    showNextCard();
                }else{
                    display.setText("That was the last card");
                    nextButton.setEnabled(false);
                }       
            }
        }
    }   
    
    public class OpenMenuListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            // Bring up a file dialog box
            // Let the user choose a card set to open.
            JFileChooser fileOpen = new JFileChooser();
            fileOpen.showOpenDialog(frame);
            loadFile(fileOpen.getSelectedFile());
        }
    }
    
    private void loadFile(File file){
    // Must build an arrayList of cards, by reading from the file.
    // Takes help of helper method MakeCard by passing read string from file
        cardList = new ArrayList<QuizCard>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while((line = reader.readLine())!=null){
                makeCard(line);
            }
        }catch(Exception ex){
            System.out.println("Cannot Load File");
            ex.printStackTrace();
        }
        // Fire up and show the first card
        showNextCard();
    }
    
    private void makeCard(String lineToParse){
    // Each Line from file is passed from loadFile. 
    // This method builds a quiz card and adds it to card arraylist.   
        String[] lines = lineToParse.split("/");
        String question = lines[0];
        String answer = lines[1]; 
        QuizCard card  = new QuizCard(question,answer);
        cardList.add(card);
        System.out.println("Made a card");
    }
    
    private void showNextCard(){
        currentCard = cardList.get(currentCardIndex);
        currentCardIndex++;
        display.setText(currentCard.getQuestion());
        nextButton.setText("Show Answer");
        isShowAnswer = true;
    }

}
