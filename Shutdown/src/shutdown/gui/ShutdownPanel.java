package shutdown.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import shutdown.sys.Shutdown;
import shutdown.sys.ShutdownMode;

/**
 * Represents the main panel used in the program.
 * Holds all main text-field, buttons, and status bar.
 *
 * @author troy
 */
public class ShutdownPanel extends JPanel {

	private JTextField textField;
	private JLabel timeLabel;
	private JButton startButton, cancelButton;

	private JRadioButton shutdownRButton, restartRButton;
	private String messageString;
	private ShutdownMode shutdownMode;
	
	private StatusBar statusBar;

	public ShutdownPanel() {
		initComponents();
		initListeners();
		setupLayout();
	}

	private void initComponents() {
		textField = new JTextField(19);
		timeLabel = new JLabel("Time in minutes:");
		timeLabel.setLabelFor(textField);

		startButton = new JButton("Start");
		cancelButton = new JButton("Cancel");

		shutdownRButton = new JRadioButton("Shutdown", true);
		restartRButton = new JRadioButton("Restart");

		ButtonGroup bg = new ButtonGroup();
		bg.add(shutdownRButton);
		bg.add(restartRButton);

		shutdownMode = ShutdownMode.Shutdown;
		messageString = "Shutting down";
		
		statusBar = new StatusBar();
	}

	private void initListeners() {
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//stops highlighting all text if they've already clicked the field
				if (!textField.hasFocus())
					textField.selectAll();
			}
		});

		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();

				if (o == startButton) {
					shutdown();
				} else if (o == cancelButton) {
					cancelShutdown();
				}
			}
		};

		startButton.addActionListener(al);
		cancelButton.addActionListener(al);

		ActionListener radioListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == shutdownRButton) {
					shutdownMode = ShutdownMode.Shutdown;
					messageString = "Shutting down";
				}
				else {
					shutdownMode = ShutdownMode.Restart;
					messageString = "Restarting";
				}
			}
		};

		shutdownRButton.addActionListener(radioListener);
		restartRButton.addActionListener(radioListener);
	}

	private void setupLayout() {
		setLayout(new BorderLayout());
		JPanel masterPanel = new JPanel();
		masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
		
		JPanel inputPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JPanel radioPanel = new JPanel();

		inputPanel.add(timeLabel);
		inputPanel.add(textField);

		buttonPanel.add(startButton);
		buttonPanel.add(cancelButton);

		radioPanel.add(shutdownRButton);
		radioPanel.add(restartRButton);

		masterPanel.add(inputPanel);
		masterPanel.add(buttonPanel);
		masterPanel.add(radioPanel);
		
		add(masterPanel);
		add(statusBar, BorderLayout.SOUTH);
	}

	private void shutdown() {
		int minutes = 0;

		try {
			minutes = Integer.parseInt(textField.getText());
		} catch (NumberFormatException e) {
			if (Pattern.matches("\\d*", textField.getText()))
					statusBar.error("Number too large...");
			else 
				statusBar.error("Enter time in minutes.");
			return;
		}

		if (minutes < 0) {
			statusBar.error("Stop living in the past.");
			return;
		} else if (minutes > Shutdown.maxMinutes()) {
			statusBar.error("Number too large...");
			return;
		}

		if (Shutdown.initiateShutdown(minutes, shutdownMode)) {
			statusBar.success(messageString + " in " + minutes + " minutes.");
		} else {
			statusBar.error("Already " + messageString.toLowerCase() + "...");
		}
	}

	private void cancelShutdown() {
		if (Shutdown.cancelShutdown()) {
			statusBar.success("Process cancelled.");
		} else {
			statusBar.error("No process to cancel...");
		}
	}
}
