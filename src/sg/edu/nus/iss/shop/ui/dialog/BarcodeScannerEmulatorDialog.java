package sg.edu.nus.iss.shop.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import sg.edu.nus.iss.shop.ui.IconHelper;
import sg.edu.nus.iss.shop.ui.LayoutHelper;

public class BarcodeScannerEmulatorDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField barcodeNumberField;

	public String getScannedBarcodeNumber() {
		return barcodeNumberField.getText().trim();
	}

	private JLabel messageLabel;
	private JButton okButton;

	public BarcodeScannerEmulatorDialog(Container container) {
		add("North", createTitlePanel());
		add("Center", createFormPanel());
		add("South", createButtonPanel());
	}

	private Component createTitlePanel() {
		JPanel p = new JPanel();
		JLabel title = new JLabel("Barcode Scanner", IconHelper.createImageIcon("barcode_scanner.png"),
				SwingConstants.CENTER);
		p.add(title);
		return p;
	}

	public void addConfirmListener(ActionListener listener) {
		okButton.addActionListener(listener);
	}

	private JPanel createButtonPanel() {
		JPanel p = new JPanel();
		okButton = new JButton("Scan");
		p.add(okButton);

		JButton cancelButton = new JButton("Close");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		p.add(cancelButton);

		return p;
	}

	protected JPanel createFormPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());

		mainPanel.add(new JPanel(), BorderLayout.NORTH);
		mainPanel.add(new JPanel(), BorderLayout.WEST);
		mainPanel.add(new JPanel(), BorderLayout.EAST);
		mainPanel.add(createInputFormPanel(), BorderLayout.CENTER);
		mainPanel.add(createFormMessagePanel(), BorderLayout.SOUTH);
		UIManager.put("title.font", new Font("Arial", Font.BOLD, 14));
		return mainPanel;
	}

	private JPanel createInputFormPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(""),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		GridBagConstraints gc = new GridBagConstraints();

		// column 1
		gc = LayoutHelper.createCellConstraint(1, 0);
		barcodeNumberField = new JTextField(20);
		barcodeNumberField.setToolTipText("Please input student or staff card number.");
		p.add(barcodeNumberField, gc);

		return p;
	}

	private JPanel createFormMessagePanel() {
		JPanel p = new JPanel(new GridLayout(0, 1));
		messageLabel = new JLabel(" ", SwingConstants.CENTER);
		messageLabel.setText("Please scan the barcode.");

		p.add(messageLabel);
		return p;
	}

}
