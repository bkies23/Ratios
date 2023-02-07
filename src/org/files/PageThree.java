package org.files;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.Hashtable;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/* Programmer Brian Kies */

public class PageThree extends Page 
{
    String[] sentences = new String[9];
    Timer timer1, timer2;
    int y = firstLine;
    ScriptPanel scriptPanel = new ScriptPanel();
    Graphics scriptPanelGraphics, imagePanelGraphics;
    
    public PageThree (Graphics g, ScriptPanel sp)
    {
        super(g);
        g.setFont(scriptFont);
        scriptPanel = sp;
        scriptPanelGraphics = g;
      
        String[] firstFraction = { "           " + Reference.currentStats[0] + "   Deaths           ", "                     =",
                                   "           X  Deaths           ", "   " + Reference.currentStats[1] + "   South Koreans              " 
                                   + Reference.currentStats[2] + "   Americans"};
        String[] secondFraction = { "X (" + Reference.currentStats[1] + " )  =  " + Reference.currentStats[0] + " (" + Reference.currentStats[2] + ")" };
        String[] thirdFraction = {"X     =  ", "    " + Reference.currentStats[0] + " ( " + Reference.currentStats[2] + " )    ", Reference.currentStats[1]};
        String[] fourthFraction = {"X   = ", "   " + Reference.formattedCaseTotal +     " Deaths"};
      
        sentences[0] = "Here is a simple math lesson on ratios and proportions: ";
        sentences[1] = "Set South Korea's Covid-19 death total over their population ";
        sentences[2] = "and let it equal X over the United States population or: ";
        sentences[3] = " ";
        sentences[4] = "Now cross-multiply to solve for X where X yields the U.S. death ";
        sentences[5] = "total had we followed South Korea's lead or:  ";
        sentences[6] = " ";
        sentences[7] = " ";
        sentences[8] = " "; 
    
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
                            timer1.setInitialDelay(2000);
                            timer1.start();
                            x = 25;
                            y += verticalSpacing;
                            break;
                        case 2:
                        case 5:
                            timer1.setInitialDelay(0);
                            timer1.start();
                            x = 25;
                            y += verticalSpacing;
                            break;
                        case 3:
                            y += verticalSpacing;
                            timer1.setInitialDelay(2000);
                            timer1.start();
                            new Reminder2 (1, firstFraction);
                            break;  
                        case 4:
                            timer1.setInitialDelay(3000);
                            timer1.start();
                            x = 25;
                            y += verticalSpacing + (verticalSpacing / 2);
                            break;
                        case 6:
                            y += verticalSpacing;
                            timer1.setInitialDelay(3000);
                            timer1.start();
                            new Reminder2 (1, secondFraction);
                            break;
                        case 7:
                            y += verticalSpacing;
                            timer1.setInitialDelay(3000);
                            timer1.start();
                            new Reminder2 (1, thirdFraction);
                            break;
                        case 8:
                            y += verticalSpacing + (verticalSpacing / 2);
                            timer1.setInitialDelay(3000);
                            timer1.start();
                            new Reminder2 (1, fourthFraction);
                            break;
                        case 9:
                            new Reminder3 (2, y);
                            scriptPanel.whichPage = "PageFour";
                            Reference.imagePanel.whichPage = "PageFour";
                            new Reminder1 (6, y - verticalSpacing);
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
    
    public PageThree (Graphics g)
    {
        imagePanelGraphics = g;
   
        ActionListener timerListener2 = new ActionListener() 
        {  
            public void actionPerformed(ActionEvent event) 
            {
                switch (counter)
                {
                    case 37:
                        Reference.imagePanel.pictureFrame.setIcon(new ImageIcon(getClass().getResource("/org/resources/NYCWave.jpg")));
                        counter++;
                        break;
                    case 215:
                        Reference.imagePanel.pictureFrame.setIcon(new ImageIcon(getClass().getResource("/org/resources/EmptyStreet.jpg")));
                        counter++;
                        break;
                     case 375:
                        Reference.imagePanel.pictureFrame.setIcon(new ImageIcon(getClass().getResource("/org/resources/WeMissYou.jpg")));
                        counter++;
                        break;
                    case 415:
                        Reference.imagePanel.pictureFrame.setIcon(new ImageIcon(getClass().getResource("/org/resources/Closed.jpg")));
                        counter++;
                        break;
                    default:
                        counter++;
                        break;        
                }
            }        
        };
        
        timer2 = new Timer(imageTimerSpeed, timerListener2);
     }
                
    public class Reminder1 
    {
        java.util.Timer timer;
        int eraseLine = 0;
        
        public Reminder1(int seconds, int y) 
        {
            eraseLine = y;
            timer = new java.util.Timer();
            timer.schedule(new RemindTask(), seconds * 1000);
	}

        class RemindTask extends TimerTask 
        {
            public void run() 
            {
                scriptPanel.repaint(0, eraseLine, 1000, 700);
            }
        }
    }
    
    public class Reminder2 
    {
        java.util.Timer timer = new java.util.Timer();
        String [] fraction;
 
        public Reminder2(int seconds, String[] fractionArray) 
        {
            fraction = fractionArray;
            timer.schedule(new RemindTask(), seconds * 1000);
	}

        class RemindTask extends TimerTask 
        {
            public void run() 
            {
                DrawFraction(y, fraction, g, sentenceCounter);
            }
        }
    }
    
     public class Reminder3 
    {
        java.util.Timer timer = new java.util.Timer();
        int eraseLine = y;
    
        public Reminder3( )
        {
            
        }
        
        public Reminder3(int seconds, int y) 
        {
            timer.schedule(new RemindTask(), seconds * 1000);
	}

        class RemindTask extends TimerTask 
        {
            public void run() 
            {
                scriptPanel.repaint(0, 0, 1000, eraseLine - 20);
                scriptPanel.MainClock(scriptPanelGraphics);
                Reference.imagePanel.MainClock(imagePanelGraphics);  
            }
        }
    }
      
    public void DrawFraction (int verticalPos, String[] fractionArray, Graphics g, int sentenceCounter)
    {
        Font font1 = new Font(Font.SERIF, Font.BOLD, 24);
        Hashtable<TextAttribute, Object> map1 = new Hashtable<>();
        map1.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        font1 = font1.deriveFont(map1);
        Font font2 = new Font(Font.SERIF, Font.BOLD, 24);
        
        switch (sentenceCounter)
        {
            case 3:
                g.setFont(font1); 
                g.drawString(fractionArray[0], 200, verticalPos);
                g.setFont(font2);
                g.drawString(fractionArray[1], 400, verticalPos);
                g.setFont(font1);
                g.drawString(fractionArray[2], 575, verticalPos);
                g.setFont(font2);
                g.drawString(fractionArray[3], 200, verticalPos + 25);
                break;
            case 6:
                g.setFont(font2);
                g.drawString(fractionArray[0], 320, verticalPos);
                break;    
            case 7:
                g.setFont(font2);
                g.drawString(fractionArray[0], 350, verticalPos);
                g.setFont(font1);
                g.drawString(fractionArray[1], 425, verticalPos);
                g.setFont(font2);
                g.drawString(fractionArray[2], 475, verticalPos + 25);
                break; 
            case 8:
                g.setFont(font2);
                g.drawString(fractionArray[0], 400, verticalPos);
                g.drawString(fractionArray[1], 460, verticalPos);
                break; 
        }                                        
    }  
}
