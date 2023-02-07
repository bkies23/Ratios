package org.files;

import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/* Programmer Brian Kies */

public class MainFrame extends JWindow
{
    Color imagePanelBackground = new Color (255, 255, 205);
    Font font = new Font ("Monospaced", Font.BOLD, 22);
    Point location;
    DecimalFormat df = new DecimalFormat("#.##");
 
    // IMAGE PANEL AND STATISTIC VARIABLES ARE CREATED UNDER CLASS REFERENCE FOR GLOBAL ACCESSIBILITY 
    // IMAGE PANEL HAS A COUNTER TO DISPLAY LIVES LOST AND A COUNTER TO DISPLAY CHRONOLOGICAL PICTURES OF PANDEMIC (IN PICTURE FRAME)
    
    // ScriptPanel has timer to display text one letter at a time
    ScriptPanel scriptPanel = new ScriptPanel ();
    Graphics scriptPanelGraphics, imagePanelGraphics;
   
    public MainFrame()
    {
       initComponents();
       setLayout(null);
    }
    
    public void Initialize() throws IOException
    {
        getContentPane().setBackground(new Color(205, 205, 205));
        // WorldMeters website is used to web mine the up-to-date death total for
        // South Korea along with South Korea and U.S. up-to-date populations 
        String skCasesURL = "https://www.worldometers.info/coronavirus/country/south-korea/";
        String skPopURL = "https://www.worldometers.info/world-population/south-korea-population/";
        String usPopURL = "https://www.worldometers.info/world-population/us-population/";
        
        scriptPanelGraphics = scriptPanel.getGraphics();
        // web mine to store up-to-date stats of South Korea and U.S. populations and South Korea Covid-19 death total
        // in String array currentStats - currentStats will be used to draw proportion on Cover but and then used elsewhere in program
        Reference.currentStats = GetCurrentStats (skCasesURL, skPopURL, usPopURL);
        // method draws proporion on Cover using currentStats data
        DrawCoverFractionLbl(Reference.currentStats);
        Reference.imagePanel.pictureFrame.setVisible(true);
        Reference.imagePanel.titleLbl.setVisible(true);
        Reference.imagePanel.counterLbl.setVisible(true);
        Reference.imagePanel.backgroundLbl.add(Reference.imagePanel.pictureFrame);
        Reference.imagePanel.backgroundLbl.add(Reference.imagePanel.titleLbl);
        Reference.imagePanel.backgroundLbl.add(Reference.imagePanel.counterLbl);
     
        imagePanelGraphics = Reference.imagePanel.getGraphics();
        CoverPanel.setLocation(5, 33);
    }
    
    // this method web mines and returns array with three strings: South Korea's current death count from Covid-19,
    // South Korea current population, and U.S. current population
    public String [] GetCurrentStats (String skCasesURL, String skPopURL, String usPopURL) throws IOException
    {
        // uses Jsoup to retrieve Worldmeters website up-to-date stats for South Korea's 
        // current Covid-19 deaths along with South Korea and U.S. populations  
        Document doc;
        String tempStr;
        String [] stats = new String[3];
        // use NumberFormat class to add appropriate commas
        NumberFormat myFormat = NumberFormat.getInstance();
        // this will also round numbers 3 decimal places
        myFormat.setGroupingUsed(true);
        int counter = 0;
        
        doc = Jsoup.connect(skCasesURL).get();
        
        Elements links = doc.getElementsByClass("maincounter-number");
        
        for (Element link : links)
        {
            counter++;
            if (counter == 2)
                stats[0] = link.text();
        }
      
        tempStr = stats[0].replaceAll(",", "");
        Reference.currentNumericalStats[0] = Integer.parseInt(tempStr);
        
        doc = Jsoup.connect(skPopURL).get();
        Elements skPop = doc.select("strong").select("strong");
        stats[1] = skPop.get(1).text();
        
        tempStr = stats[1].replaceAll(",", "");
        Reference.currentNumericalStats[1] = Integer.parseInt(tempStr);
        
        doc = Jsoup.connect(usPopURL).get();
        Elements usPop = doc.select("strong").select("strong");
        stats[2] = usPop.get(1).text();
        
        tempStr = stats[2].replaceAll(",", "");
        Reference.currentNumericalStats[2] = Integer.parseInt(tempStr);
        
        Reference.unformattedCaseTotal = (Reference.currentNumericalStats[0] * Reference.currentNumericalStats[2]) / Reference.currentNumericalStats[1];
        Reference.formattedCaseTotal = myFormat.format(Reference.unformattedCaseTotal);
        
        double doubleDeathTotal  = Reference.unformattedCaseTotal * .0261;
        long longDeathTotal = Double.valueOf(doubleDeathTotal).longValue();
        Reference.formattedDeathTotal = myFormat.format(longDeathTotal);
        
        return stats;
    }
    
    public void DrawCoverFractionLbl (String [] currentStats)
    {
        JskDeathsLbl.setLocation(new Point(860, 240));
        JXLbl.setLocation(new Point(1170, 265));
    //    JskDeathsLbl.setLocation(new Point(860, 256));
    //    JXLbl.setLocation(new Point(1170, 255));
        JLabel tempCasesLbl = JskDeathsLbl;
        JskDeathsLbl.setText(currentStats[0]);
        
        // sets location 
        DrawFractionLines(tempCasesLbl);
        
        JskPopLbl.setText(currentStats[1]);
        JusPopLbl.setText(currentStats[2]);
    }
    
    public void DrawFractionLines (JLabel tempStatLbl)
    {
        location = tempStatLbl.getLocation();
    }
    
    public Graphics GetImagePanelGraphics ()
    {
         return Reference.imagePanel.getGraphics();
    }
     
    public void AddPanels()
    {
        // add ScriptPanel and ImagePanel to MainFrame
        add(scriptPanel);
        add(Reference.imagePanel);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CoverPanel = new javax.swing.JPanel();
        coverPanelLbl = new javax.swing.JLabel();
        JDividerLbl = new javax.swing.JLabel();
        JskDeathsLbl = new javax.swing.JLabel();
        JskPopLbl = new javax.swing.JLabel();
        JEqualsLbl = new javax.swing.JLabel();
        JXLbl = new javax.swing.JLabel();
        JusPopLbl = new javax.swing.JLabel();
        JTitleBarPnl = new javax.swing.JPanel();
        JCloseLbl = new javax.swing.JLabel();
        JTitleLbl = new javax.swing.JLabel();
        JControlPnl = new javax.swing.JPanel();
        JStartBtn = new javax.swing.JButton();
        jPauseBtn = new javax.swing.JButton();
        jContinueBtn = new javax.swing.JButton();
        JPageTwoBtn = new javax.swing.JButton();
        JPageThreeBtn = new javax.swing.JButton();
        JPageFourBtn = new javax.swing.JButton();
        JPageFiveBtn = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(1500, 845));
        setPreferredSize(new java.awt.Dimension(1500, 845));

        CoverPanel.setBackground(new java.awt.Color(255, 255, 220));
        CoverPanel.setPreferredSize(new java.awt.Dimension(1490, 718));

        coverPanelLbl.setBackground(new java.awt.Color(255, 255, 205));
        coverPanelLbl.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        coverPanelLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        coverPanelLbl.setText("A SIMPLE  MATH LESSON ON RATIOS & PROPORTIONS");

        JDividerLbl.setBackground(new java.awt.Color(153, 153, 153));
        JDividerLbl.setText(" ");
        JDividerLbl.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        JskDeathsLbl.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        JskDeathsLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JskDeathsLbl.setLabelFor(CoverPanel);
        JskDeathsLbl.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        JskPopLbl.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        JskPopLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JskPopLbl.setLabelFor(CoverPanel);
        JskPopLbl.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        JEqualsLbl.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JEqualsLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JEqualsLbl.setText("=");

        JXLbl.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        JXLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JXLbl.setLabelFor(CoverPanel);
        JXLbl.setText("X");
        JXLbl.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        JXLbl.setMaximumSize(new java.awt.Dimension(200, 30));
        JXLbl.setMinimumSize(new java.awt.Dimension(200, 30));
        JXLbl.setPreferredSize(new java.awt.Dimension(200, 30));

        JusPopLbl.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        JusPopLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JusPopLbl.setLabelFor(CoverPanel);
        JusPopLbl.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout CoverPanelLayout = new javax.swing.GroupLayout(CoverPanel);
        CoverPanel.setLayout(CoverPanelLayout);
        CoverPanelLayout.setHorizontalGroup(
            CoverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CoverPanelLayout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(coverPanelLbl)
                .addGap(100, 100, 100)
                .addComponent(JDividerLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(CoverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JskPopLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(CoverPanelLayout.createSequentialGroup()
                        .addComponent(JskDeathsLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(JEqualsLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(58, 58, 58)
                .addGroup(CoverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JXLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JusPopLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        CoverPanelLayout.setVerticalGroup(
            CoverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CoverPanelLayout.createSequentialGroup()
                .addGroup(CoverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CoverPanelLayout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addComponent(JXLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JusPopLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CoverPanelLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(JDividerLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CoverPanelLayout.createSequentialGroup()
                        .addGap(262, 262, 262)
                        .addComponent(JskDeathsLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JskPopLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CoverPanelLayout.createSequentialGroup()
                        .addGap(287, 287, 287)
                        .addComponent(coverPanelLbl))
                    .addGroup(CoverPanelLayout.createSequentialGroup()
                        .addGap(279, 279, 279)
                        .addComponent(JEqualsLbl)))
                .addContainerGap(100, Short.MAX_VALUE))
        );

        JTitleBarPnl.setBackground(new java.awt.Color(10, 50, 200));
        JTitleBarPnl.setPreferredSize(new java.awt.Dimension(1500, 30));

        JCloseLbl.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        JCloseLbl.setForeground(new java.awt.Color(255, 255, 255));
        JCloseLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JCloseLbl.setText("X");
        JCloseLbl.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        JCloseLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JCloseLblMouseClicked(evt);
            }
        });

        JTitleLbl.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        JTitleLbl.setForeground(new java.awt.Color(255, 255, 255));
        JTitleLbl.setText("A SIMPLE MATH LESSON ON RATIOS & PROPORTIONS");

        javax.swing.GroupLayout JTitleBarPnlLayout = new javax.swing.GroupLayout(JTitleBarPnl);
        JTitleBarPnl.setLayout(JTitleBarPnlLayout);
        JTitleBarPnlLayout.setHorizontalGroup(
            JTitleBarPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JTitleBarPnlLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(JTitleLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JCloseLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );
        JTitleBarPnlLayout.setVerticalGroup(
            JTitleBarPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JTitleBarPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JTitleBarPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTitleLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JCloseLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(754, 754, 754))
        );

        JControlPnl.setBackground(new java.awt.Color(10, 50, 200));
        JControlPnl.setPreferredSize(new java.awt.Dimension(1490, 95));

        JStartBtn.setText("Start");
        JStartBtn.setMaximumSize(new java.awt.Dimension(75, 25));
        JStartBtn.setMinimumSize(new java.awt.Dimension(75, 25));
        JStartBtn.setPreferredSize(new java.awt.Dimension(75, 25));
        JStartBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JStartBtnActionPerformed(evt);
            }
        });

        jPauseBtn.setText("Pause");
        jPauseBtn.setMaximumSize(new java.awt.Dimension(80, 25));
        jPauseBtn.setMinimumSize(new java.awt.Dimension(80, 25));
        jPauseBtn.setPreferredSize(new java.awt.Dimension(80, 25));
        jPauseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPauseBtnActionPerformed(evt);
            }
        });

        jContinueBtn.setText("Continue");
        jContinueBtn.setMaximumSize(new java.awt.Dimension(80, 25));
        jContinueBtn.setMinimumSize(new java.awt.Dimension(80, 25));
        jContinueBtn.setPreferredSize(new java.awt.Dimension(80, 25));
        jContinueBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jContinueBtnActionPerformed(evt);
            }
        });

        JPageTwoBtn.setText("Page 2");
        JPageTwoBtn.setPreferredSize(new java.awt.Dimension(70, 25));
        JPageTwoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JPageTwoBtnActionPerformed(evt);
            }
        });

        JPageThreeBtn.setText("Page 3");
        JPageThreeBtn.setPreferredSize(new java.awt.Dimension(70, 25));
        JPageThreeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JPageThreeBtnActionPerformed(evt);
            }
        });

        JPageFourBtn.setText("Page 4");
        JPageFourBtn.setPreferredSize(new java.awt.Dimension(70, 25));
        JPageFourBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JPageFourBtnActionPerformed(evt);
            }
        });

        JPageFiveBtn.setText("Page 5");
        JPageFiveBtn.setMaximumSize(new java.awt.Dimension(70, 25));
        JPageFiveBtn.setMinimumSize(new java.awt.Dimension(70, 25));
        JPageFiveBtn.setPreferredSize(new java.awt.Dimension(70, 25));
        JPageFiveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JPageFiveBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JControlPnlLayout = new javax.swing.GroupLayout(JControlPnl);
        JControlPnl.setLayout(JControlPnlLayout);
        JControlPnlLayout.setHorizontalGroup(
            JControlPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JControlPnlLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(JStartBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(162, 162, 162)
                .addComponent(jPauseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(172, 172, 172)
                .addComponent(jContinueBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JPageTwoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(JPageThreeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JPageFourBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JPageFiveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );
        JControlPnlLayout.setVerticalGroup(
            JControlPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JControlPnlLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(JControlPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JStartBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPauseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jContinueBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JPageTwoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JPageThreeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JPageFourBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JPageFiveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JTitleBarPnl, javax.swing.GroupLayout.DEFAULT_SIZE, 1490, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(CoverPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(JControlPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JTitleBarPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(CoverPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JControlPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JStartBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JStartBtnActionPerformed
      
            if (CoverPanel.isVisible())
                CoverPanel.hide();
            else
                ResetPage();
            
            scriptPanel.whichPage = "PageOne";
            scriptPanel.MainClock(scriptPanelGraphics);
            Reference.imagePanel.whichPage = "PageOne";
            Reference.imagePanel.MainClock(imagePanelGraphics);
    }//GEN-LAST:event_JStartBtnActionPerformed

    private void JCloseLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JCloseLblMouseClicked
        
        System.exit(0);
    }//GEN-LAST:event_JCloseLblMouseClicked

    public void ResetPage ( )
    {
        scriptPanel.repaint(0, 0, 1000, 700);
        
        switch (scriptPanel.whichPage)
        {
            case "PageOne":
                Reference.imagePanel.pictureFrame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/resources/YellowPanel.jpg")));
                Reference.imagePanel.counterLbl.setText(" ");
                Reference.imagePanel.pageOne.timer2.stop();
                Reference.imagePanel.timer1.stop();
                scriptPanel.pageOne.timer1.stop();
                break;
                
            case "PageTwo":
                Reference.imagePanel.pictureFrame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/resources/YellowPanel.jpg")));
                Reference.imagePanel.counterLbl.setText(" ");
                Reference.imagePanel.pageTwo.timer2.stop();
                Reference.imagePanel.timer1.stop();
                scriptPanel.pageTwo.timer1.stop();
                break;
                
            case "PageThree":
                Reference.imagePanel.pictureFrame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/resources/YellowPanel.jpg")));
                Reference.imagePanel.counterLbl.setText(" ");
                Reference.imagePanel.pageThree.timer2.stop();
                Reference.imagePanel.timer1.stop();
                scriptPanel.pageThree.timer1.stop();
                break;
                
            case "PageFour":
                Reference.imagePanel.pictureFrame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/resources/YellowPanel.jpg")));
                Reference.imagePanel.counterLbl.setText(" ");
                Reference.imagePanel.pageFour.timer2.stop();
                Reference.imagePanel.timer1.stop();
                scriptPanel.pageFour.timer1.stop();
                break;
                
             case "PageFive":
                Reference.imagePanel.pictureFrame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/resources/YellowPanel.jpg")));
                Reference.imagePanel.counterLbl.setText(" ");
                Reference.imagePanel.pageFive.timer2.stop();
                Reference.imagePanel.timer1.stop();
                scriptPanel.pageFive.timer1.stop();
                break; 
        }    
    }
    
    private void JPageTwoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JPageTwoBtnActionPerformed
        
        if (CoverPanel.isVisible())
            CoverPanel.hide(); 
        else
            ResetPage();
        
        scriptPanel.whichPage = "PageTwo";
        scriptPanel.MainClock(scriptPanelGraphics);
        Reference.imagePanel.whichPage = "PageTwo";
        Reference.imagePanel.MainClock(imagePanelGraphics);
    }//GEN-LAST:event_JPageTwoBtnActionPerformed

    private void JPageFourBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JPageFourBtnActionPerformed
       
        if (CoverPanel.isVisible())
            CoverPanel.hide();
        else
            ResetPage();
        
        scriptPanel.whichPage = "PageFour";
        scriptPanel.MainClock(scriptPanelGraphics);
        Reference.imagePanel.whichPage = "PageFour";
        Reference.imagePanel.count = 132531;
        Reference.imagePanel.MainClock(imagePanelGraphics);   
    }//GEN-LAST:event_JPageFourBtnActionPerformed

    private void JPageThreeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JPageThreeBtnActionPerformed
        
        if (CoverPanel.isVisible())
            CoverPanel.hide();
        else
            ResetPage();
        
        scriptPanel.whichPage = "PageThree";
        scriptPanel.MainClock(scriptPanelGraphics);
        Reference.imagePanel.whichPage = "PageThree";
        Reference.imagePanel.count = 30671;
        Reference.imagePanel.MainClock(imagePanelGraphics);
    }//GEN-LAST:event_JPageThreeBtnActionPerformed

    private void JPageFiveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JPageFiveBtnActionPerformed
       
        if (CoverPanel.isVisible())
            CoverPanel.hide();
        else
            ResetPage();
      
        scriptPanel.whichPage = "PageFive";
        scriptPanel.MainClock(scriptPanelGraphics);
        Reference.imagePanel.whichPage = "PageFive";
        Reference.imagePanel.count = 558321;
        Reference.imagePanel.MainClock(imagePanelGraphics);
    }//GEN-LAST:event_JPageFiveBtnActionPerformed

    private void jPauseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPauseBtnActionPerformed
        
        Reference.imagePanel.timer1.stop();
        
        switch (scriptPanel.whichPage)
        {
            case "PageOne":
                if (scriptPanel.pageOne == null)
                    break;
                else
                    scriptPanel.pageOne.timer1.stop();
                    break;
            case "PageTwo":
                scriptPanel.pageTwo.timer1.stop();
                Reference.imagePanel.pageTwo.timer2.stop();
                break;
            case "PageThree":
                scriptPanel.pageThree.timer1.stop();
                Reference.imagePanel.pageThree.timer2.stop();
                break;
            case "PageFour":
                scriptPanel.pageFour.timer1.stop();
                Reference.imagePanel.pageFour.timer2.stop();
                break;
            case "PageFive":
                scriptPanel.pageFive.timer1.stop();
                Reference.imagePanel.pageFive.timer2.stop();
                break;           
        }                             
    }//GEN-LAST:event_jPauseBtnActionPerformed

    private void jContinueBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jContinueBtnActionPerformed
        
        switch (scriptPanel.whichPage)
        {
            case "PageOne":
                scriptPanel.pageOne.timer1.start();
                break;
            case "PageTwo":
                scriptPanel.pageTwo.timer1.start();
                Reference.imagePanel.timer1.start();
                Reference.imagePanel.pageTwo.counter -= 15;
                Reference.imagePanel.pageTwo.timer2.start();
                break;
            case "PageThree":
                scriptPanel.pageThree.timer1.start();
                Reference.imagePanel.timer1.start();
                Reference.imagePanel.pageThree.counter -= 15;
                Reference.imagePanel.pageThree.timer2.start();
                break;
            case "PageFour":
                scriptPanel.pageFour.timer1.start();
                Reference.imagePanel.timer1.start(); 
                Reference.imagePanel.pageFour.counter -= 15;
                Reference.imagePanel.pageFour.timer2.start();
                break;
            case "PageFive":
                scriptPanel.pageFive.timer1.start();
                Reference.imagePanel.timer1.start();  
                Reference.imagePanel.pageFive.counter -= 15;  
                Reference.imagePanel.pageFive.timer2.start();
                break;
        }
    }//GEN-LAST:event_jContinueBtnActionPerformed

    class UnderlinedLabel extends JLabel 
    {
        public UnderlinedLabel() 
        {
            this("");
        }
    
        public UnderlinedLabel(String text) 
        {
            super(text);
        }
    }
    
    public void paint (Graphics g)
    {
        Point firstNumLocation = null;
        Point secondNumLocation = null;
        
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3.0f));
        firstNumLocation = JskDeathsLbl.getLocation();
        g2.drawLine(firstNumLocation.x, firstNumLocation.y + 75, firstNumLocation.x + 200, firstNumLocation.y + 75);
        secondNumLocation = JXLbl.getLocation();
        g2.drawLine(secondNumLocation.x, firstNumLocation.y + 75, secondNumLocation.x + 200, firstNumLocation.y + 75);    
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        MainFrame mf = new MainFrame();
       
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                mf.AddPanels();
                
                try 
                {
                    mf.Initialize();
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                mf.setVisible(true);
            }  
        });    
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CoverPanel;
    private javax.swing.JLabel JCloseLbl;
    private javax.swing.JPanel JControlPnl;
    private javax.swing.JLabel JDividerLbl;
    private javax.swing.JLabel JEqualsLbl;
    private javax.swing.JButton JPageFiveBtn;
    private javax.swing.JButton JPageFourBtn;
    private javax.swing.JButton JPageThreeBtn;
    private javax.swing.JButton JPageTwoBtn;
    private javax.swing.JButton JStartBtn;
    private javax.swing.JPanel JTitleBarPnl;
    private javax.swing.JLabel JTitleLbl;
    private javax.swing.JLabel JXLbl;
    private javax.swing.JLabel JskDeathsLbl;
    private javax.swing.JLabel JskPopLbl;
    private javax.swing.JLabel JusPopLbl;
    private javax.swing.JLabel coverPanelLbl;
    private javax.swing.JButton jContinueBtn;
    private javax.swing.JButton jPauseBtn;
    // End of variables declaration//GEN-END:variables
}
