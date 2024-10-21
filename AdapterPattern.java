
// DocumentViewer interface
interface DocumentViewer {
    void display(String documentType, String documentName);
}

// New DocumentReader interface
interface DocumentReader {
    void readPdf(String fileName);
    void readDoc(String fileName);
}

// Implementation of DocumentReader for PDF
class PdfReader implements DocumentReader {
    @Override
    public void readPdf(String fileName) {
        System.out.println("Reading PDF document. Name: " + fileName);
    }

    @Override
    public void readDoc(String fileName) {
        // Do nothing
    }
}

// Implementation of DocumentReader for DOC
class DocReader implements DocumentReader {
    @Override
    public void readPdf(String fileName) {
        // Do nothing
    }

    @Override
    public void readDoc(String fileName) {
        System.out.println("Reading DOC document. Name: " + fileName);
    }
}

// Adapter class implementing DocumentViewer and using DocumentReader
class DocumentViewerAdapter implements DocumentViewer {
    DocumentReader documentReader;

    public DocumentViewerAdapter(String documentType) {
        if (documentType.equalsIgnoreCase("pdf")) {
            documentReader = new PdfReader();
        } else if (documentType.equalsIgnoreCase("doc")) {
            documentReader = new DocReader();
        }
    }

    @Override
    public void display(String documentType, String documentName) {
        if (documentType.equalsIgnoreCase("pdf")) {
            documentReader.readPdf(documentName);
        } else if (documentType.equalsIgnoreCase("doc")) {
            documentReader.readDoc(documentName);
        }
    }
}

// Implementation of DocumentViewer using the adapter
class ViewerApplication implements DocumentViewer {
    DocumentViewerAdapter documentViewerAdapter;

    @Override
    public void display(String documentType, String documentName) {
        // Displaying text documents directly
        if (documentType.equalsIgnoreCase("txt")) {
            System.out.println("Displaying text document. Name: " + documentName);
        }
        // Using adapter for other document formats
        else if (documentType.equalsIgnoreCase("pdf") || documentType.equalsIgnoreCase("doc")) {
            documentViewerAdapter = new DocumentViewerAdapter(documentType);
            documentViewerAdapter.display(documentType, documentName);
        } else {
            System.out.println("Invalid document format. " + documentType + " not supported");
        }
    }
}

// Main class to test the adapter pattern
public class AdapterPattern {
    public static void main(String[] args) {
        ViewerApplication viewerApplication = new ViewerApplication();

        viewerApplication.display("txt", "introduction.txt");
        viewerApplication.display("pdf", "report.pdf");
        viewerApplication.display("doc", "memo.doc");
        viewerApplication.display("html", "index.html");
    }
}

