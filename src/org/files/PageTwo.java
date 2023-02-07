package org.files;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/* Programmer Brian Kies */

public class PageTwo extends Page 
{
    String[] sentences = new String[12];
    int y = firstLine;
    ScriptPanel scriptPanel = new ScriptPanel();
    Timer timer1, timer2;
    Graphics scriptPanelGraphics, imagePanelGraphics;
  
    PageThree pageThree;
   
    public PageTwo (Graphics g, ScriptPanel sp)
    {
        super(g);
        g.setFont(scriptFont);
        scriptPanelGraphics = g;
        scriptPanel = sp;
        
        sentences[0] = "In early 2020, ";
        sentences[1] = " as a new Coronavirus began to circle the globe, ";
        sentences[2] = "a few countries acted immediately and with great urgency.";
        sentences[3] = "While our president kept insisting the virus was under control, ";
        sentences[4] = "even mocked wearing masks against the guidance of his advisors, ";
        sentences[5] = "South Korea was testing, contact tracing, and quarantining most ";
        sentences[6] = "of its citizens. ";
        sentences[7] = " Their leadership understood if you do not stay ";
        sentences[8] = "ahead of the virus, "; 
        sentences[9] = " the virus will stay far ahead of you.";
        sentences[10] = "At the cost of thousands upon thousands of precious lives.";
        sentences[11] = "Leadership can be crucial.";
       
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
                        case 7:
                        case 9:
                            timer1.setInitialDelay(2000);
                            timer1.start();
                            break;
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 10:
                            timer1.setInitialDelay(2000);
                            timer1.start();
                            x = 25;
                            y += verticalSpacing;
                            break;
                        case 6:
                        case 8:
                            timer1.setInitialDelay(0);
                            timer1.start();
                            x = 25;
                            y += verticalSpacing;
                            break;
                        case 11:
                            timer1.setInitialDelay(3000);
                            timer1.start();
                            x = 25;
                            y += verticalSpacing + (verticalSpacing / 2);
                            break;
                        case 12:
                            new Reminder2 (2, y);
                            scriptPanel.whichPage = "PageThree";
                            Reference.imagePanel.whichPage = "PageThree";
                            new Reminder1 (6, y - verticalSpacing);
                            break;         
                        }
                    }       
                else
                {
                    charCounter++;
                    x += 15;    
                }           
            }
        }; 
        
        timer1 = new Timer(scriptTimerSpeed, timerListener1); 
    }
    
     public PageTwo (Graphics g)
     {
        imagePanelGraphics = g;
      
        ActionListener timerListener2 = new ActionListener() 
        { 
            public void actionPerformed(ActionEvent event) 
            {
                switch (counter)
                {
                    case 35:
                        Reference.imagePanel.pictureFrame.setIcon(new ImageIcon(getClass().getResource("/org/resources/Kirkland.jpg")));
                        counter++;
                        break;
                    case 260:
                        Reference.imagePanel.pictureFrame.setIcon(new ImageIcon(getClass().getResource("/org/resources/GrandmaAtWindow.jpg")));
                        counter++;
                        break;
                     case 440:
                        Reference.imagePanel.pictureFrame.setIcon(new ImageIcon(getClass().getResource("/org/resources/CB1.jpg")));
                        counter++;
                        break;
                    case 530:
                        Reference.imagePanel.pictureFrame.setIcon(new ImageIcon(getClass().getResource("/org/resources/CB2.jpg")));
                        counter++;
                        break;
                    case 660:
                        Reference.imagePanel.pictureFrame.setIcon(new ImageIcon(getClass().getResource("/org/resources/CovidTesting.jpg")));
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
