package org.files;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/* Programmer Brian Kies */

public class PageOne extends Page
{
    String[] sentences = new String[5];
    Timer timer1, timer2;
    int y = firstPageFirstLine;
    Graphics scriptPanelGraphics;
    Graphics imagePanelGraphics;
    ScriptPanel scriptPanel = new ScriptPanel( );
    PageTwo pageTwo;
   
    public PageOne (Graphics g, ScriptPanel sp)
    {
        super(g);
    
        scriptPanelGraphics = g;
        scriptPanel = sp;
        
        sentences[0] = "It is difficult to grasp how our country has changed.";
        sentences[1] = "That the character of its leader no longer matters.";
        sentences[2] = "Nor the intelligence.";
        sentences[3] = "That the mouthpiece of a nation can be incapable of empathy?";
        sentences[4] = "But this should matter."; 
        
        g.setFont(scriptFont);
      
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
                        case 2:
                        case 3:
                        case 4:
                            timer1.setInitialDelay(2000);
                            timer1.start();
                            x = 25;
                            y += verticalSpacing;
                            break;
                        case 5:
                            new Reminder2 (2, y);
                            scriptPanel.whichPage = "PageTwo";
                            Reference.imagePanel.whichPage = "PageTwo";
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
    
    public PageOne (Graphics g)
    {
        imagePanelGraphics = g;
       
        ActionListener timerListener = new ActionListener() 
        {  
            public void actionPerformed(ActionEvent event) 
            {
                switch (imageCounter)
                {
                    case 0:
                        Reference.imagePanel.pictureFrame.setIcon(new ImageIcon(getClass().getResource("/org/resources/YellowPanel.jpg")));
                        timer2.stop();
                        break;
                }
            }        
        };
        
        timer2 = new Timer(100, timerListener);
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
                scriptPanel.repaint(0, 0, 1000, eraseLine - 20);
                scriptPanel.MainClock(scriptPanelGraphics);
                Reference.imagePanel.MainClock(imagePanelGraphics);
            }
        }
    }
}
