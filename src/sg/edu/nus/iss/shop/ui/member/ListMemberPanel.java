package sg.edu.nus.iss.shop.ui.member;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import sg.edu.nus.iss.shop.model.domain.Member;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;

public class ListMemberPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;

	public ListMemberPanel(ShopApplication shopApplication) {
		super();
		this.shopApplication = shopApplication;
		this.add(createMainPanel());
	}

	private JPanel createMainPanel() {
		JPanel p = new JPanel();

		List<Member> members = shopApplication.getMembers();
		Object tableData[][] = new Object[members.size()][3];

		int i = 0;
		for (Member member : members) {
			tableData[i][0] = member.getName();
			tableData[i][1] = member.getId();
			tableData[i][2] = member.getLoyalPoints();
			i++;
		}

		Object columnNames[] = { "Member Name", "Member ID", "Loyalty Points" };
		JTable table = new JTable(tableData, columnNames);
		table.setName("Items");
		table.setEnabled(false);
		JScrollPane scrollPane = new JScrollPane(table);
		p.add(scrollPane);
		return p;
	}


}
