package Shutdown;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 * Simple GUI based shutdown utility for windows.
 * There are 2 buttons and a text field.
 * You can type in a number in minutes into the text field
 * One button will shutdown the computer in the time specified in the text field.
 * The other will cancel your shutdown attempt if you change your mind.
 */
public class ShutdownFrame extends JFrame {

	private ShutdownPanel shutdownPanel;
	private MenuBar menuBar;

	public ShutdownFrame() {
		super("Shutdown utility");

		setNativeLAndF();
		
		initComponents();
		initMenubar();
		setupLayout();

		pack();
		center();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	private void initComponents() {
		shutdownPanel = new ShutdownPanel();
	}
	
	private void initMenubar() {
		menuBar = new MenuBar();
		
		setJMenuBar(menuBar);
	}
	
	private void setupLayout() {
		getContentPane().add(shutdownPanel);
	}
	
	private void setNativeLAndF() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {

		}
	}

	private void center() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		int w = this.getSize().width;
		int h = this.getSize().height;
		int x = (dim.width - w) / 2;
		int y = (dim.height - h) / 2;

		setBounds(x, y, w, h);
	}
	
	public static void main(String[] args) {
		new ShutdownFrame();
	}
}