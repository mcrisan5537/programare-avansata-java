import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Hospital implements Comparable<Hospital> {

    private String name;
    private Integer capacity;
    private List<Resident> preferredResidents;

    public Hospital(String name) {
        this.name = name;
        this.preferredResidents = new ArrayList<>();
    }

    public Hospital(String name, Integer capacity) {
        this(name);
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void decreaseCapacity() {
        this.capacity = capacity - 1;
    }

    public List<Resident> getPreferredResidents() {
        return preferredResidents;
    }

    public void setPreferredResidents(List<Resident> preferredResidents) {
        this.preferredResidents = preferredResidents;
    }

    @Override
    public int compareTo(Hospital h) {
        return this.getName().compareTo(h.getName());
    }

    @Override
    public boolean equals(Object obj) {
        Hospital hospital = (Hospital) obj;
        return this.name.equals(hospital.getName());
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ", "(", ")");
        preferredResidents.forEach(resident -> sj.add(resident.getName()));
        return this.name + " (CAPACITY " + this.capacity + ") : "  + sj.toString();
    }
}
