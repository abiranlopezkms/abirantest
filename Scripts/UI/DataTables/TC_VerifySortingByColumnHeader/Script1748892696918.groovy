import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject

// Step 1: Capture the first value in the "Position" column (column 2)
TestObject firstPositionCell = new TestObject('first_position_cell')
firstPositionCell.addProperty('css', ConditionType.EQUALS, '#example tbody tr:nth-child(1) td:nth-child(2)')
String beforeSort = WebUI.getText(firstPositionCell)
WebUI.comment("Before sort: " + beforeSort)

// Step 2: Click on the "Position" column header to sort
TestObject positionHeader = new TestObject('position_header')
positionHeader.addProperty('xpath', ConditionType.EQUALS, '//th[contains(.,"Extn")]')
WebUI.click(positionHeader)

// Step 3: Wait and capture the new first "Position" cell
WebUI.delay(2)
String afterSort = WebUI.getText(firstPositionCell)
WebUI.comment("After sort: " + afterSort)

// Step 6: Validate sorting changed the order
assert beforeSort != afterSort : "Sorting by 'Position' did not change row order."

