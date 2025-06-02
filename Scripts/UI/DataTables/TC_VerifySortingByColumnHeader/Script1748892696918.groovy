import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject

// Step 1: Open browser and go to the page
WebUI.openBrowser('')
WebUI.navigateToUrl('https://datatables.net/examples/data_sources/ajax')
WebUI.waitForPageLoad(10)
WebUI.delay(2) // wait for Ajax data

// Step 2: Capture the first value in the "Position" column (column 2)
TestObject firstPositionCell = new TestObject('first_position_cell')
firstPositionCell.addProperty('css', ConditionType.EQUALS, '#example tbody tr:nth-child(1) td:nth-child(2)')
String beforeSort = WebUI.getText(firstPositionCell)
WebUI.comment("Before sort: " + beforeSort)

// Step 3: Click on the "Position" column header to sort
TestObject positionHeader = new TestObject('position_header')
positionHeader.addProperty('xpath', ConditionType.EQUALS, '//th[contains(.,"Extn")]')
WebUI.click(positionHeader)

// Step 4: Wait and capture the new first "Position" cell
WebUI.delay(2)
String afterSort = WebUI.getText(firstPositionCell)
WebUI.comment("After sort: " + afterSort)

// Step 5: Validate sorting changed the order
assert beforeSort != afterSort : "Sorting by 'Position' did not change row order."

// Step 6: Close browser
WebUI.closeBrowser()
