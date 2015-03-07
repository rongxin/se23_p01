package sg.edu.nus.iss.shop.controller;

import java.util.LinkedList;
import java.util.List;

import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Member;
import sg.edu.nus.iss.shop.model.domain.NonMemberCustomer;

public class MemberManager {
	private static final String NOT_SUFFICIENT_POINTS_ERROR_MESSAGE = "There is insufficient points in member's account.";
	private static MemberManager theOnlyMemberManager;

	private MemberManager() {

	}

	public static MemberManager getMemberManager() {
		if (MemberManager.theOnlyMemberManager == null) {
			MemberManager.theOnlyMemberManager = new MemberManager();
		}
		return MemberManager.theOnlyMemberManager;
	}

	public Member addMember(String id, String name) throws ApplicationGUIException{

		return null;
	}

	public List<Member> getAllMembers() {
		return new LinkedList<Member>();
	}
	
	public NonMemberCustomer generateNonMember(){
		return new NonMemberCustomer();
	}
	
	public int reduceLoyalPoints(Member member, int usedPoints) throws ApplicationGUIException{
		if (member.getLoyalPoints() < usedPoints){
			throw new ApplicationGUIException(MemberManager.NOT_SUFFICIENT_POINTS_ERROR_MESSAGE);
		}
		member.setLoyalPoints(member.getLoyalPoints()-usedPoints);
		return member.getLoyalPoints();
	}

}
