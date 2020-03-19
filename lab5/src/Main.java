import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) {

        Main app = new Main();
        app.testCreateSave();
        app.testLoadView();

    }

    private void testCreateSave() {
        Catalog catalog = new Catalog("Java resources", "catalog.ser");
        Document document = new Document("java1", "Java course1",
                "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
        document.addTag("Type", "Slides");

        catalog.add(document);

        CatalogUtil.save(catalog);
    }

    private void testLoadView() {

        try {
            Catalog catalog = CatalogUtil.load("catalog.ser");
            Document document = catalog.findByID("java1");
            CatalogUtil.view(document);
        } catch(InvalidCatalogException e) {
            System.out.println(e);
            e.printStackTrace();
        } catch(IOException e) {
            System.out.println("failed desktop.open()");
            e.printStackTrace();
        } catch(URISyntaxException e) {
            System.out.println("failed desktop.browse()");
            e.printStackTrace();
        }

    }

}
