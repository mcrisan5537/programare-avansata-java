import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class CatalogUtil {

    public static void save(Catalog catalog) {
        try(var oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(catalog.getPath())))) {
            oos.writeObject(catalog);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static Catalog load(String path) throws InvalidCatalogException {

        Catalog catalog = null;
        try (var ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(path)))) {
            catalog = (Catalog) ois.readObject();
        } catch(IOException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            System.out.println("Saved class was not of type \"Catalog\"");
            throw new InvalidCatalogException(e);
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
