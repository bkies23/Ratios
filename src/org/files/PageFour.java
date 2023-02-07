package org.files;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/* Programmer Brian Kies */

public class PageFour extends Page
{
    String[] sentences = new String[9];
    Timer timer1, timer2;
    int y = firstLine;
    ScriptPanel scriptPanel = new ScriptPanel();
    Graphics scriptPanelGraphics, imagePanelGraphics;

    public PageFour(Graphics g, ScriptPanel sp)
    {
        super(g);
        g.setFont(scriptFont);
  
        scriptPanel = sp;
        scriptPanelGraphics = g;
        
        sentences[0] = "It would not be fair to compare death rates between countries. ";
        sentences[1] = "The overall health of a country's citizens can have a great ";
        sentences[2] = "bearing on those figures. ";
        sentences[3] = " Although the U.S. may have had more ";
        sentences[4] = "than " + Reference.formattedCaseTotal + " deaths, ";
        sentences[5] = " it surely would have been hundreds of ";
        sentences[6] = "thousands less than the one million plus we have experienced. ";
        sentences[7] = "This is a disturbing figure. ";
        sentences[8] = "Leadership can be crucial.";
        
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
                        case 3:
                        case 5:
                            timer1.setInitialDelay(2000);
                            timer1.start();
                            break;
                        case 2:
                        case 4:
                        case 6:
                            timer1.setInitialDelay(0);
                            timer1.start();
                            x = 25;
                            y += verticalSpacing;
                            break;
                        case 7:
                        case 8:
                            timer1.setInitialDelay(3000);
                            timer1.start();
                            x = 25;
                            y += verticalSpacing + (verticalSpacing / 2);
                            break;
                        case 9:
                            new Reminder2 (2, y);
                            scriptPanel.whichPage = "PageFive";
                            Reference.imagePanel.whichPage = "PageFive";
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
    
     public PageFour (Graphics g)
     {
        imagePanelGraphics = g;
   
        ActionListener timerListener2 = new ActionListener() 
        {  
            public void actionPerformed(ActionEvent event) 
            {
                switch (counter)
                {
                    case 37:
                        Reference.imagePanel.pictureFrame.setIcon(new ImageIcon(getClass().getResource("/org/resources/RedCountry.jpg")));
                        imageCounter++;
                        counter++;
                        break;
                     case 190:
                        Reference.imagePanel.pictureFrame.setIcon(new ImageIcon(getClass().getResource("/org/resources/StepWorker.jpg")));
                        imageCounter++;
                        counter++;
                        break;
                    case 390:
                        Reference.imagePanel.pictureFrame.setIcon(new ImageIcon(getClass().getResource("/org/resources/MSH.jpg")));
                        imageCounter++;
                        counter++;
                        break;
                    case 440:
                        Reference.imagePanel.pictureFrame.setIcon(new ImageIcon(getClass().getResource("/org/resources/MB.jpg")));
                        imageCounter++;
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
              
    @Override
     public void paint(Graphics g)
     {
         super.paint(g);
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
                scriptPanel.repaint(0, eraseLine, 1000, 65);
            }
        }
    }
   
    public class Reminder2
    {
        java.util.Timer timer;
        int eraseLine = y;
        
        public Reminder2(int seconds, int y) 
        {
            timer = new java.util.Timer();
            timer.schedule(new RemindTask(), seconds * 1000);
	}

        class RemindTask extends TimerTask 
        {
            public void run() 
            {
                scriptPanel.repaint(0, 0, 1000, eraseLine - 50);
                scriptPanel.MainClock(scriptPanelGraphics);
                Reference.imagePanel.MainClock(imagePanelGraphics);
            }
        }
    }
}
