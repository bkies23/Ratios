package org.files;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/* Programmer Brian Kies */

public class PageFive extends Page 
{
    String[] sentences = new String[19];
    Timer timer1, timer2;
    int y = firstLine;
    ScriptPanel scriptPanel = new ScriptPanel();
    Graphics scriptPanelGraphics, imagePanelGraphics;
    
    public PageFive (Graphics g, ScriptPanel sp)
    {
        super(g);
        g.setFont(scriptFont);
        sentences[0] = "In early 2020, ";
        sentences[1] = " the president understood this virus to be deadly ";
        sentences[2] = "and highly contagious. ";
        sentences[3] = " He decided not to warn us or to take much ";
        sentences[4] = "action. ";
        sentences[5] = " Not because he feared Americans might panic as he later ";
        sentences[6] = "stated, ";
        sentences[7] = " he was concerned with its affect on his re-election.";
        sentences[8] = "To put it bluntly, ";
        sentences[9] =  " he was counting on it to go away by Easter.";
        sentences[10] = "This is the opposite of leadership when we needed it the most.";
        sentences[11] = "All citizens should be outraged by it.";
        sentences[12] = "It is frightening to grasp how our country has changed.";
        sentences[13] = "We must be careful of who we elect to lead this nation.";
        sentences[14] = "It is extremely dangerous to select someone who can put their ";
        sentences[15] = "own interests ahead of the American people.";
        sentences[16] = "After all, ";
        sentences[17] = " you never know, ";  
        sentences[18] = " a crisis may be around the corner.";
       
        sentence = ConvertStringArrayToCharacterArray (sentences);
       
        ActionListener timerListener1 = new ActionListener() 
        {  
            public void actionPerformed(ActionEvent event) 
            {
                tempStr = String.valueOf(sentence[sentenceCounter][charCounter]);
                g.drawString(tempStr, x, y);
                
                if ( charCounter == sentence[sentenceCounter].length - 1 )
                {
                    sentenceCounter++;
                    charCounter = 0;
            
                    timer1.stop();
                
                    switch (sentenceCounter)
                    {
                        case 1:
                        case 3:
                        case 5:
                        case 7:
                        case 9:
                        case 17:
                        case 18:
                            timer1.setInitialDelay(2000);
                            timer1.start();
                            break;
                        case 2:
                        case 4:
                        case 6:
                        case 15:
                            timer1.setInitialDelay(0);
                            timer1.start();
                            x = 25;
                            y += verticalSpacing;
                            break;
                        case 8:
                        case 10:
                        case 11:
                            timer1.setInitialDelay(2000);
                            timer1.start();
                            x = 25;
                            y += verticalSpacing;
                            break;
                        case 12:
                            g.setColor(Color.red);
                            timer1.setInitialDelay(2000);
                            timer1.start();
                            x = 25;
                            y += verticalSpacing;
                            break;
                        case 13:
                        case 14:
                        case 16:
                            g.setColor(Color.black);
                            timer1.setInitialDelay(2000);
                            timer1.start();
                            x = 25;
                            y += verticalSpacing;
                            break;
                    }   
                }
                else
                {
                    charCounter++;
                    x += 15;    
                }           
            }}; 
        
            timer1 = new Timer(100, timerListener1); 
    } 
    
     public PageFive (Graphics g)
     {
        imagePanelGraphics = g;
   
        ActionListener timerListener2 = new ActionListener() 
        {  
            public void actionPerformed(ActionEvent event) 
            {
                switch (counter)
                {
                    case 35:
                        Reference.imagePanel.pictureFrame.setIcon(new ImageIcon(getClass().getResource("/org/resources/CASES-2020-2021.jpg")));
                        imageCounter++;
                        counter++;
                        break;
                    case 275:
                        Reference.imagePanel.pictureFrame.setIcon(new ImageIcon(getClass().getResource("/org/resources/Vaccine.jpg")));
                        imageCounter++;
                        counter++;
                        break;
                     case 450:
                        Reference.imagePanel.pictureFrame.setIcon(new ImageIcon(getClass().getResource("/org/resources/Fauci.jpg")));
                        imageCounter++;
                        counter++;
                        break;
                    case 650:
                        Reference.imagePanel.pictureFrame.setIcon(new ImageIcon(getClass().getResource("/org/resources/FirstShot.jpg")));
                        imageCounter = 0;
                        counter++;
                        break;
                    case 725:
                        Reference.imagePanel.pictureFrame.setIcon(new ImageIcon(getClass().getResource("/org/resources/FirstUSShot.jpg")));
                        imageCounter = 0;
                        counter++;
                        break;
                    case 850:
                        Reference.imagePanel.pictureFrame.setIcon(new ImageIcon(getClass().getResource("/org/resources/Kirkland.jpg")));
                        imageCounter = 0;
                        counter++;
                        break;
                     case 990:
                        Reference.imagePanel.pictureFrame.setIcon(new ImageIcon(getClass().getResource("/org/resources/BlueSkySunset.jpg")));
                        imageCounter = 0;
                        counter++;
                        break;
                    default:
                        counter++;
                        break;        
                }
            }     
        };
        
         timer2 = new Timer(100, timerListener2); 
    }
}
