import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject

// Step 1: Open browser and navigate
WebUI.openBrowser('')
WebUI.navigateToUrl('https://datatables.net/examples/data_sources/ajax')
WebUI.waitForPageLoad(10)
WebUI.delay(2) // Allow AJAX data to load

// Step 2: Get text from the first cell of the table (page 1)
TestObject firstCell = new TestObject('page1_first_cell')
firstCell.addProperty('css', ConditionType.EQUALS, '#example tbody tr:nth-child(1) td:nth-child(1)')
String page1Text = WebUI.getText(firstCell)
WebUI.comment("Page 1 - First cell: " + page1Text)

// Step 3: Click the Next button
TestObject nextButton = new TestObject('next_button')
nextButton.addProperty('css', ConditionType.EQUALS, 'button.dt-paging-button.next')
WebUI.click(nextButton)

// Step 4: Wait and get the new first row's first column text (page 2)
WebUI.delay(2)
String page2Text = WebUI.getText(firstCell)
WebUI.comment("Page 2 - First cell: " + page2Text)

// Step 5: Compare that the two values are different
assert page1Text != page2Text : "Pagination did not load different data."

// Step 6: Close browser
WebUI.closeBrowser()
