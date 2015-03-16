package sg.edu.nus.iss.shop.ui.util;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class LayoutHelper {

	public static GridBagConstraints createCellConstraint(int x, int y) {
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = x;
		gc.gridy = y;
		gc.gridwidth = 1;
		gc.gridheight = 1;

		boolean isLeftMostColumn = x == 0;
		gc.anchor = isLeftMostColumn ? GridBagConstraints.WEST : GridBagConstraints.EAST;
		gc.fill = isLeftMostColumn ? GridBagConstraints.BOTH : GridBagConstraints.HORIZONTAL;

		Insets westInset = new Insets(5, 0, 5, 5);
		Insets eastInset = new Insets(5, 5, 5, 0);
		gc.insets = isLeftMostColumn ? westInset : eastInset;
		gc.weightx = isLeftMostColumn ? 0.1 : 1.0;
		gc.weighty = 1.0;
		return gc;
	}

}
