package org.files;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

/* Programmer Brian Kies */

public class ImagePanel extends JPanel
{
    Color backgroundColor = new Color (255, 255, 205);
    Color labelBorderColor = new Color (102, 102, 255);
    Color panelBorderColor = new Color (255, 255, 255);
    Color titleBackgroundColor = new Color (255, 255, 255);
    Font panelFont = new Font ("Times New Roman", Font.BOLD, 22);
    LineBorder imagePanelBorder = new LineBorder(panelBorderColor, 6, false);
    JLabel titleLbl = new JLabel ("LIVES LOST TOO SOON", SwingConstants.CENTER);
    JLabel counterLbl = new JLabel (" ", SwingConstants.CENTER);
    JLabel backgroundLbl = new JLabel();
    int count;
    int timerSpeed = 1000;
    int imageCounter = 0;
  
    PageOne pageOne;
    PageTwo pageTwo;
    PageThree pageThree;
    PageFour pageFour;
    PageFive pageFive;
    String whichPage = "PageOne";

    Image fraction;
    JLabel pictureFrame = new JLabel();
  
    int indexCount = 0;
    
    public ImagePanel ( )
    {
       setSize(500, 722);
       setBackground(backgroundColor);
       setFont(panelFont);
       setLocation(1000, 30);
       setBorder(imagePanelBorder);
       setLayout(null);
       setVisible(true);
      
       backgroundLbl.setSize(500, 722);
       backgroundLbl.setLocation(0, 0);
       backgroundLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/resources/OldCrinkledPaper.jpg")));
       backgroundLbl.setOpaque(true);
       add(backgroundLbl);
       
       titleLbl.setFont(panelFont);
       titleLbl.setSize(375, 50);
       titleLbl.setLocation(60, 75);
       titleLbl.setOpaque(true);
       titleLbl.setBackground(titleBackgroundColor);
       titleLbl.setForeground(new Color (102, 102, 255));
       titleLbl.setBorder(BorderFactory.createLineBorder(labelBorderColor, 4, false));
       titleLbl.hide();
       add(titleLbl);
       
       counterLbl.setFont(panelFont);
       counterLbl.setSize(205, 45);
       counterLbl.setLocation(145, 175);
       counterLbl.setOpaque(true);
       counterLbl.setBackground(Color.WHITE);
       counterLbl.setForeground(Color.BLACK);
       counterLbl.setBorder(BorderFactory.createLineBorder(labelBorderColor, 4, true));
       counterLbl.hide();
       add(counterLbl);
       
       pictureFrame.setSize(478, 402);
       pictureFrame.setLocation(11, 270);
       pictureFrame.setOpaque(true);
       pictureFrame.hide();
       pictureFrame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/resources/YellowPanel.jpg")));
    }
    
    @Override
    public void paint (Graphics g)
    {
        super.paint(g);
        DrawLine(g);
    }
   
    public void MainClock (Graphics g) 
    {
        switch (whichPage)
        {   
            case "PageOne":
                pageOne = new PageOne(g);
                pageOne.timer2.stop();
                break;
                
            case "PageTwo":
                pageTwo = new PageTwo(g);
                pageTwo.timer2.start();
                Reference.imagePanel.count = 1;
                Reference.imagePanel.timer1.start();
                break;
                
             case "PageThree":
                pageThree = new PageThree(g);
                pageThree.timer2.start();
                timer1.setDelay(5);
                Reference.imagePanel.count = 14712;
                Reference.imagePanel.timer1.start();    
                break;
                 
             case "PageFour":
                pageFour = new PageFour(g); 
                pageFour.timer2.start();
                timer1.setDelay(5);  
                Reference.imagePanel.count = 188409;
                Reference.imagePanel.timer1.start();
                break;
                 
            case "PageFive":
                pageFive = new PageFive(g);
                pageFive.timer2.start();
                timer1.setDelay(5);
                Reference.imagePanel.count = 507475;
                Reference.imagePanel.timer1.start();
                break;
        }                            
    }
                
    public void DrawLine(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        g2.setColor(new Color (102, 102, 250));
        g2.drawLine(0, 0, 0, 722);
        g2.drawLine(0, 722, 500, 722); 
        g2.drawLine(500, 722, 500, 0);
        g2.drawLine(500, 0, 0, 0);
    }
    
    ActionListener timerListener1 = new ActionListener() 
    {
        public void actionPerformed(ActionEvent event) 
        {
           String str;
         
           if (count <= 50)
           {
                str = Integer.toString(count);
                counterLbl.setText(str);
                count += 1; 
                timerSpeed -= 5;
                timer1.setDelay(timerSpeed);    
           }
           
           else if ( count <= 125)
           {
                str = Integer.toString(count);
                counterLbl.setText(str);
                count += 1; 
                timerSpeed -= 10;
                timer1.setDelay(timerSpeed);    
               
           }
           else if (count <= 500)
           {
                str = Integer.toString(count);
                counterLbl.setText(str);
                count += 5; 
                timerSpeed = 5;
                timer1.setDelay(timerSpeed);    
           }
           
           else if (count <= 5000)
           {
                str = Integer.toString(count);
                counterLbl.setText(str);
                count += 21; 
           }
           
           else if (count <= 49000)
           {
                str = Integer.toString(count);
                counterLbl.setText(str);
                count += 42;    
           }
           
           else if (count <= 150000)
           {
                str = Integer.toString(count);
                counterLbl.setText(str);
                count += 63;    
           }
           
           else if (count <= 250000)
           {
                str = Integer.toString(count);
                counterLbl.setText(str);
                count += 84;  
           }
           
           else if (count <= 350000)
           {
                str = Integer.toString(count);
                counterLbl.setText(str);
                count += 100;     
           }
           
           else if (count <= 525000)
           {
                str = Integer.toString(count);
                counterLbl.setText(str);
                count += 106; 
           }
       
           else if (count <= 850000)
           {
                str = Integer.toString(count);
                counterLbl.setText(str);
                count += 85;        
           }
           
           else if (count <= 1000000)
           {
                str = Integer.toString(count);
                counterLbl.setText(str);
                count += 66;        
           }
           
           else
           {
               timer1.stop();
               String countStr = "1,000,000 ...";
               counterLbl.setText(countStr);
           }            
        }                                 
    };             
    
    Timer timer1 = new Timer(timerSpeed, timerListener1);            
}
