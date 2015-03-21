package sg.edu.nus.iss.shop.model.nondomain;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.shop.controller.MemberManager;
import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Member;

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
	public List<String[]> retreiveAndGenerateReportData() throws ApplicationGUIException {
		List<Member> membersList = null;
		List<String[]> returnMembersList = new ArrayList<String[]>();
		String[] memberArray = null;
		String memberName = null;
		String memberId = null;
		int memberLoyaltyPoints = -1;
		
		MemberManager memberManager = MemberManager.getMemberManager();
		membersList = memberManager.getAllMembers();
		if(membersList != null && membersList.size() > 0){
			for(Member member : membersList){
				memberName = member.getName();
				memberId = member.getId();
				memberLoyaltyPoints = member.getLoyalPoints();
				memberArray = new String[]{
						memberName,
						memberId,
						String.valueOf(memberLoyaltyPoints)
				};
				returnMembersList.add(memberArray);
			}
		}
		return returnMembersList;
	}
}
