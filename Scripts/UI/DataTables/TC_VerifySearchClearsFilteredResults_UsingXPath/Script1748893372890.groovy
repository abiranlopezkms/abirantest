import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject

WebUI.openBrowser('')
WebUI.navigateToUrl('https://datatables.net/examples/data_sources/ajax')
WebUI.waitForPageLoad(10)
WebUI.delay(2) // Wait for data to load

// Step 1: Locate the search input using XPath
TestObject searchInputXPath = new TestObject('search_input_xpath')
searchInputXPath.addProperty('xpath', ConditionType.EQUALS, '//div[@class="demo-html"]//input[@type="search"]')

// Step 2: Enter 'Tokyo'
WebUI.setText(searchInputXPath, 'Tokyo')
WebUI.delay(2)

// Step 3: Count number of rows with 'Tokyo' in Office column
TestObject tokyoRows = new TestObject('tokyo_office_rows')
tokyoRows.addProperty('xpath', ConditionType.EQUALS, '//table[@id="example"]//tbody//tr/td[3][text()="Tokyo"]')

int tokyoCount = WebUI.findWebElements(tokyoRows, 5).size()
WebUI.comment("Rows with 'Tokyo': $tokyoCount")
assert tokyoCount > 0 : "No rows with 'Tokyo' found after filtering"

// Step 4: Clear the input
WebUI.setText(searchInputXPath, '') // Clear input
WebUI.delay(30)

// Step 5: Count total visible rows after clearing filter
TestObject allVisibleRows = new TestObject('all_visible_rows')
allVisibleRows.addProperty('xpath', ConditionType.EQUALS, '//table[@id="example"]//tbody//tr')

int allRows = WebUI.findWebElements(allVisibleRows, 5).size()
WebUI.comment("Rows after clearing filter: $allRows")
assert allRows > tokyoCount : "Table did not reset properly after clearing filter" // this step fails because loading all the rows are not working properly

WebUI.closeBrowser()


