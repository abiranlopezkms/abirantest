package pages

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.ObjectRepository as OR

public class ProductDetailPage {

	void addToCart() {
		WebUI.click(OR.findTestObject('ProductDetailPage/addToCartButton'))
	}
}
