package org.files;

import java.awt.*;
import javax.swing.*;

/* Programmer Brian Kies */

public class ScriptPanel extends JPanel 
{
    JLabel cover = new JLabel();
    JLabel pictureFrame = new JLabel();
    PageOne pageOne;
    PageTwo pageTwo;
    PageThree pageThree;
    PageFour pageFour;
    PageFive pageFive;
    String whichPage = "PageOne";
  
    public ScriptPanel ( )
    {
        setSize(1000, 722);
        setLayout(null);
        setOpaque(true);
        setLocation(0, 30);
        setVisible(true);
        cover.setBounds(0, 0, 1000, 722);
        cover.setIcon(new ImageIcon(getClass().getResource("/org/resources/OldCrinkledPaper.jpg")));
        cover.setOpaque(true);
        add(cover);
     }
    
    public void DrawLine(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        g2.setColor(new Color (102, 102, 250));  
        g2.drawLine(0, 0, 1000, 0);
        g2.drawLine(0, 722, 1000, 722);
        g2.drawLine(0, 0, 0, 722);
    }
     
     @Override
     public void paint(Graphics g)
     {
         super.paint(g);
         DrawLine(g); 
     }
     
    public void MainClock (Graphics g)
    {
        switch (whichPage)
        {
            case "PageOne":
                pageOne = new PageOne(g, this);
                pageOne.timer1.setInitialDelay(2000);
                pageOne.timer1.start();
                break;
                
            case "PageTwo":
                pageTwo = new PageTwo(g, this);
                pageTwo.timer1.setInitialDelay(2000);
                pageTwo.timer1.start();
                break;
                
            case "PageThree":
                pageThree = new PageThree(g, this);
                pageThree.timer1.setInitialDelay(2000);
                pageThree.timer1.start();
                break;
                
            case "PageFour":
               pageFour = new PageFour(g, this);
               pageFour.timer1.setInitialDelay(2000);
               pageFour.timer1.start();
               break;  
                
             case "PageFive":
               pageFive = new PageFive(g, this);
               pageFive.timer1.setInitialDelay(2000);
               pageFive.timer1.start();
               break;    
        } 
    }    
}
