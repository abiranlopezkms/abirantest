import pages.HomePage
import pages.SearchPage
import pages.ProductDetailPage
import pages.CartPage
import com.kms.katalon.core.util.KeywordUtil

HomePage home = new HomePage()
SearchPage search = new SearchPage()
ProductDetailPage detail = new ProductDetailPage()
CartPage cart = new CartPage()

home.goTo()

WebUI.setText(findTestObject('HomePage/searchInput'), 'iPhone')
WebUI.clic(findTestObject('HomePage/searchButton'))