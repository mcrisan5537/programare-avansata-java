import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Document implements Serializable {

    private String id;
    private String name;
    private String location;
    private boolean locationIsFile;
    private Map<String, Object> tags = new HashMap<>();

    public Document() {
    }

    public Document(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public Document(String id, String name, String location, Map<String, Object> tags) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean LocationIsFile() {
        return locationIsFile;
    }

    public void setLocationIsFile(boolean locationIsFile) {
        this.locationIsFile = locationIsFile;
    }

    public Map<String, Object> getTags() {
        return tags;
    }

    public void addTag(String key, String val) {
        this.tags.put(key, val);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id=" + id + "\n");
        sb.append("name=" + name + "\n");
        sb.append("location=" + location + "\n");
        sb.append("locationIsFile=" + locationIsFile + "\n");
        sb.append("Tags=" + tags.size() + "\n");
        for(String key : tags.keySet())
            sb.append(key + "=" + tags.get(key) + "\n");

        return sb.toString();
    }
}
