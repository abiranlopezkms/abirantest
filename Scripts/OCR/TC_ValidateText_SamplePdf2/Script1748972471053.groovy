import org.apache.pdfbox.Loader
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper
import java.io.File

File file = new File("C:/Users/AbiranLopez/Documents/katalontest/sample2.pdf")
assert file.exists() : "PDF not found at ${file.absolutePath}"

PDDocument document = Loader.loadPDF(file)
PDFTextStripper stripper = new PDFTextStripper()
String text = stripper.getText(document)
document.close()

println "Extracted PDF Text:\n$text"

assert text.contains("Given Name:") : "Expected 'Given Name' not found"
assert text.contains("Height (cm):") : "Expected 'Height' not found"
assert text.contains("Important: Save the completed PDF form (use menu File - Save).") : "Expected 'Important' section not found"



