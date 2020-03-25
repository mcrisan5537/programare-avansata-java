import java.io.IOException;
import java.net.URISyntaxException;

public class ViewCommand extends Command {

    public static void process(Catalog catalog, String documentID) throws ShellException {

        if (catalog == null)
            throw new ShellException("Catalog not loaded, load catalog first.");

        try {
            CatalogUtil.view(catalog.findByID(documentID));
        } catch (IOException e) {
            System.err.println("Could not operate with file.");
            e.printStackTrace();
        } catch (URISyntaxException e) {
            System.err.println("Invalid URL");
            e.printStackTrace();
        } catch (InvalidDocumentException e) {
            throw new InvalidArgumentShellException("Invalid argument: " + e.getMessage());
        }

    }

}
