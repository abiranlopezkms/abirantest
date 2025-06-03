import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.context.TestCaseContext

class CommonHooks {

    @BeforeTestCase
    def setupBrowser(TestCaseContext testCaseContext) {
        if (shouldSkip(testCaseContext)) return

        WebUI.openBrowser('')
        WebUI.navigateToUrl('https://datatables.net/examples/data_sources/ajax')
        WebUI.waitForPageLoad(10)
        WebUI.delay(2)
    }

    @AfterTestCase
    def teardownBrowser(TestCaseContext testCaseContext) {
        if (shouldSkip(testCaseContext)) return

        WebUI.closeBrowser()
    }

    private boolean shouldSkip(TestCaseContext ctx) {
        def excluded = [
            'Test Cases/OCR/TC_ValidateText_SamplePdf1',
			'Test Cases/OCR/TC_ValidateText_SamplePdf2',
			'Test Cases/OCR/TC_ValidateText_SamplePdf3',
			'Test Cases/Data/OpenExcel',
			'Test Cases/Data/OpenJson',
			'Test Cases/Data/OpenCsv'
        ]
        return excluded.contains(ctx.getTestCaseId())
    }
}



