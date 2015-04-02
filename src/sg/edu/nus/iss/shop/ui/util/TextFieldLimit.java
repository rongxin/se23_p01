package sg.edu.nus.iss.shop.ui.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Xia RONgxin
 *
 */
public class TextFieldLimit extends PlainDocument {

	private static final long serialVersionUID = 1L;
	private int maxTextLength;

	public TextFieldLimit(int limit) {
		super();
		maxTextLength = limit;
	}

	public TextFieldLimit(int maxTextLength, boolean upper) {
		super();
		this.maxTextLength = maxTextLength;
	}

	@Override
	public void insertString(int offset, String input, AttributeSet attr) throws BadLocationException {
		if (input == null)
			return;

		if ((getLength() + input.length()) <= maxTextLength) {
			super.insertString(offset, input, attr);
		}
	}
}
