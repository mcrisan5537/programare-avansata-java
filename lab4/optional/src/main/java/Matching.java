import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Matching {

    private List<Pair<Resident, Hospital>> assignations;

    public Matching() {
        this.assignations = new ArrayList<>();
    }

    public List<Pair<Resident, Hospital>> getAssignations() {
        return assignations;
    }

    public boolean addAssignation(Resident resident, Hospital hospital) {
        return this.assignations.add(new ImmutablePair<>(resident, hospital));
    }

    public boolean isStable(List<Resident> residents, List<Hospital> hospitals) {
        for(Pair<Resident, Hospital> assignation : assignations) {
            Hospital assignedHospital = assignation.getRight();
            List<Hospital> preferredHospitals = assignation.getLeft().getPreferredHospitals();
            int indexOfAssignedHospitalInPreferenceList = preferredHospitals.indexOf(assignedHospital);
            // if there is another hospital which ranked higher than the assigned one
            // and that hospital prefers this resident more over their assigned ones
            // then false
            for(int i = indexOfAssignedHospitalInPreferenceList - 1; i >= 0; i--) {
                Hospital hospital = preferredHospitals.get(i);
                if(hospitalPrefersResidentMore(hospital, assignation.getLeft()))
                    return false;
            }
        }
        return true;
    }

    private boolean hospitalPrefersResidentMore(Hospital hospital, Resident resident) {
        List<Resident> preferredResidents = hospital.getPreferredResidents();
        List<Resident> assignedResidents = getHospitalAssignedResidents(hospital);
        int firstAssignedResidentIndex = 0;
        for(int i = 0; i < preferredResidents.size(); i++)
            if(assignedResidents.contains(preferredResidents.get(i))) {
                firstAssignedResidentIndex = i;
                break;
            }

        for(int i = firstAssignedResidentIndex - 1; i >= 0; i--)
            if(preferredResidents.get(i).equals(resident))
                return true;
        return false;
    }

    private List<Resident> getHospitalAssignedResidents(Hospital hospital) {
        List<Resident> assignedResidents = new ArrayList<>();
        for(Pair<Resident, Hospital> assignation : assignations)
            if(assignation.getRight().equals(hospital))
                assignedResidents.add(assignation.getLeft());
        return assignedResidents;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(",\n", "[", "]");
        for(Pair<Resident, Hospital> assignation : assignations)
            sj.add("(" + assignation.getLeft().getName() + " : " + assignation.getRight().getName() + ")");
        return sj.toString();
    }
}
