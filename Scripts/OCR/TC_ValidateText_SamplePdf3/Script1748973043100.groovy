import org.apache.pdfbox.Loader
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper
import java.io.File

File file = new File("C:/Users/AbiranLopez/Documents/katalontest/sample3.pdf")
assert file.exists() : "PDF not found at ${file.absolutePath}"

PDDocument document = Loader.loadPDF(file)
PDFTextStripper stripper = new PDFTextStripper()
String text = stripper.getText(document)
document.close()

println "Extracted PDF Text:\n$text"

assert text.contains("Project Background") : "Missing 'Project Background' section"
assert text.contains("1.1 Introduction") : "Missing 'Introduction' section"
assert text.contains("Option 1: Cheapest") : "Missing travel option content"
assert text.contains("REQ016 Breaking-up Quotes") : "Missing requirement REQ016"
assert text.contains("3.2.1.1.1.1 Basic Path : Get Quote") : "Missing scenario structure"
assert text.contains("ISS034 Cash collections process") : "Missing issue reference"



