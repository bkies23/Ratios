package org.files;

import java.time.Clock;
import java.time.Instant;

/* Programmer Brian Kies */

public class Reference 
{
     public static final ImagePanel imagePanel = new ImagePanel();
     
     public static Clock clock;
     public static boolean NotStarted = true;
     static String [] currentStats = new String [3];
     static long [] currentNumericalStats = new long [3];
     static String formattedCaseTotal;
     static long unformattedCaseTotal;
     static String formattedDeathTotal;
}
