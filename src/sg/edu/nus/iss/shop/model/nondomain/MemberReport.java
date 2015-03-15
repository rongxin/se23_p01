package sg.edu.nus.iss.shop.model.nondomain;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.shop.controller.CategoryManager;
import sg.edu.nus.iss.shop.controller.MemberManager;
import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.model.domain.Member;
import sg.edu.nus.iss.shop.model.domain.Vendor;

/**
 * @author lokeshkanna-b
 *
 */
public class MemberReport extends Report {

	private static MemberReport theOnlyMemberReport;
	
	private MemberReport() {
		// TODO Auto-generated constructor stub
	}
	
	public static MemberReport getMemberReport(){
		if(MemberReport.theOnlyMemberReport == null){
			MemberReport.theOnlyMemberReport = new MemberReport();
		}
		return MemberReport.theOnlyMemberReport;
	}
	
	@Override
	public List<String[]> retreiveAndGenerateReportData() {
		List<Member> membersList = null;
		List<String[]> returnMembersList = new ArrayList<String[]>();
		String[] memberArray = null;
		String memberName = null;
		String memberId = null;
		String memberLoyaltyPoints = null;
		
		MemberManager memberManager = MemberManager.getMemberManager();
		membersList = memberManager.getAllMembers();
		if(membersList != null && membersList.size() > 0){
			for(Member member : membersList){
				memberName = member.getName();
				memberId = member.getId();
				memberArray = new String[]{
						memberName,
						memberId,
						memberLoyaltyPoints
				};
				returnMembersList.add(memberArray);
			}
		}
		return returnMembersList;
	}
}
