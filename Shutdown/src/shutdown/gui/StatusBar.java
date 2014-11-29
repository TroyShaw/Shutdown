package shutdown.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Class represents the status-bar, which is the bar at the bottom of
 * the GUI which provides information to the user.
 *
 * @author troy
 */
public class StatusBar extends JPanel {
	private JLabel label;

	public StatusBar() {
		setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		setPreferredSize(new Dimension(50, 16));
		setLayout(new BorderLayout());
		setBackground(Color.lightGray.brighter());

		initializeLabels();
	}

	private void initializeLabels() {
		label = new JLabel("", SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.TOP);
		add(label);
	}

	public void success(String message) {
		setMessage(message, Color.green.darker());
	}

	public void error(String message) {
		setMessage(message, Color.red);
	}

	private void setMessage(String message, Color color) {
		label.setForeground(color);
		label.setText(message);
	}
}
