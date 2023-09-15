package controller;

import java.util.ArrayList;
import java.util.Calendar;

import model.Candidate;
import model.Experience;
import model.Fresher;
import model.Intern;
import view.Menu;
import view.Validation;

public class CandidateManagement extends Menu {

    private static ArrayList<Candidate> candidates = new ArrayList<>();

    public CandidateManagement() {
        super("Candidate Management Menu", new String[]{
            "Create Experience Candidate",
            "Create Fresher Candidate",
            "Create Internship Candidate",
            "Search Candidates",
            "Exit"
        });
    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1:
                createCandidate(0);
                break;
            case 2:
                createCandidate(1);
                break;
            case 3:
                createCandidate(2);
                break;
            case 4:
                searchCandidates();
                break;
            case 5:
                System.out.println("Exiting the program. Goodbye!");
                System.exit(0);
            default:
                System.err.println("Choose 1-5");
        }
    }

    private void createCandidate(int type) {
        while (true) {
            System.out.print("Enter id: ");
            String id = Validation.checkInputString();
            System.out.print("Enter first name: ");
            String firstName = Validation.checkInputString();
            System.out.print("Enter last name: ");
            String lastName = Validation.checkInputString();
            System.out.print("Enter birth date: ");
            int birthDate = Validation.checkInputIntLimit(1900, Calendar.getInstance().get(Calendar.YEAR));
            System.out.print("Enter address: ");
            String address = Validation.checkInputString();
            System.out.print("Enter phone: ");
            String phone = Validation.checkInputPhone();
            System.out.print("Enter email: ");
            String email = Validation.checkInputEmail();

            Candidate candidate = new Candidate(id, firstName, lastName,
                    birthDate, address, phone, email, type);

            switch (type) {
                case 0:
                    createExperience(candidate);
                    break;
                case 1:
                    createFresher(candidate);
                    break;
                case 2:
                    createInternship(candidate);
                    break;
            }

            System.out.print("Do you want to continue (Y/N): ");
            if (!Validation.checkInputYN()) {
                return;
            }
        }
    }

    private void createExperience(Candidate candidate) {
        System.out.print("Enter year of experience: ");
        int yearExperience = Validation.checkInputExprience(candidate.getBirthDate());
        System.out.print("Enter professional skill: ");
        String professionalSkill = Validation.checkInputString();
        candidates.add(new Experience(yearExperience, professionalSkill,
                candidate.getIdCandidate(), candidate.getFirstName(), candidate.getLastName(),
                candidate.getBirthDate(), candidate.getAddress(),
                candidate.getPhone(), candidate.getEmail(), candidate.getTypeCandidate()));
        System.err.println("Create success.");
    }

    private void createFresher(Candidate candidate) {
        System.out.print("Enter graduation date: ");
        String graduationDate = Validation.checkInputString();

        String graduationRank = mapGraduationRank();
        candidates.add(new Fresher(graduationDate, graduationRank, candidate.getIdCandidate(),
                candidate.getFirstName(), candidate.getLastName(),
                candidate.getBirthDate(), candidate.getAddress(),
                candidate.getPhone(), candidate.getEmail(),
                candidate.getTypeCandidate()));
        System.err.println("Create success.");
    }

    private String mapGraduationRank() {
        System.out.print("Enter graduation rank (1: Excellence, 2: Good, 3: Fair, 4: Poor): ");
        int rankInput = Validation.checkInputIntLimit(1, 4);

        switch (rankInput) {
            case 1:
                return "Excellence";
            case 2:
                return "Good";
            case 3:
                return "Fair";
            case 4:
                return "Poor";
            default:
                return "Unknown";
        }
    }

    private void createInternship(Candidate candidate) {
        System.out.print("Enter major: ");
        String major = Validation.checkInputString();
        System.out.print("Enter semester: ");
        String semester = Validation.checkInputString();
        System.out.print("Enter university: ");
        String university = Validation.checkInputString();
        candidates.add(new Intern(major, semester, university, candidate.getIdCandidate(),
                candidate.getFirstName(), candidate.getLastName(),
                candidate.getBirthDate(), candidate.getAddress(),
                candidate.getPhone(), candidate.getEmail(),
                candidate.getTypeCandidate()));
        System.err.println("Create success.");
    }

    private void searchCandidates() {
        printListNameCandidate(candidates);
        System.out.print("Enter candidate name (First name or Last name): ");
        String nameSearch = Validation.checkInputString();
        System.out.print("Enter type of candidate: (0 :Experience; 1: Fresher ; 2: Internship )");
        int typeCandidate = Validation.checkInputIntLimit(0, 2);

        for (Candidate candidate : candidates) {
            if (candidate.getTypeCandidate() == typeCandidate
                    && (candidate.getFirstName().contains(nameSearch)
                    || candidate.getLastName().contains(nameSearch))) {
                System.out.println(candidate.toString());
            }
        }
    }

    private void printListNameCandidate(ArrayList<Candidate> candidates) {
        System.err.println("------------- Experience Candidates -------------");
        for (Candidate candidate : candidates) {
            if (candidate instanceof Experience) {
                System.out.println(candidate.getFirstName() + " " + candidate.getLastName());
            }
        }
        System.err.println("------------- Fresher Candidates ----------------");
        for (Candidate candidate : candidates) {
            if (candidate instanceof Fresher) {
                System.out.println(candidate.getFirstName() + " " + candidate.getLastName());
            }
        }
        System.err.println("------------- Internship Candidates ---------------");
        for (Candidate candidate : candidates) {
            if (candidate instanceof Intern) {
                System.out.println(candidate.getFirstName() + " " + candidate.getLastName());
            }
        }
    }
}
