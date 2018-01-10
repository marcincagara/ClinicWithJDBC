package pl.sdacademy;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Before;
import pl.sdacademy.database.dao.DMLDAO;
import pl.sdacademy.database.dao.DaoImpl;
import pl.sdacademy.database.dbtables.TableManager;

import java.util.List;


public class AppTest extends TestCase {
    DMLDAO dao;

    public AppTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(AppTest.class);
    }


    public void testApp() {
        assertTrue(true);
    }

    @Before
    public void setUp() {
        dao = new DaoImpl();
    }

    public void testDeleteAllPatients() {
        dao.deleteAllPatients();
        List<Patient> patients = dao.findAll();
        assertTrue(patients.size() == 0);
    }

    public void testFindDoctorByLastName() {
        Doctor doctor = new Doctor("Ferdynand", "Kiepski", "Urolog");
        dao.insertDoctor(doctor);
        Doctor findDoctor = dao.findDoctorByName("Kiepski");
        assertTrue(doctor.getName().equals(findDoctor.getName()));
        assertTrue(doctor.getSurname().equals(findDoctor.getSurname()));
        assertTrue(doctor.getSpecialization().equals(findDoctor.getSpecialization()));
    }

    public void testInsertPatient() {
        Patient patient = new Patient("Andrzej", "Gołota", "Lenox", "goryl", "W starciu z właścicielem pokonuje go już w pierwszej rundzie");
        dao.insertPatient(patient);
        Patient patient2 = new Patient("Steve", "Irwin", "Płaska", "płaszczka", "Niekontrolowane ruchy ogonem");
        dao.insertPatient(patient2);
        Patient patient3 = new Patient("Pierre", "Linguini", "Remy", "szczur", "Dodaje zbyt duzo soli do potraw");
        dao.insertPatient(patient3);
        List<Patient> patients = dao.findAll();
        assertTrue(patients.size() == 3);
        dao.deleteAllPatients();
    }

    public void testUpdatePatient() {
        Patient patient = new Patient("Pierre", "Linguini", "Remy", "szczur", "Dodaje zbyt duzo soli do potraw");
        dao.insertPatient(patient);
        patient.setName("Mark");
        patient.setPetName("Ratty");
        patient.setPetSpecies("mysz");
        dao.updatePatient(patient);
        assertTrue(patient.getName().trim().equals("Mark"));
        assertTrue(patient.getPetName().trim().equals("Ratty"));
        assertTrue(patient.getPetSpecies().trim().equals("mysz"));
        dao.deleteAllPatients();
    }

    public void testDeleteOnePatient() {
        Patient patient = new Patient("Pierre", "Linguini", "Remy", "szczur", "Dodaje zbyt duzo soli do potraw");
        dao.insertPatient(patient);
        List<Patient> patients = dao.findAll();
        patient = patients.get(0);
        assertTrue(patients.size() == 1);
        dao.deletePatient(patient.getId());
        patients = dao.findAll();
        assertTrue(patients.size() == 0);
        dao.deleteAllPatients();
    }

    public void testCreateTables() {
        TableManager.dropAllTables();
        TableManager.createAllTables();
    }

    public void testInsertAppointment() {
        Patient patient = new Patient("Steve", "Irwin", "Płaska", "płaszczka", "Niekontrolowane ruchy ogonem");
        Doctor doctor = new Doctor("Marian", "Pazdzioch", "Neurolog");
        dao.insertPatient(patient);
        List<Patient> patients = dao.findAll();
        patient = patients.get(0);
        dao.insertDoctor(doctor);
        doctor = dao.findDoctorByName("Pazdzioch");
        Appointment appointment = new Appointment(patient.getId(), doctor.getId());
        dao.insertAppointment(appointment);
        List<Appointment> appointments = dao.findAllAppointments();
        assertTrue(appointments.size() == 1);
        dao.deleteAllAppointments();
        dao.deleteAllPatients();
    }

    public void testFindAppointmentByPatientId() {
        Patient patient = new Patient("Pierre", "Linguini", "Remy", "szczur", "Dodaje zbyt duzo soli do potraw");
        Doctor doctor = new Doctor("Marian", "Pazdzioch", "Neurolog");
        dao.insertPatient(patient);
        List<Patient> patients = dao.findAll();
        patient = patients.get(0);
        dao.insertDoctor(doctor);
        doctor = dao.findDoctorByName("Pazdzioch");
        Appointment appointment = new Appointment(doctor.getId(), patient.getId());
        dao.insertAppointment(appointment);
        Appointment findAppointment = dao.findAppointmentByPatientId(patient.getId());
        int patientIDGetFromAppointment = findAppointment.getPatientId();
        assertTrue(patient.getId() == patientIDGetFromAppointment);
        dao.deleteAllAppointments();
        dao.deleteAllPatients();
    }


}
