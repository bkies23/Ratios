package org.files;

import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/* Programmer Brian Kies */

public class Page extends JPanel 
{
    final int firstLine = 50;
    final int firstPageFirstLine = 150;
    String tempStr;
    final int verticalSpacing = 50;
    int x = 25;
    Font scriptFont = new Font ("Monospaced", Font.BOLD, 22);
    char [][] sentence; 
    int sentenceCounter = 0;  
    int charCounter = 0;
    int globalSentenenceCounter = 0;
    int count = 1;
    int scriptTimerSpeed = 100;
    int imageTimerSpeed = 100;
    int imageCounter = 0;
    int counter = 1;
    Graphics g;
    
    public Page ( )
    {

    }

    public Page (Graphics g)
    {
        setG(g);
    }
     
    public Graphics getG() 
    {
        return g;
    }

    public void setG(Graphics g) 
    {
        this.g = g;
    }

    public int getFirstLine() 
    {
        return firstLine;
    }

    public int getVerticalSpacing() 
    {
        return verticalSpacing;
    }

    public Font getScriptFont() 
    {
        return scriptFont;
    }

    public int getSentenceCounter() 
    {
        return sentenceCounter;
    }
    
    // This method takes each line from the parameter SENTENCES array and converts it into a character array stored in SENTENCE[NumberOfLines].
    // This makes it possible for the timer to print each sentence's character across the screen.  
    public char [][] ConvertStringArrayToCharacterArray (String [] sentences)
    {  
        sentence = new char[sentences.length][];
        
        for( int line = 0; line < sentences.length; line++)
        {
            sentence[line] = sentences[line].toCharArray();
        }
       
        return sentence;
    }
}

