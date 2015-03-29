package sg.edu.nus.iss.shop.ui.util;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class MessageHelper {

	public static void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public static void showErrorMessage(JFrame frame, String message) {
		JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public static void showErrorMessage(JComponent component, String message) {
		showErrorMessage((JFrame) SwingUtilities.getWindowAncestor(component), message);
	}

	public static void showInfoMessage(JComponent component, String message) {
		if (component == null) {
			JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(component), message, "Info",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}
}
