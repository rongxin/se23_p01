package sg.edu.nus.iss.shop.controller;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import sg.edu.nus.iss.shop.dao.PersistentService;
import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Discount;
import sg.edu.nus.iss.shop.model.domain.Member;
import sg.edu.nus.iss.shop.model.domain.NonMemberCustomer;

public class MemberManager {
	private static final String NOT_SUFFICIENT_POINTS_ERROR_MESSAGE = "There is insufficient points in member's account.";
	private static final String INVALID_ID_ERROR_MESSAGE = "Invalid ID";
	private static final String INVALID_NAME_ERROR_MESSAGE = "Invalid Name";
	private static final String MEMBER_EXISTS_ERROR_MESSAGE = "Member already exists.";
	private static final int MIN_ID_LENGTH = 5;
	private static final int MAX_ID_LENGTH = 20;
	private static final int MIN_NAME_LENGTH = 5;
	private static final int MAX_NAME_LENGTH = 30;
	private static MemberManager theOnlyMemberManager;

	private MemberManager() {

	}

	public static MemberManager getMemberManager() {
		if (MemberManager.theOnlyMemberManager == null) {
			MemberManager.theOnlyMemberManager = new MemberManager();
		}
		return MemberManager.theOnlyMemberManager;
	}

	public Member addMember(String id, String name) throws ApplicationGUIException {
		if (id == null || id.trim().length() < MemberManager.MIN_ID_LENGTH || id.trim().length() > MemberManager.MAX_ID_LENGTH) {
			throw new ApplicationGUIException(MemberManager.INVALID_ID_ERROR_MESSAGE);
		}
		if (name == null || name.trim().length() < MemberManager.MIN_NAME_LENGTH || name.trim().length() > MemberManager.MAX_NAME_LENGTH) {
			throw new ApplicationGUIException(MemberManager.INVALID_NAME_ERROR_MESSAGE);
		}
		Member existingMember = MemberManager.getMemberManager().getMemberById(id);
		if (existingMember != null) {
			throw new ApplicationGUIException(MemberManager.MEMBER_EXISTS_ERROR_MESSAGE);
		}

		return null;
	}

	public List<Member> getAllMembers() throws ApplicationGUIException {
		List<Member> memberList = new LinkedList<Member>();
		List<Object> objectList = null;
		
		try{
			objectList = PersistentService.getService().retrieveAll(Member.class);
		}catch(Exception e){
			throw new ApplicationGUIException(e.toString());
		}
		
		if (objectList != null && objectList.isEmpty()){
			Iterator<Object> iter = objectList.iterator();
			while(iter.hasNext()){
				memberList.add((Member)iter.next());
			}
		}
		return memberList;
	}

	public Member getMemberById(String id) throws ApplicationGUIException {
		Member result = null;
		List<Member> allMembers = MemberManager.getMemberManager().getAllMembers();
		Iterator<Member> it = allMembers.iterator();
		while (it.hasNext()) {
			Member member = it.next();
			if (member.getId().equals(id)) {
				result = member;
				return result;
			}
		}
		return result;
	}

	public NonMemberCustomer generateNonMember() {
		return new NonMemberCustomer();
	}

	public int reduceLoyalPoints(Member member, int usedPoints) throws ApplicationGUIException {
		if (member.getLoyalPoints() < usedPoints) {
			throw new ApplicationGUIException(MemberManager.NOT_SUFFICIENT_POINTS_ERROR_MESSAGE);
		}
		member.setLoyalPoints(member.getLoyalPoints() - usedPoints);
		return member.getLoyalPoints();
	}
	
	public int adjustLoyalPoints(Member member, int points) throws ApplicationGUIException {
		if (points >= 0 && member.getLoyalPoints() < points){
			throw new ApplicationGUIException(MemberManager.NOT_SUFFICIENT_POINTS_ERROR_MESSAGE);
		}else if(points < 0){

		}

		member.setLoyalPoints(member.getLoyalPoints() - points);
		return member.getLoyalPoints();
	}
}
