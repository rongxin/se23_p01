package sg.edu.nus.iss.shop.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import sg.edu.nus.iss.shop.dao.PersistentService;
import sg.edu.nus.iss.shop.dao.exception.InvalidDataFormat;
import sg.edu.nus.iss.shop.dao.exception.InvalidDomainObject;
import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Member;
import sg.edu.nus.iss.shop.model.domain.NonMemberCustomer;
import sg.edu.nus.iss.shop.util.ILogger;
import sg.edu.nus.iss.shop.util.Logger;

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
	private ILogger log = Logger.getLog();
	
	private MemberManager() {

	}

	public static MemberManager getMemberManager() {
		if (MemberManager.theOnlyMemberManager == null) {
			MemberManager.theOnlyMemberManager = new MemberManager();
		}
		return MemberManager.theOnlyMemberManager;
	}

	public Member addMember(String id, String name)
			throws ApplicationGUIException {

		if (id == null || id.trim().length() < MemberManager.MIN_ID_LENGTH
				|| id.trim().length() > MemberManager.MAX_ID_LENGTH) {
			throw new ApplicationGUIException(
					MemberManager.INVALID_ID_ERROR_MESSAGE);
		}
		if (name == null
				|| name.trim().length() < MemberManager.MIN_NAME_LENGTH
				|| name.trim().length() > MemberManager.MAX_NAME_LENGTH) {
			throw new ApplicationGUIException(
					MemberManager.INVALID_NAME_ERROR_MESSAGE);
		}

		Member existingMember = MemberManager.getMemberManager().getMemberById(
				id);
		if (existingMember != null) {
			throw new ApplicationGUIException(
					MemberManager.MEMBER_EXISTS_ERROR_MESSAGE);
		}

		Member newMember = new Member(id, name);
		try {
			PersistentService.getService().saveRecord(newMember);
		} catch (Exception e) {
			throw new ApplicationGUIException(e.toString());
		}
		return newMember;
	}

	public List<Member> getAllMembers() throws ApplicationGUIException {
		List<Member> memberList = new LinkedList<Member>();

		try {
			memberList = PersistentService.getService().retrieveAll(
					Member.class);
		} catch (IOException e) {
			throw new ApplicationGUIException(e.toString());
		} catch (InvalidDataFormat e) {
			throw new ApplicationGUIException(e.toString());
		} catch (InvalidDomainObject e) {
			throw new ApplicationGUIException(e.toString());
		}
		return memberList;
	}

	public Member getMemberById(String id) throws ApplicationGUIException {
		Member member = null;

		try {
			member = PersistentService.getService().retrieveObject(
					Member.class, id);
		} catch (InvalidDomainObject e) {
			throw new ApplicationGUIException(e.toString());
		} catch (InvalidDataFormat e) {
			throw new ApplicationGUIException(e.toString());
		} catch (IOException e) {
			throw new ApplicationGUIException(e.toString());
		}
		return member;
	}

	public NonMemberCustomer generateNonMember() {
		return new NonMemberCustomer();
	}

	public void reduceLoyalPoints(Member member, int usedPoints)
			throws ApplicationGUIException {
		if (member.getLoyalPoints() < 0) {
			member.setLoyalPoints(0);
			return;
		}
		
		if (member.getLoyalPoints() < usedPoints) {
			throw new ApplicationGUIException(
					MemberManager.NOT_SUFFICIENT_POINTS_ERROR_MESSAGE);
		}
		member.setLoyalPoints(member.getLoyalPoints() - usedPoints);
		try {
			PersistentService.getService().saveRecord(member);
		} catch (Exception e) {
			throw new ApplicationGUIException(e.toString());
		}
	}

	public void increaseLoyalPoints(Member member, int earnPoints)
			throws ApplicationGUIException {
//		System.out.println(member.getName() + ", " + member.getLoyalPoints());
		log.log(member.getName() + ", " + member.getLoyalPoints());

		if (member.getLoyalPoints() < 0) {
			member.setLoyalPoints(0);
		}

		member.setLoyalPoints(member.getLoyalPoints() + earnPoints);
		try {
			PersistentService.getService().saveRecord(member);
		} catch (Exception e) {
			throw new ApplicationGUIException(e.toString());
		}
	}
}
