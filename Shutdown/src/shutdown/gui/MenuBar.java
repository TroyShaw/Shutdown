package shutdown.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Class represents the menu-bar for the program.
 *
 * @author troy
 */
public class MenuBar extends JMenuBar implements ActionListener {

	private JMenu file, helper;
	private JMenuItem exit, help, about;
	
	/**
	 * Instantiates the menu-bar, setting up event-listeners, etc.
	 */
	public MenuBar() {
		super();
		
		file = new JMenu("File");
		helper = new JMenu("Help");
		
		exit = new JMenuItem("Exit");
		help = new JMenuItem("Help");
		about = new JMenuItem("About");
		
		exit.addActionListener(this);
		help.addActionListener(this);
		about.addActionListener(this);
		
		file.add(exit);
		helper.add(help);
		helper.addSeparator();
		helper.add(about);
		
		add(file);
		add(helper);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exit) {
			System.exit(0);
		} else if (e.getSource() == help) {
			Dialogs.displayHelp();
		} else if (e.getSource() == about) {
			Dialogs.displayAbout();
		}
	}
}
