package shutdown.gui;

import javax.swing.JOptionPane;

/**
 * Class holds methods to create popup dialog boxes, with help and about messages.
 * 
 * @author troy
 */
public class Dialogs {
	
	/**
	 * Displays the programs 'help'.
	 */
	public static void displayHelp() {
		String help = 
				"This program can be used to easily shut down or restart your computer some time in the future.\n\n" +
				"Enter the countdown time in minutes, then " +
				"select either shutdown or restart.\n" + 
				"Click \"Start\" to start the process.\n\n" +
				"You can also stop the process by clicking \"Cancel\".";
				
		JOptionPane.showMessageDialog(null, help);
	}
	
	/**
	 * Displays the programs 'about'.
	 */
	public static void displayAbout() {
		String about = 	
				"Shutdown utilty.\nVersion 2.00\n\n" +
				"This software is free and open source.\n" +
				"Feel free to distribute it to your friends!\n\n" +
				"Please email bugs, suggestions and comments to troyshw@gmail.com\n\n" + 
				"Troy Shaw, 2012.";
				
		JOptionPane.showMessageDialog(null, about);
	}
}
