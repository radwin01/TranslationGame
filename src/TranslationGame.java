import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.HashMap; 
import java.util.Random;

/**
 * A French/English translation game with GUI components!
 * 
 * @author Yara Radwan
 * @version 2017-06-06
 */
public class TranslationGame
{
    // class constants
    private static final BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
    private static final String ERROR_IMAGE_UNAVAILABLE = "Error! Unable to display image.";
    private static final String SENTINEL_EXIT_VALUE = "EXIT";
    private static final int NUMBER_OF_PHRASES = 10;

    private static final int BUTTON_WIDTH = 80;
    private static final int BUTTON_HEIGHT = 20;

    private static final int FRAME_HEIGHT = 500;
    private static final int FRAME_WIDTH = 1000;

    private static final int SCORE_PANEL_HEIGHT = 100;
    private static final int SCORE_PANEL_WIDTH = 300;

    private static final int TEXT_FIELD_HEIGHT = 40;
    private static final int TEXT_FIELD_WIDTH = 550;

    // files
    private static final String ENGLISH_SENTENCES = "./englishSentences.txt";
    private static final String FRENCH_SENTENCES = "./frenchSentences.txt";

    // instance fields
    private ArrayList<String> englishSentences;
    private ArrayList<String> frenchSentences;
    private boolean promptEnglishOrFrench;

    // player data
    private HashMap<Integer, SentencePair> gamePhrases;
    private int integerKey;
    private Random randomizer;
    private String playerInput;
    private int correctEnglishSentences;
    private int correctFrenchSentences;
    private int correctSentences;
    private int phrasesPosed;

    // GUI instance fields
    private JFrame frame;   

    private JPanel introPanel;
    private JPanel playerPanel;

    private JLabel welcomeLabel;
    private JLabel instructionsLabel1;
    private JLabel instructionsLabel2;
    private JLabel instructionsLabel3;
    private JLabel instructionsLabel4;
    private JLabel scoreboardLabel;
    private JLabel englishCorrectLabel;
    private JLabel frenchCorrectLabel;
    private JLabel playerHighscoreLabel;
    private JLabel totalScoreLabel;
    private JLabel questionsAskedLabel;

    private ImageComponent[] image;

    // GUI class constants
    private static final String[] IMAGE_SOURCE = {"TGAME Images/apple.jpeg", "TGAME Images/avocado.jpg", 
            "TGAME Images/banana.jpeg", "TGAME Images/cherry.png",  
            "TGAME Images/mango.png", "TGAME Images/orange.jpg", "TGAME Images/pear.jpg",
            "TGAME Images/pineapple.jpg","TGAME Images/plum.jpg",  "TGAME Images/strawberry.jpg"};

    private static final String TITLE = "E-F Translation Game: FRUIT EDITION";

    /* constructors */
    /**
     * Constructs a game with default characteristics.
     */
    public TranslationGame()
    {
        // initialize GUI
        makeFrame();

        // initialize instance fields
        englishSentences = new ArrayList<String>();
        frenchSentences = new ArrayList<String>();

        // initialize GUI instance fields
        gamePhrases = new HashMap<Integer, SentencePair>();
        integerKey = 0;
        randomizer = new Random();
        playerInput = null;
        correctEnglishSentences = 0;
        correctFrenchSentences = 0;
        correctSentences = 0;
        phrasesPosed = 0;   
    } // end of constructor TranslationGame()

    /* mutators */
    /**
     * Sets the label used to welcome this user to this game.
     * 
     * @param newLabel label used to welcome user to game
     */
    public void setWelcomeLabel(String newLabel)
    {
        this.welcomeLabel.setText(newLabel);
    } // end of method setWelcomeLabel(String newLabel)

    /**
     * Sets the label used to display the number of correct French to English translations.
     *
     * @param newLabel the current number of correct French translations
     */
    public void setFrenchCorrectLabel(int newLabel)
    {
        this.frenchCorrectLabel.setText("Successful French-English Translations: " + newLabel);
    } // end of method setFrenchCorrectLabel(int newLabel)

    /**
     * Sets the label used to display the number of correct English-French translations.
     * 
     * @param newLabel the current number of correct English translations
     */
    public void setEnglishCorrectLabel(int newLabel)
    {
        this.englishCorrectLabel.setText("Successful English-French Translations: "  + newLabel);
    } // end of method setEnglishCorrectLabel(int newLabel)

    /**
     * Sets the label used to display the total number of phrases asked to user.
     * 
     * @param newLabel the number of phrases posed
     */
    public void setQuestionsAskedLabel(int newLabel)
    {
        this.questionsAskedLabel.setText("Phrases Posed: "+ newLabel);
    } // end of method setQuestionsAskedLabel(int newLabel)

    /**
     * Sets the label used to display the total number of correct translations.
     * 
     * @param newLabel the number of correct translations
     */
    public void setTotalScoreLabel(int newLabel)
    {
        this.totalScoreLabel.setText("Total Score: "+ newLabel);
    } // end of method setTotalLabel(int newLabel)

    /* private methods */
    /*
     * Creates the panel used to display all information(instructions, scores) to user.
     */
    private void makePlayerPanel()
    {
        // GUI labels
        instructionsLabel1 = new JLabel("INSTRUCTIONS: Translate the following ten");
        instructionsLabel2 = new JLabel("fruits into the correct language(E-F/F-E). ");
        instructionsLabel3 = new JLabel("Pictures for each will be provided as extra help.");
        instructionsLabel4 = new JLabel("Enter 'EXIT' at anytime to exit game.");
        scoreboardLabel = new JLabel("------------SCOREBOARD-------------");
        englishCorrectLabel = new JLabel("Successful English-French Translations: " + correctEnglishSentences);
        frenchCorrectLabel = new JLabel("Successful French-English Translations: " + correctFrenchSentences);
        questionsAskedLabel = new JLabel("Phrases Posed: " + phrasesPosed);
        totalScoreLabel = new JLabel("Total Score: " + correctSentences);

        // set the color font of the labels
        instructionsLabel1.setForeground(Color.MAGENTA);
        instructionsLabel2.setForeground(Color.MAGENTA);
        instructionsLabel3.setForeground(Color.MAGENTA);
        instructionsLabel4.setForeground(Color.RED);
        scoreboardLabel.setForeground(Color.GREEN);
        frenchCorrectLabel.setForeground(Color.BLUE);
        englishCorrectLabel.setForeground(Color.BLUE);
        totalScoreLabel.setForeground(Color.BLUE);
        questionsAskedLabel.setForeground(Color.BLUE);

        playerPanel = new JPanel();
        playerPanel.setBackground(Color.WHITE);

        // add labels to the panel
        playerPanel.setPreferredSize(new Dimension(SCORE_PANEL_WIDTH, SCORE_PANEL_HEIGHT));
        playerPanel.add(instructionsLabel1, BorderLayout.LINE_END);
        playerPanel.add(instructionsLabel2, BorderLayout.LINE_END);
        playerPanel.add(instructionsLabel3, BorderLayout.LINE_END);
        playerPanel.add(instructionsLabel4, BorderLayout.LINE_END);
        playerPanel.add(scoreboardLabel, BorderLayout.LINE_END);
        playerPanel.add(frenchCorrectLabel, BorderLayout.LINE_END);
        playerPanel.add(englishCorrectLabel, BorderLayout.LINE_END);
        playerPanel.add(totalScoreLabel, BorderLayout.LINE_END);
        playerPanel.add(questionsAskedLabel, BorderLayout.LINE_END);
    } // end of method makeScorePanel()

    /*
     * Creates the panel used to initially welcome user to game.
     */
    private void makeIntroPanel()
    {
        final String INTRO_LABEL = "FRENCH-ENGLISH TRANSLATION GAME: Fruit Edition! Welcome!";

        welcomeLabel = new JLabel(INTRO_LABEL);
        welcomeLabel.setForeground(Color.BLACK);

        introPanel = new JPanel();
        introPanel.setBackground(Color.CYAN);
        introPanel.add(welcomeLabel);
    } // end of method makeCommunicationPanel()

    /*
     * Creates the application frame and its content.
     */
    private void makeFrame()
    {
        frame = new JFrame(TITLE); 
        frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        frame.getContentPane().setBackground(Color.WHITE);  

        makeIntroPanel();
        makePlayerPanel();

        // display a default image for the frame
        loadImageData();
        if (image[0] != null)
        {
            frame.add(image[0], BorderLayout.CENTER);
        } // end of if (image[0] != null)

        // add panels to the frame
        frame.add(introPanel, BorderLayout.PAGE_START);
        frame.add(playerPanel, BorderLayout.LINE_END);

        frame.pack();
        frame.setVisible(true);
    } // end of method makeFrame()

    /*
     * Loads the English and French phrases from their files and assigns them to the HashMap. 
     */
    private void loadSentences() throws IOException
    {
        // establish a connection to the text file(for English and French phrases).
        BufferedReader englishFile = new BufferedReader(new FileReader(ENGLISH_SENTENCES));
        BufferedReader frenchFile = new BufferedReader(new FileReader(FRENCH_SENTENCES));

        // read the first English phrase
        String lineOfText = englishFile.readLine();

        // read the next sentence(s) until all of them are read
        while (lineOfText != null)
        {
            englishSentences.add(lineOfText);
            lineOfText = englishFile.readLine();
        } // end of while (lineOfText != null)

        // read the first French phrase
        lineOfText = frenchFile.readLine();

        // read the next sentence(s) until all of them are read
        while (lineOfText != null)
        {
            frenchSentences.add(lineOfText);
            lineOfText = frenchFile.readLine();
        } // end of while (lineOfText != null)

        // finish reading all phrases from the file
        englishFile.close();
        frenchFile.close(); 

        // store each SentencePair value to the HashMap with its correspondent key
        for (int integerKey = 0; integerKey < englishSentences.size(); integerKey++)
        {
            gamePhrases.put(integerKey, new SentencePair(englishSentences.get(integerKey), 
                    frenchSentences.get(integerKey)));
        } // end of for (int integerKey = 0; integerKey < englishSentences.size(); integerKey++)
    } // end of method loadSentences() throws IOException

    /**
     * Generates a valid integer key.
     */
    public int generateValidIntegerKey() 
    {
        boolean validKey = false;

        // ensures that if sentence pair of corresponding integer key has already been prompted to the user, it will not be used again throughout the game
        do
        {
            integerKey = randomizer.nextInt(gamePhrases.size());
            validKey = !gamePhrases.get(integerKey).getIsCorrectlyTranslated();
        }
        while (!validKey);

        return integerKey;
    } // end of method generateValidIntegerKey() 

    /*
     * Loads image data for later use.
     */
    private void loadImageData()
    {
        image = new ImageComponent[IMAGE_SOURCE.length];

        for (int imageNumber = 0; imageNumber < IMAGE_SOURCE.length; imageNumber++)
        {
            image[imageNumber] = new ImageComponent(IMAGE_SOURCE[imageNumber]);
        } // end of for (int imageNumber = 0; imageNumber < NUMBER_OF_IMAGES; imageNumber++)
    } // end of method loadImageData()

    /*
     * Removes the current image and replaces it with one that corresponds with the given phrase(in English/French). 
     */
    private void replaceImage(int newImage)
    {
        BorderLayout layout = (BorderLayout)(frame.getContentPane()).getLayout();
        Component component = layout.getLayoutComponent(BorderLayout.CENTER);

        // removes default image currently displayed    
        frame.setVisible(false);
        frame.remove(component);

        //redraw the frame after adding the image
        frame.add(image[newImage], BorderLayout.CENTER);
        frame.setVisible(true);
    } // end of method replaceImage(int newImage)

    /**
     * Indicates whether a French or English sentence should be displayed.
     * 
     * @return true if French, else English
     */
    private boolean englishOrFrench()
    {
        int randomizedKey = randomizer.nextInt(2);
        return (randomizedKey == 0);
    } // end of method englishOrFrench()

    /*
     * Prompts the sentences to be translated and exits after ten sentences have been prompted.
     */
    private void promptSentences() throws IOException
    { 
        do
        {
            promptEnglishOrFrench = englishOrFrench();

            generateValidIntegerKey();

            // display corresponding image with its integer key after default image
            replaceImage(integerKey);

            // prompt phrases from the HashMap  
            String englishSentence = gamePhrases.get(integerKey).getEnglish();
            String frenchSentence = gamePhrases.get(integerKey).getFrench();

            if (promptEnglishOrFrench)
            {   
                // display a French phrase to be translated into English
                playerInput = JOptionPane.showInputDialog(null, frenchSentence, "INPUT", JOptionPane.QUESTION_MESSAGE);
                checkTranslation(promptEnglishOrFrench, integerKey);
            }
            else
            {
                // display an English phrase to be translated into French
                playerInput = JOptionPane.showInputDialog(null, englishSentence, "INPUT", JOptionPane.QUESTION_MESSAGE);
                checkTranslation(promptEnglishOrFrench, integerKey);
            } // end of if (englishOrFrench)
        } while (phrasesPosed != NUMBER_OF_PHRASES);

        // end game after 10 phrases have been prompted
        gameOver();
    } // end of method promptSentences()

    /*
     * Prompts phrase to be translated by user and checks if user's translation is correct.
     */
    private void checkTranslation(boolean promptEnglishOrFrench, int integerKey)
    {
        final String CORRECT_MESSAGE = "Correct! Nice!";
        final String INCORRECT_MESSAGE = "Incorrect!";
        final String EXIT_MESSAGE = "FINAL SCORE: " + correctSentences + "/10 - " + ((correctSentences * 100) / 10) + "%. Goodbye!";

        String englishSentence = gamePhrases.get(integerKey).getEnglish();
        String frenchSentence = gamePhrases.get(integerKey).getFrench();

        /* although the method is 'setIsCorrectlyTranslated', my game will not repeat the same question twice regardless if the user has  
         * correctly translated it or not. Thus, 'setIsCorrectlyTranslated' will be true for all situations.
         */
        if (promptEnglishOrFrench)
        {
            if (playerInput.toLowerCase().trim().equals(englishSentence.toLowerCase().trim()))
            {
                JOptionPane.showMessageDialog(null, CORRECT_MESSAGE, TITLE, 
                    JOptionPane.INFORMATION_MESSAGE);

                // update scores  
                correctEnglishSentences++;
                setEnglishCorrectLabel(correctEnglishSentences);
                correctSentences++;
                setTotalScoreLabel(correctSentences);
                phrasesPosed++;
                setQuestionsAskedLabel(phrasesPosed);

                // make sure the certain phrase pair won't be used again in the duration of the game
                gamePhrases.get(integerKey).setIsCorrectlyTranslated(true);
            } // end of if (playerInput.toLowerCase().trim().equals(englishSentence.toLowerCase().trim()))
            else if (playerInput.equals(SENTINEL_EXIT_VALUE))
            {
                JOptionPane.showMessageDialog(null, EXIT_MESSAGE, TITLE,
                    JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            } // end of else if (playerInput.equals(SENTINEL_EXIT_VALUE))
            else
            {
                JOptionPane.showMessageDialog(null, INCORRECT_MESSAGE, TITLE,
                    JOptionPane.INFORMATION_MESSAGE);

                // update score 
                phrasesPosed++;
                setQuestionsAskedLabel(phrasesPosed);

                // make sure the certain phrase pair won't be used again in the duration of the game
                gamePhrases.get(integerKey).setIsCorrectlyTranslated(true);
            } // end of else
        } // end of if (promptEnglishOrFrench)
        else
        {
            if (playerInput.toLowerCase().trim().equals(frenchSentence.toLowerCase().trim()))
            {
                JOptionPane.showMessageDialog(null, CORRECT_MESSAGE, TITLE, 
                    JOptionPane.INFORMATION_MESSAGE);

                // update scores  
                correctFrenchSentences++;
                setFrenchCorrectLabel(correctFrenchSentences);
                correctSentences++;
                setTotalScoreLabel(correctSentences);
                phrasesPosed++;
                setQuestionsAskedLabel(phrasesPosed);

                // make sure the certain phrase pair won't be used again in the duration of the game
                gamePhrases.get(integerKey).setIsCorrectlyTranslated(true);
            } // end of if (playerInput.toLowerCase().trim().equals(frenchSentence.toLowerCase().trim()))
            else if (playerInput.equals(SENTINEL_EXIT_VALUE))
            {
                JOptionPane.showMessageDialog(null, EXIT_MESSAGE, TITLE,
                    JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            } // end of else if (playerInput.equals(SENTINEL_EXIT_VALUE))
            else
            {
                JOptionPane.showMessageDialog(null, INCORRECT_MESSAGE, TITLE,
                    JOptionPane.INFORMATION_MESSAGE);

                // update score 
                phrasesPosed++;
                setQuestionsAskedLabel(phrasesPosed);

                // make sure the certain phrase pair won't be used again in the duration of the game
                gamePhrases.get(integerKey).setIsCorrectlyTranslated(true);
            } // end of else
        } // end of else
    } // end of method checkTranslation(boolean promptEnglishOrFrench, int integerKey)

    /*
     * Prompts translation game to finish and exit.
     */
    private void gameOver()
    {
        final String GAME_OVER_MESSAGE = "Game over! Your final score is: " + correctSentences + "/10 - " + ((correctSentences * 100) /10) + "%. Thanks for playing!";

        JOptionPane.showMessageDialog(null, GAME_OVER_MESSAGE, TITLE,
            JOptionPane.INFORMATION_MESSAGE);

        System.exit(0);
    } // end of method gameOver()

    /*
     * Runs the translation game.
     */
    private void playGame() throws IOException
    {
        loadSentences();
        loadImageData();
        promptSentences();
        gameOver();
    } // end of method run()

    /* private classes */
    private class ImageComponent extends Component
    {
        // class fields
        private static final int NO_PROBLEMS_ENCOUNTERED = 0;
        private static final int PROBLEMS_ENCOUNTERED = -1;

        // instance fields
        private BufferedImage bufferedImage;
        private int status;

        /* constructors */
        /*
         * Creates a component with a drawn image. If the image was
         * drawn, the component's status is NO_PROBLEMS_ENCOUNTERED;
         * otherwise, PROBLEMS_ENCOUNTERED.
         */
        public ImageComponent(String fileName)
        {
            bufferedImage = null;
            status = NO_PROBLEMS_ENCOUNTERED;
            try
            {
                bufferedImage = ImageIO.read(new File(fileName));
            }
            catch (IOException exception)
            {
                status = PROBLEMS_ENCOUNTERED;
            } // end of catch (IOException exception)
        } // end of method ImageComponent(String fileName)

        /* accessors */
        /*
         * Returns the status of this component: NO_PROBLEMS_ENCOUNTERED
         * or PROBLEMS_ENCOUNTERED.
         */
        public int getStatus()
        {
            return status;
        } // end of method getStatus()

        /* mutators */
        /*
         * Called when the contents of the component should be painted, such as
         * when the component is first being shown or is damaged and in need of
         * repair.
         */
        public void paint(Graphics graphicsContext)
        {
            super.paint(graphicsContext);
            int x = (this.getWidth() - bufferedImage.getWidth(null)) / 2;
            int y = (this.getHeight() - bufferedImage.getHeight(null)) / 2;
            graphicsContext.drawImage(bufferedImage, x, y, null);
        } // end of method paint(Graphics graphicsContext)
    } // end of class ImageComponent extends Component

    /**
     * Tests and manipulates objects of this class.
     * 
     * @param argument not used
     */
    public static void main(String[] argument) throws IOException
    {
        TranslationGame game = new TranslationGame();
        game.playGame();
    } // end of method
} // end of class TranslationGame
