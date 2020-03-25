public class LoadCommand extends Command {

    public static Catalog process(String path) throws ShellException {
        Catalog catalog;
        try {
            catalog = CatalogUtil.load(path);
        } catch(InvalidCatalogException e) {
            throw new InvalidArgumentShellException("Invalid catalog path.");
        }
        return catalog;
    }

}
