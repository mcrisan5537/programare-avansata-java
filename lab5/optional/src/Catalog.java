import javax.print.Doc;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {

    private String name;
    private String path;
    private List<Document> documents = new ArrayList<>();

    public Catalog() {
    }

    public Catalog(String name, String path) {
        this.name = name;
        this.path = path;
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

    public Document findByID(String id) throws InvalidDocumentException {
        Document document = documents.stream()
                                     .filter(d -> d.getId().equals(id))
                                     .findFirst()
                                     .orElse(null);
        if(document == null)
            throw new InvalidDocumentException();

        return document;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("catalog name=" + name + "\n");
        sb.append("catalog path=" + path + "\n");
        sb.append("catalog number of documents=" + documents.size() + "\n");
        for(Document document : documents)
            sb.append(document);
        return sb.toString();
    }
}
