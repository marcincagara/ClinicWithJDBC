package pl.sdacademy;

public class Doctor extends Person {

    private String specialization;


    public Doctor(String Name, String Surname, String specialization) {
        super(Name, Surname);
        this.specialization = specialization;
    }

    public Doctor() {
    }


    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }


    @Override
    public String toString() {
        return "id: " + id + '\n' +
                "Name: " + name + '\n' +
                "Surname: " + surname + '\n' +
                "specialization: " + specialization + '\n';
    }
}
