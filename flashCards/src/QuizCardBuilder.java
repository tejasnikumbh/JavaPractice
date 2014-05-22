import java.util.*;// For DataStructures like ArrayList
import java.awt.event.*; // For Event Handling
import java.awt.*; // For Event Handling
import javax.swing.*; // For GUI elements
import java.io.*; // For file IO

public class QuizCardBuilder{

    // Instance Variable
    private JFrame frame;
    private JTextArea question;
    private JTextArea answer;
    private ArrayList<QuizCard> cardList;

    // Main Launcher Method
    public static void main(String[] args){
        QuizCardBuilder applet = new QuizCardBuilder();
        applet.display();
    }
    
    // Building up the GUI
    public void display(){
    frame = new JFrame("Quiz Card Builder");
        JPanel mainPanel = new JPanel();
        Font bigFont = new Font("sanserif",Font.BOLD,24);
        
        question = new JTextArea(6,20);
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setFont(bigFont);
        
        JScrollPane qScroller = new JScrollPane(question);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        answer = new JTextArea(6,20);
        answer.setLineWrap(true);
        answer.setWrapStyleWord(true);
        answer.setFont(bigFont);
        
        JScrollPane aScroller = new JScrollPane(answer);
        aScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        aScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        JButton nextButton = new JButton("Next Card");
        cardList =  new ArrayList<QuizCard>();
        JLabel qLabel = new JLabel("Question : ");
        JLabel aLabel = new JLabel("Answer : ");
        
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        mainPanel.add(qLabel);
        mainPanel.add(qScroller);
        mainPanel.add(aLabel);
        mainPanel.add(aScroller);
        mainPanel.add(nextButton);
         
        nextButton.addActionListener(new NextCardListener());
        
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenu = new JMenuItem("New");
        JMenuItem saveMenu = new JMenuItem("Save");
        newMenu.addActionListener(new NewMenuListener());
        saveMenu.addActionListener(new SaveMenuListener());
        fileMenu.add(newMenu);
        fileMenu.add(saveMenu);
        menuBar.add(fileMenu);
        
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);
        
    }
    
    //Listener Inner Classes
    public class NextCardListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
        // Add current card to list and clear text areas.
        QuizCard card = new QuizCard(question.getText(),answer.getText());
        cardList.add(card);
        clearCard();
        }
    }
    
    // Clear contents of current text areas
    public void clearCard(){
        question.setText("");
        answer.setText("");
        question.requestFocus();
    }
    
    public class SaveMenuListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
        // Bring up a dialog box
        // Let the user name and save the set of cards
        // Saving current card
            QuizCard card = new QuizCard(question.getText(),answer.getText());
            cardList.add(card);
            clearCard();
        // Choosing file to save in
            JFileChooser fileSave = new JFileChooser();
            fileSave.showSaveDialog(frame);
            saveFile(fileSave.getSelectedFile());    
            
        }   
    }
    
    public class NewMenuListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
        // Clear out the card list and clear out text areas
            cardList.clear();
            clearCard();
        }        
    }
    
    // Subroutine to save the file
    public void saveFile(File file){
    // Iterate through the list of cards, and save each to a text file.
    // in a parseable way. Does the work for SaveMenuListener.
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(QuizCard card: cardList){
                writer.write(card.getQuestion() + "/");
                writer.write(card.getAnswer() + "\n");
            }   
            writer.close();
        }catch(Exception ex){
            System.out.println("Couldn't write to File");
            ex.printStackTrace();
        }
    }
    
}
