import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class CatalogUtil {

    public static void save(Catalog catalog) {
        try(PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(catalog.getPath())))) {
            printWriter.println(catalog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Catalog load(String path) throws InvalidCatalogException {

        Catalog catalog = new Catalog();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {

            String line = bufferedReader.readLine();
            catalog.setName(line.split("=")[1]);
            line = bufferedReader.readLine();
            catalog.setPath(line.split("=")[1]);

            int noOfDocuments = Integer.parseInt(bufferedReader.readLine().split("=")[1]);
            for(int i = 0; i < noOfDocuments; i++) {

                Document document = new Document();
                //id
                line = bufferedReader.readLine();
                document.setId(line.split("=")[1]);

                //name
                line = bufferedReader.readLine();
                document.setName(line.split("=")[1]);

                //location
                line = bufferedReader.readLine();
                document.setLocation(line.split("=")[1]);

                //locationIsFile
                line = bufferedReader.readLine();
                document.setLocationIsFile(Boolean.parseBoolean(line.split("=")[1]));

                int noOfTags = Integer.parseInt(bufferedReader.readLine().split("=")[1]);
                for(int j = 0; j < noOfTags; j++) {
                    line = bufferedReader.readLine();
                    String[] splitLine = line.split("=");
                    document.addTag(splitLine[0], splitLine[1]);
                }

                catalog.add(document);

            }

        } catch (FileNotFoundException e) {
            throw new InvalidCatalogException("Invalid path to catalog.");
        } catch (Exception e) {
            throw new InvalidCatalogException("failed catalog load", e);
        }

        return catalog;

    }

    public static void view(Document document) throws IOException, URISyntaxException {
        Desktop desktop = Desktop.getDesktop();
        if(document.LocationIsFile()) {
            desktop.open(new File(document.getLocation()));
        } else {
            desktop.browse(new URI(document.getLocation()));
        }
    }

}
