package sg.edu.nus.iss.shop.ui.category;

import java.awt.Button;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;

public class ListCategoryPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;

	public ListCategoryPanel(ShopApplication shopApplication) {
		super();
		this.shopApplication = shopApplication;
		this.add(createMainPanel());
	}

	private JPanel createMainPanel() {
		JPanel p = new JPanel();

		List<Category> categories = shopApplication.getCategories();
		// Oscar: Adding the Edit button
		Object categoryData[][] = new Object[categories.size()][3];

		int i = 0;
		for (Category category : categories) {
			categoryData[i][0] = category.getCode();
			categoryData[i][1] = category.getName();
			// Edit Button
			categoryData[i][2] = "Edit";

			i++;
		}

		Object columnNames[] = { "Category Code", "Cateory Name", "Edit" };
		// Object rowData[][] = { { "CLO", "Clothes" }, { "MUG", "Mugs" } };
		JTable table = new JTable(categoryData, columnNames);
		table.getColumn("Edit").setCellRenderer(new EditButtonRenderer());
		table.getColumn("Edit").setCellEditor(new EditButtonEditor(new JCheckBox()));
		table.setName("Items");
		table.setEnabled(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(table);
		p.add(scrollPane);
		return p;
	}

	private class EditButtonRenderer extends JButton implements TableCellRenderer {

		@Override
		public Component getTableCellRendererComponent(JTable arg0,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			return this;
		}
	}

	class EditButtonEditor extends DefaultCellEditor {
		protected JButton button;

		private String label;

		private boolean isPushed;

		public EditButtonEditor(JCheckBox checkBox) {
			super(checkBox);
			button = new JButton("Oscar");
			button.setOpaque(true);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fireEditingStopped();
				}
			});
		}

		public Component getTableCellEditorComponent(JTable table,
				Object value, boolean isSelected, int row, int column) {
			if (isSelected) {
				button.setForeground(table.getSelectionForeground());
				button.setBackground(table.getSelectionBackground());
			} else {
				button.setForeground(table.getForeground());
				button.setBackground(table.getBackground());
			}
			label = (value == null) ? "" : value.toString();
			button.setText(label);
			isPushed = true;
			return button;
		}

		public Object getCellEditorValue() {
			if (isPushed) {
				AddCategoryDialog d = new AddCategoryDialog(shopApplication);
				d.pack();
				d.setLocationByPlatform(true);
				d.setVisible(true);
			}
			isPushed = false;
			return new String(label);
		}

		public boolean stopCellEditing() {
			isPushed = false;
			return super.stopCellEditing();
		}

		protected void fireEditingStopped() {
			super.fireEditingStopped();
		}
	}

}
