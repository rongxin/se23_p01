package sg.edu.nus.iss.shop.ui.util;

import javax.swing.ImageIcon;

public class IconHelper {
	private static final String ICONS_PATH = "/sg/edu/nus/iss/shop/ui/icons/";

	public static ImageIcon createImageIcon(String imageName) {
		ImageIcon imageIcon = new ImageIcon(IconHelper.class.getResource(ICONS_PATH + imageName));
		return imageIcon;
	}
}
