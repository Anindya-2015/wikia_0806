
package exercise;

import org.testng.annotations.Test;

public class Exercise {

	public void script(){

		try{
			MethodRepository mr = new MethodRepository();
			mr.startDriver();
			mr.navigateToHomePage();
			mr.verifyAddress();
			mr.mouseHoverOnSignIn();
			mr.verifyLoginForm();
			mr.login();
			mr.validateLogin();
			mr.clickOnContribute();
			mr.selectAddVideo();
			mr.addVideo();
			mr.videoAdditionMessageConfirmation();
			mr.clickOnFileLinkAndConfirmUrl();
			mr.confirmFilename();
			mr.closeDriver();
		}
		catch (Exception e) {
			System.out.println("Exception occurred in script() : "+e.getMessage());
			e.printStackTrace();
		}	


	}

	@Test
	public void runTest() {
		Exercise exer = new Exercise();
		exer.script();
	}


}
