package pl.sdacademy;

import pl.sdacademy.database.dao.DMLDAO;
import pl.sdacademy.database.dao.DaoImpl;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private static DMLDAO dbExecutor = new DaoImpl();
    private static Scanner sc = new Scanner(System.in);
    private static int chosenItemMenu;
    private static int chosenItemMenu2;
    private static boolean finish = true;
    private static boolean finish2;
    private static boolean finishMain = true;

    public static boolean isFinishMain() {
        return finishMain;
    }

    public static void showMenu() {
        System.out.println("Choose 1 to log in as a doctor or 2 to log in as a patient");
        Scanner sc = new Scanner(System.in);
        chosenItemMenu = sc.nextInt();
        switch (chosenItemMenu) {
            case 1:
                finish = true;
                showDoctorMenu();
                break;
            case 2:
                finish = true;
                showPatientMenu();
                break;
            default:
                System.out.println("Wrong input");
                break;
        }
    }


    private static void showPatientMenu() {
        while (finish) {
            System.out.println("1. Patient information    2. Search a doctor   3. Make an appointment    8. Exit");
            Scanner sc = new Scanner(System.in);
            chosenItemMenu = sc.nextInt();
            switch (chosenItemMenu) {
                case 1:
                    readPatientData();
                    break;
                case 2:
                    searchDoctor();
                    break;
                case 3:
                    makeAnAppointment();
                    break;
                case 8:
                    finishMain = false;
                    finish = false;
                    break;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }

    }

    private static void readPatientData() {
        System.out.println("wprowadz swoje id");
        int id = sc.nextInt();
        Patient patient = dbExecutor.findPatientById(id);
        Appointment appointment = dbExecutor.findAppointmentByPatientId(patient.getId());
        if (patient != null) {
            System.out.print(patient);
            System.out.println("Appointments" + appointment.getDate());
        } else
            System.out.println("brak pacjenta w bazie");
    }


    private static void showDoctorMenu() {
        while (finish) {
            System.out.println("1. Show patients database    2. Search a patient     3. Add a patient      4. Delete a patient     5. Update patient information   8. Exit ");
            Scanner sc = new Scanner(System.in);
            chosenItemMenu = sc.nextInt();
            switch (chosenItemMenu) {
                case 1:
                    findAllPatients();
                    break;
                case 2:
                    searchPatient();
                    break;
                case 3:
                    addPatient();
                    break;
                case 4:
                    deletePatientAndHisAppointments();
                    break;
                case 5:
                    updatePatient();
                    break;
                case 8:
                    finish = false;
                    break;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }

    }

    private static void updatePatient() {
        System.out.println("wprowadz id pacjenta");
        int ID = sc.nextInt();
        Patient patient = dbExecutor.findPatientById(ID);
        finish2 = true;
        if (patient != null) {
            while (finish2) {
                System.out.println("Wybierz co chcesz zaktualizowac");
                System.out.println(" 1. Name     2. Surname    3. Pet Name     4. Pet Species     5. Illness     8. Exit ");
                chosenItemMenu2 = sc.nextInt();
                switch (chosenItemMenu2) {
                    case 1:
                        System.out.println("Wprowadz prawidlowe Imie");
                        String newName = sc.next();
                        patient.setName(newName);
                        System.out.println("Imie zostalo zaktualizowane");
                        System.out.println("Czy chcesz dalej aktualizowac dane? (T/N)");
                        char answer = sc.next().charAt(0);
                        if (answer == 'N') {
                            finish2 = false;
                            break;
                        }
                        break;
                    case 2:
                        System.out.println("Wprowadz prawidlowe Nazwisko");
                        String newSurname = sc.next();
                        patient.setSurname(newSurname);
                        System.out.println("Nazwisko zostalo zaktualizowane");
                        System.out.println("Czy chcesz dalej aktualizowac dane? (T/N)");
                        char answer2 = sc.next().charAt(0);
                        if (answer2 == 'N') {
                            finish2 = false;
                            break;
                        }
                        break;
                    case 3:
                        System.out.println("Wprowadz prawidlowe Imie zwierzaka");
                        String newPetName = sc.next();
                        patient.setPetName(newPetName);
                        System.out.println("Imie zwierzaka zostalo zaktualizowane");
                        System.out.println("Czy chcesz dalej aktualizowac dane? (T/N)");
                        char answer3 = sc.next().charAt(0);
                        if (answer3 == 'N') {
                            finish2 = false;
                            break;
                        }
                        break;
                    case 4:
                        System.out.println("Wprowadz gatunek zwierzaka");
                        String newPetSpecies = sc.next();
                        patient.setPetSpecies(newPetSpecies);
                        System.out.println("Gatunek zostal zaktualizowany");
                        System.out.println("Czy chcesz dalej aktualizowac dane? (T/N)");
                        char answer4 = sc.next().charAt(0);
                        if (answer4 == 'N') {
                            finish2 = false;
                            break;
                        }
                        break;
                    case 5:
                        System.out.println("Wprowadz chorobe zwierzaka");
                        String newPetIlness = sc.next();
                        patient.setIllness(newPetIlness);
                        System.out.println("Choroba zostala zaktualizowana");
                        System.out.println("Czy chcesz dalej aktualizowac dane? (T/N)");
                        char answer5 = sc.next().charAt(0);
                        if (answer5 == 'N') {
                            finish2 = false;
                            break;
                        }
                        break;
                    case 8:
                        finish2 = false;
                        break;
                    default:
                        System.out.println("Wrong input");
                        break;
                }
                dbExecutor.updatePatient(patient);
            }
        } else {
            System.out.println("brak pacjenta w bazie");
        }
    }

    private static void deletePatientAndHisAppointments() {
        System.out.println("wprowadz id pacjenta");
        int patiendId = sc.nextInt();
        System.out.println("podaj id wizyty do usuniecia");
        int appointmentId = sc.nextInt();
        deleteAppointment(appointmentId);
        Patient patient = dbExecutor.findPatientById(patiendId);
        if (patient != null) {
            dbExecutor.deletePatient(patiendId);
            System.out.println("Pacjent o id: " + patiendId + " zostal usuniety");
        } else {
            System.out.println("Nie ma takiego pacjenta");
        }
    }

    private static void deleteAppointment(int id) {
        Appointment appointment = dbExecutor.findAppointmentById(id);
        if (appointment != null) {
            dbExecutor.deleteAppointment(id);
            System.out.println("Wizyta o id: " + id + " zostal usuniety");
        } else {
            System.out.println("Nie ma takiego pacjenta");
        }
    }

    private static void searchPatient() {
        System.out.println("wprowadz id pacjenta");
        int id = sc.nextInt();
        Patient patient = dbExecutor.findPatientById(id);
        if (patient != null) {
            System.out.println(patient);
        } else {
            System.out.println("brak pacjenta w bazie");
        }
    }


    private static void addPatient() {
        System.out.println("podaj imie właściciela");
        String name = sc.next();
        System.out.println("podaj nazwisko właściciela");
        String surname = sc.next();
        System.out.println("podaj imie zwierzaka");
        String petName = sc.next();
        System.out.println("podaj gatunek zwierzaka");
        String petSpecies = sc.next();
        System.out.println("podaj chorobe");
        String illness = sc.next();

        Patient toAdd = new Patient(name, surname, petName, petSpecies, illness);
        dbExecutor.insertPatient(toAdd);
        System.out.println("Pacjent zostal dodany");
    }


    private static void findAllPatients() {
        List<Patient> patients = dbExecutor.findAll();
        for (Patient patient : patients) {
            Appointment appointment = dbExecutor.findAppointmentByPatientId(patient.getId());
            System.out.print(patient);
            System.out.println("Appointments" + appointment.getDate());
        }
    }

    private static void searchDoctor() {
        System.out.println("wprowadz nazwisko lekarza");
        String surname = sc.next();
        Doctor doctor = dbExecutor.findDoctorByName(surname);
        if (doctor != null) {
            System.out.println(doctor);
        } else {
            System.out.println("Nie znaleziono doktora o takim nazwisku");
        }
    }

    private static void makeAnAppointment() {
        System.out.println("wpisz nazwisko lekarza");
        String surname = sc.next();
        System.out.println("Podaj swoje id");
        int patientId = sc.nextInt();
        Doctor doctor = dbExecutor.findDoctorByName(surname);
        Patient patient = dbExecutor.findPatientById(patientId);
        Appointment appointment = new Appointment(doctor.getId(), patient.getId());
        if (doctor != null && patient != null) {
            dbExecutor.insertAppointment(appointment);
            dbExecutor.updatePatient(patient);
            System.out.println("Twoja wizyta bedzie " + appointment.getDate());
        } else {
            System.out.println("brak lekarza w bazie");
        }

    }
}
