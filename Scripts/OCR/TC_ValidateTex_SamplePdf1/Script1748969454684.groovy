import org.apache.pdfbox.Loader
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper
import java.io.File

File file = new File('C:/Users/AbiranLopez/Documents/katalontest/sample1.pdf')
assert file.exists() : "PDF file not found at ${file.absolutePath}"

PDDocument document = Loader.loadPDF(file)

PDFTextStripper stripper = new PDFTextStripper()
String pdfText = stripper.getText(document)
document.close()

println "Extracted PDF text:\n$pdfText"

assert pdfText.contains("You’re Ready to Print!") : "Expected content not found in the PDF."



