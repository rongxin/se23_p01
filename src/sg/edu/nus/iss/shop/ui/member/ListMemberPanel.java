package sg.edu.nus.iss.shop.ui.member;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import sg.edu.nus.iss.shop.model.domain.Member;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;

/**
 *
 * @author Xia Rongxin
 *
 */
public class ListMemberPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private MemberTableModel tableModel;

	public ListMemberPanel(ShopApplication shopApplication) {
		super();
		this.shopApplication = shopApplication;
		this.add(createMainPanel());
	}

	private JPanel createMainPanel() {
		JPanel p = new JPanel();
		List<Member> members = shopApplication.getMembers();

		tableModel = new MemberTableModel();
		for (Member member : members) {
			tableModel.addToTable(member);
		}

		JTable table = new JTable(tableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(230);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.setName("Items");
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(500, 450));
		p.add(scrollPane);
		return p;
	}

	public MemberTableModel getTableModel() {
		return tableModel;
	}

}
