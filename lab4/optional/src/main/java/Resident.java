import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Resident {

    private String name;
    private List<Hospital> preferredHospitals;

    public Resident(String name) {
        this.name = name;
        this.preferredHospitals = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Hospital> getPreferredHospitals() {
        return preferredHospitals;
    }

    public void setPreferredHospitals(List<Hospital> preferredHospitals) {
        this.preferredHospitals = preferredHospitals;
    }

    @Override
    public boolean equals(Object obj) {
        Resident resident = (Resident) obj;
        return name.equals(resident.getName());
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ", "(", ")");
        preferredHospitals.forEach(hospital -> sj.add(hospital.getName()));
        return this.name + " : " + sj.toString();
    }
}
