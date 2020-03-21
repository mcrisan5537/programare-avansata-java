import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public class ReportHTMLCommand extends Command {

    public static void process(Catalog catalog) throws ShellException {

        if(catalog == null)
            throw new ShellException("No catalog loaded, can't create report.html");

        List<Document> documents = catalog.getDocuments();
        PrintStream printStream = null;
        try {
            printStream = new PrintStream(new BufferedOutputStream(new FileOutputStream("report.html")));
        } catch (IOException e) {
            System.err.println("IO Error when creating report.html");
            e.printStackTrace();
        }

        printStream.println("<!DOCTYPE html>");
        printStream.println("<html>");
            printStream.println("<head>");
                printStream.println("<title>");
                    printStream.println("Catalog contents");
                printStream.println("</title>");
            printStream.println("</head>");
            printStream.println("<body>");
                printStream.println("<h1>");
                    printStream.println("<pre>");
                    printStream.println("Catalog name: " + catalog.getName());
                    printStream.println("Catalog path: " + catalog.getPath());
                    printStream.println("Catalog documents: ");
                    printStream.println("</pre>");
                printStream.println("</h1>");
                printStream.println("<pre>");
                for(int i = 0; i < documents.size(); i++) {
                    printStream.println("<h2>");
                        printStream.println("Document #" + (i + 1) + "");
                    printStream.println("</h2>");
                    printStream.println("<p>");
                        printStream.println(documents.get(i));
                    printStream.println("</p>");
                }
                printStream.println("</pre>");
            printStream.println("</body>");
        printStream.println("</html>");

        printStream.close();

    }

}
