/**
 * Stores the sentence pairs in French and English.
 * 
 * @author Yara Radwan
 * @version 2017-06-06
 */
public class SentencePair
{
    // instance fields
    private String frenchSentence;
    private String englishSentence;
    boolean isCorrectlyTranslated;

    /* constructors */
    /**
     * Constructs a sentence pair with default characteristics.
     */
    public SentencePair()
    {
        // initialize the instance variables
        frenchSentence = "";
        englishSentence = "";
        isCorrectlyTranslated = false;
    } // end of method SentencePair()

    /**
     * Constructs a sentence pair with specified characteristics.
     */
    public SentencePair(String frenchSentence, String englishSentence)
    {
        this.frenchSentence = frenchSentence;
        this.englishSentence = englishSentence;
        isCorrectlyTranslated = false;
    } // end of method SentencePair(String frenchSentence, String englishSentence)

    /* accessors */
    /**
     * Indicates whether this sentence pair is correctly translated.
     * 
     * @Return true if correctly translated, else false
     */
    public boolean getIsCorrectlyTranslated()
    {
        return this.isCorrectlyTranslated;
    }  // end of method getIsCorrectlyTranslated()

    /**
     * Returns the French sentence of this sentence pair.
     * 
     * @Return the French sentence
     */
    public String getFrench()
    {
        return this.frenchSentence;
    } // end of method getfrenchSentence()

    /**
     * Returns the englishSentence sentence of this sentence pair.
     * 
     * @Return the englishSentence sentence
     */
    public String getEnglish()
    {
        return this.englishSentence;
    } // end of method getEnglish()

    /* mutators */
    /**
     * Sets the English sentence of this sentence pair.
     * 
     * @param englishSentence the English sentence
     */
    public void setEnglishSentence(String englishSentence)
    {
        this.englishSentence = englishSentence;
    } // end of method setEnglishSentence(String englishSentence)

    /**
     * Sets the French sentence of this sentence pair.
     * 
     * @param frenchSentence the frenchSentence sentence
     */
    public void setFrenchSentence(String frenchSentence)
    {
        this.frenchSentence = frenchSentence;
    } // end of method setFrenchSentence(String frenchSentence)

    /**
     * Indicates whether this sentence pair is correctly translated.
     * 
     * @param isCorrectlyTranslated true if correctly translated, else false
     */
    public void setIsCorrectlyTranslated(boolean isCorrectlyTranslated)
    {
        this.isCorrectlyTranslated = isCorrectlyTranslated;
    } // end of method setIsCorrectlyTranslated(boolean isCorrectlyTranslated)
} // end of class SentencePair 