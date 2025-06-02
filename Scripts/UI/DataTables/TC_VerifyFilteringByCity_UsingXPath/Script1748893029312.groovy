import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject

// Step 1: Open browser
WebUI.openBrowser('')
WebUI.navigateToUrl('https://datatables.net/examples/data_sources/ajax')
WebUI.waitForPageLoad(10)
WebUI.delay(2) // Wait for DataTable to load

// Step 2: Locate search input using XPath
TestObject searchInputXPath = new TestObject('search_input_xpath')
searchInputXPath.addProperty('xpath', ConditionType.EQUALS, '//div[@class="demo-html"]//input[@type="search"]')

// Step 3: Type 'London' in the search field
WebUI.setText(searchInputXPath, 'London')
WebUI.delay(3) // Allow table to filter

// Step 4: Verify that at least one row contains 'London' in the "Office" column
TestObject londonCellXPath = new TestObject('cell_with_london')
londonCellXPath.addProperty('xpath', ConditionType.EQUALS, '//table[@id="example"]//tbody//tr/td[3][text()="London"]')

WebUI.verifyElementPresent(londonCellXPath, 5)

// Step 5: Close browser
WebUI.closeBrowser()
