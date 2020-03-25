import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.*;
import java.util.StringTokenizer;

//       info: display the metadata of a specific file: (you may want to use Apache Tika or something similar).
public class InfoCommand extends Command {

    public static void process(String fileName) throws ShellException {

//        Mar 25, 2020 5:21:07 PM org.apache.tika.config.InitializableProblemHandler$3 handleInitializableProblem
//        WARNING: J2KImageReader not loaded. JPEG2000 files will not be processed.
//        See https://pdfbox.apache.org/2.0/dependencies.html#jai-image-io
//        for optional dependencies.
//
//        Mar 25, 2020 5:21:07 PM org.apache.tika.config.InitializableProblemHandler$3 handleInitializableProblem
//        WARNING: org.xerial's sqlite-jdbc is not loaded.
//        Please provide the jar on your classpath to parse sqlite files.
//                See tika-parsers/pom.xml for the correct version.

//        Imi apar warning-urile de mai sus, motiv pentru care am setat stderr la /dev/null
//        si am afisat erorile la stdout
        try {
            System.setErr(new PrintStream(new FileOutputStream("/dev/null")));
        } catch (Exception e) {
            // ignore
        }

        AutoDetectParser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream("report.html"))) {
            parser.parse(inputStream, handler, metadata);
        } catch (FileNotFoundException e) {
            throw new InvalidArgumentShellException("Invalid catalog path.");
        } catch (IOException e) {
            System.out.println("IO Error when parsing " + fileName + ".");
            e.printStackTrace();
        } catch (TikaException e) {
            System.out.println("TikaException when parsing " + fileName + ".");
            e.printStackTrace();
        } catch (SAXException e) {
            System.out.println("SAXException when parsing " + fileName + ".");
            e.printStackTrace();
        }

        StringTokenizer separatedString = new StringTokenizer(metadata.toString(), " ");
        while (separatedString.hasMoreElements())
            System.out.println(separatedString.nextElement());

        System.setErr(new PrintStream(new FileOutputStream(FileDescriptor.err)));
    }

}
