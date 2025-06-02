package pages


import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class HomePage {
	
	String baseUrl = 'https://demo.opencart.com'
	
	void goTo() {
		WebUI.openBrowser('')
		WebUI.navigateToUrl(baseUrl)
		WebUI.maximizeWindow()
	}
}
