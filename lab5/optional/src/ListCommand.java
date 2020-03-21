import java.util.List;

public class ListCommand extends Command {

    public static String process(Catalog catalog) throws ShellException {

        if (catalog == null)
            throw new ShellException("Catalog not loaded, load catalog first.");

        StringBuffer sb = new StringBuffer();
        List<Document> documents = catalog.getDocuments();
        for(int i = 0; i < documents.size(); i++) {
            sb.append("\nDocument #" + (i + 1) + "\n");
            sb.append(documents.get(i));
        }
        return sb.toString();
    }

}
