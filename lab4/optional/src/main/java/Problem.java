import com.github.javafaker.Faker;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem {

    private List<Resident> residents;
    private List<Hospital> hospitals;

    public Problem() {
        residents = new ArrayList<>();
        hospitals = new ArrayList<>();
    }

    public void generateRandomProblem(int noOfResidents, int noOfHospitals) {
        Faker faker = new Faker();
        Random random = new Random();

        // creating random hospitals
        hospitals = IntStream.range(0, noOfHospitals)
                .mapToObj(i -> new Hospital(
                        faker.medical().hospitalName(),
                        1 + Math.abs(random.nextInt()) % noOfResidents))
                .collect(Collectors.toList());

        // creating random residents
        residents = IntStream.range(0, noOfResidents)
                .mapToObj(i -> new Resident(faker.name().name()))
                .collect(Collectors.toList());

        // adding hospital preferences to residents
        Hospital randomUniqueHospital;
        int randomMaxNoOfHospitalPreferences;
        for (Resident resident : residents) {
            randomMaxNoOfHospitalPreferences = 1 + Math.abs(random.nextInt()) % noOfHospitals;
            for (int i = 0; i < randomMaxNoOfHospitalPreferences; i++) {
                // make sure to add hospital only once per resident preference list
                randomUniqueHospital = getRandomUniqueHospital(resident.getPreferredHospitals());
                resident.getPreferredHospitals().add(randomUniqueHospital);
            }
        }

        // adding resident preferences to hospitals
        Resident randomUniqueResident;
        for (Hospital hospital : hospitals)
            for (int i = 0; i < (1 + Math.abs(random.nextInt()) % noOfResidents); i++) {
                // make sure to add resident only once per hospital preference list
                randomUniqueResident = getRandomUniqueResident(hospital.getPreferredResidents());
                hospital.getPreferredResidents().add(randomUniqueResident);
            }

    }

    private Resident getRandomUniqueResident(List<Resident> preferredResidents) {
        Random random = new Random();
        int index;

        do {
            index = Math.abs(random.nextInt()) % residents.size();
        }while(preferredResidents.contains(residents.get(index)));

        return residents.get(index);
    }

    private Hospital getRandomUniqueHospital(List<Hospital> preferredHospitals) {
        Random random = new Random();
        int index;

        do {
            index = Math.abs(random.nextInt()) % hospitals.size();
        }while(preferredHospitals.contains(hospitals.get(index)));

        return hospitals.get(index);
    }

    public Matching getMatching() {
        Matching matching = new Matching();

        // pick first available hospital in resident preference list
        for(Resident resident : residents)
            if(!residentIsAssigned(resident, matching.getAssignations()))
                for(Hospital hospital : resident.getPreferredHospitals())
                    if(hospital.getCapacity() > 0) {
                        matching.addAssignation(resident, hospital); // assign resident to hospital;
                        hospital.decreaseCapacity(); // decrease capacity by 1
                        break; // once resident is assigned to hospital stop looking for hospitals to assign resident to
                    }

        return matching;
    }

    private boolean residentIsAssigned(Resident resident, List<Pair<Resident, Hospital>> assignations) {
        for(Pair<Resident, Hospital> assignation : assignations)
            if(assignation.getLeft().equals(resident))
                return true;
        return false;
    }

    public void printInstance() {
        residents.forEach(System.out::println);
        System.out.println();
        hospitals.forEach(System.out::println);
    }

    public List<Resident> getResidents() {
        return residents;
    }

    public List<Hospital> getHospitals() {
        return hospitals;
    }
}
