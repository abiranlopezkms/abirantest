import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

class CommonHooks {

    @BeforeTestCase
    def setupBrowser() {
        WebUI.openBrowser('')
        WebUI.navigateToUrl('https://datatables.net/examples/data_sources/ajax')
        WebUI.waitForPageLoad(10)
        WebUI.delay(2)
    }

    @AfterTestCase
    def teardownBrowser() {
        WebUI.closeBrowser()
    }
}



