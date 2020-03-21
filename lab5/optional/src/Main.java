import javax.swing.text.View;
import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {

        Main app = new Main();
        app.createCatalogAndSave();

        while(true) {
            try {
                app.myShell();
            } catch (InvalidArgumentShellException e) {
                System.err.println("Invalid argument to command!");
                System.err.println(e);
            } catch (InvalidCommandShellException e) {
                System.err.println("Invalid command!");
                System.err.println(e);
            } catch (ShellException e) {
                System.err.println("Encountered shell error!");
                System.err.println(e);
            }
            System.out.println("Restarting...");
        }

    }

    private void createCatalogAndSave() {
        Catalog catalog = new Catalog("Java resources", "catalog.txt");

        Document document = new Document("java1", "Java course1",
                "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
        document.addTag("Type", "Slides");
        catalog.add(document);

        document = new Document("text1", "fisier text", "./test.txt");
        document.setLocationIsFile(true);
        document.addTag("Type", "Text");
        document.addTag("Content", "ASCII text");
        catalog.add(document);

        CatalogUtil.save(catalog);
    }

    private void myShell() throws ShellException {

        Scanner scanner = new Scanner(System.in);
        String[] splitLine;
        Catalog catalog = null;
        System.out.print("CatalogShell> ");
        while(scanner.hasNextLine()) {
            splitLine = scanner.nextLine().split(" ");

            if(splitLine[0].equals("exit") || splitLine[0].equals("quit"))
                System.exit(1);

            if (splitLine[0].equals("load")) {
                if(splitLine.length == 1) {
                    System.out.println("Specify an argument");
                    String arg = scanner.nextLine();
                    catalog = LoadCommand.process(arg);
                } else {
                    catalog = LoadCommand.process(splitLine[1]);
                }
            } else if (splitLine[0].equals("list")) {
                System.out.println(ListCommand.process(catalog));
            } else if (splitLine[0].equals("view")) {
                if(splitLine.length == 1) {
                    System.out.println("Specify an argument");
                    String arg = scanner.nextLine();
                    ViewCommand.process(catalog, arg);
                } else {
                    ViewCommand.process(catalog, splitLine[1]);
                }
            } else if (splitLine[0].equals("report") && splitLine[1].equals("html")) {
                ReportHTMLCommand.process(catalog);
            } else {
                throw new InvalidCommandShellException("invalid command");
            }

            System.out.print("CatalogShell> ");

        }

    }

}
