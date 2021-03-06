package sg.edu.nus.iss.shop.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import sg.edu.nus.iss.shop.ui.main.ShopApplication;

public abstract class OkCancelDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JButton okButton;
	protected ShopApplication shopApplication;

	public OkCancelDialog(ShopApplication shopApplication, JFrame parent, String title) {
		super(parent, title);
		this.shopApplication = shopApplication;
		add("Center", createFormPanel());
		add("South", createButtonPanel());
	}

	/*
	 * allow set the form panel after dialog created
	 */
	public void setFormPanel(JPanel formPanel) {
		add("Center", formPanel);
	}

	public OkCancelDialog(JFrame parent, String title) {
		super (parent, title);
		add ("Center", createFormPanel());
		add ("South",  createButtonPanel());
	}

	public OkCancelDialog (JFrame parent) {
		this (parent, "");
	}


	private JPanel createButtonPanel () {
		JPanel p = new JPanel ();

		ActionListener l;

		okButton = new JButton ("OK");
		l = new ActionListener () {
			@Override
			public void actionPerformed (ActionEvent e) {
				boolean success = performOkAction ();
				if (success) {
					destroyDialog();
				}
			}
		};
		okButton.addActionListener (l);
		p.add (okButton);

		okButton = new JButton ("Cancel");
		l = new ActionListener () {
			@Override
			public void actionPerformed (ActionEvent e) {
				destroyDialog ();
			}
		};
		okButton.addActionListener (l);
		p.add (okButton);

		return p;
	}

	private void destroyDialog () {
		setVisible (false);
		dispose();
	}

	protected abstract JPanel createFormPanel () ;

	protected abstract boolean performOkAction () ;

}