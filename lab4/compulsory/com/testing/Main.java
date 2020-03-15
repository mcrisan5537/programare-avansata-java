package com.testing;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        // COMPULSORY

        // Create all the objects in the example (one by one or using streams).
        var residentsArray = IntStream.rangeClosed(0,3)
                                            .mapToObj(i -> new Resident("R" + i))
                                            .toArray(Resident[]::new);

        var hospitalsArray = IntStream.rangeClosed(0,2)
                                            .mapToObj(i -> new Hospital("H" + i))
                                            .toArray(Hospital[]::new);

        // Create a list of residents, using an ArrayList implementation. Sort the residents, using a comparator.
        List<Resident> residentsList = new ArrayList<>(Arrays.asList(residentsArray));
        residentsList.sort((r1, r2) -> r1.getName().compareTo(r2.getName()));

        // Create a set of hospitals, using a TreeSet implementation. Make sure that Hospital objects are comparable.
        Set<Hospital> hospitalsSet = new TreeSet<>(Arrays.asList(hospitalsArray));

        //Create two maps (having different implementations) describing the residents and the hospital preferences and print them on the screen.
        Map<Resident, List<Hospital>> resPrefMap = new HashMap<>();
        resPrefMap.put(residentsArray[0], Arrays.asList(hospitalsArray[0], hospitalsArray[1], hospitalsArray[2]));
        resPrefMap.put(residentsArray[1], Arrays.asList(hospitalsArray[0], hospitalsArray[1], hospitalsArray[2]));
        resPrefMap.put(residentsArray[2], Arrays.asList(hospitalsArray[0], hospitalsArray[1]));
        resPrefMap.put(residentsArray[3], Arrays.asList(hospitalsArray[0], hospitalsArray[2]));

        Map<Hospital, List<Resident>> hosPrefMap = new Hashtable<>();
        hosPrefMap.put(hospitalsArray[0], Arrays.asList(residentsArray[3], residentsArray[0], residentsArray[1], residentsArray[2]));
        hosPrefMap.put(hospitalsArray[1], Arrays.asList(residentsArray[0], residentsArray[2], residentsArray[1]));
        hosPrefMap.put(hospitalsArray[2], Arrays.asList(residentsArray[0], residentsArray[1], residentsArray[3]));

        // Using Java Stream API, write queries that display the residents who find acceptable H0 and H2, and the hospitals that have R0 as their top preference.
        residentsList.stream()
                     .filter(r -> resPrefMap.get(r).contains(hospitalsArray[0]) && resPrefMap.get(r).contains(hospitalsArray[2]))
                     .forEach(System.out::println);

        System.out.println();

        hospitalsSet.stream()
                    .filter(h -> hosPrefMap.get(h).get(0).getName().equals("R0"))
                    .forEach(System.out::println);

    }
}
