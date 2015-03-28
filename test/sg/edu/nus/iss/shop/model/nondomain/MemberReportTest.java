package sg.edu.nus.iss.shop.model.nondomain;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.shop.controller.MemberManager;
import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Member;

public class MemberReportTest {
	
	private MemberReport memberReport = null;
	MemberManager memberManager = null;
	
	@Before
	public void setup() {
		memberReport = MemberReport.getMemberReport();
		memberManager = MemberManager.getMemberManager();
	}
	
	@Test
	public void testMemberReportInitialization(){
		assertNotNull("MemberReport Object should not be null", memberReport);
		MemberReport newMemberReport = MemberReport.getMemberReport();
		assertSame("The MemberReport object doesn't match", newMemberReport, memberReport);
	}
	
	@Test
	public void testRetreiveAndGenerateReportData(){
		
		List<String[]> memberReportList = null;
		List<Member> memberList = null;
		try{
			memberReportList = memberReport.retreiveAndGenerateReportData();
			assertNotNull("The member report data should not be null", memberReportList);
			if(memberReportList.size() > 0){
				for(String[] memberArray : memberReportList){
					assertNotNull("The member array should not be null!", memberArray);
					boolean isInvalidaData = checkArrayHasNullOrEmpty(memberArray);
					assertFalse("The value inside member array should not be null or empty", isInvalidaData == false);
				}
			}else{
				memberList = memberManager.getAllMembers();	
				assertFalse("The member report data is incorrect!", !memberList.isEmpty());
			}
		}catch(ApplicationGUIException aguie){
			aguie.printStackTrace();
			Assert.fail(aguie.getDisplayMessage());
		}
	}
	
	
	/**
	 * Method to check whether the array got a invalid data
	 * */
	private boolean checkArrayHasNullOrEmpty(String[] memberArray){
		
		for(String memberDetails : memberArray){
			if(memberDetails == null || memberDetails == ""){
				return false;
			}
		}
		return true;
	}

}
