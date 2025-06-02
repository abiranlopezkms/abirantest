package pages

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.ObjectRepository as OR

public class SearchPage {
	
	void waitForResults() {
		WebUI.waitForElementVisible(OR.findTestObject('SearchPage/productTitleLink'), 10)
	}
	
	void clickFirstResult() {
		WebUI.click(OR.findTestObject('SearchPage/productTitleLink'))
	}
}
