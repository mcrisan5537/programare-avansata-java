import javax.print.Doc;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {

    private String name;
    private String path;
    private List<Document> documents;

    public Catalog(String name, String path) {
        this.name = name;
        this.path = path;
        this.documents = new ArrayList<>();
    }

    public Catalog(String name, String path, List<Document> documents) {
        this.name = name;
        this.path = path;
        this.documents = documents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public boolean add(Document document) {
        return this.documents.add(document);
    }

    public Document findByID(String id) {
        return documents.stream()
                        .filter(document -> document.getId().equals(id))
                        .findFirst()
                        .orElse(null);
    }

}
