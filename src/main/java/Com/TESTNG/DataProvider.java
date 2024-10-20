package Com.TESTNG;

public class DataProvider extends BASECLASS {

	@Test(dataProvider= "login")
	
	public void login(String username,String password) {
		launchBrowser ("chrome");
		launchurl ()
		
	}


		
	}
}
