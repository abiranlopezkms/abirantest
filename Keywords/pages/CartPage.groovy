package pages

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.ObjectRepository as OR

public class CartPage {
	
	void goTo() {
		WebUI.click(OR.findTestObject('CartPage/cartButton'))
	}
	
	List<String> getProductNames(){
		List<String> names = []
		def elements = WebUI.findWebElements(OR.finTestObject('CartPage/cartProductNames'),10)
		elements.each { el ->
			names << el.getText()
		}
		return names
	}
}
