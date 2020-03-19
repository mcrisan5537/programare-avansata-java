import com.github.javafaker.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Problem problem = new Problem();
        problem.generateRandomProblem(10, 3);
        problem.printInstance();

        System.out.println();

        Matching matching = problem.getMatching();
        System.out.println(matching);
        System.out.println(matching.isStable(problem.getResidents(), problem.getHospitals()));

    }

}
