package pl.sdacademy;

public class Patient extends Person {
    private String illness;
    private String petName;
    private String petSpecies;

    public Patient(String name, String surname, String petName, String petSpecies, String illness) {
        super(name, surname);
        this.illness = illness;
        this.petName = petName;
        this.petSpecies = petSpecies;
    }

    public Patient() {
    }

    public String getIllness() {
        return illness;
    }

    public String getPetName() {
        return petName;
    }

    public String getPetSpecies() {
        return petSpecies;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public void setPetSpecies(String petSpecies) {
        this.petSpecies = petSpecies;
    }


    @Override
    public String toString() {

        return '\n' + "id: " + id + "\n" +
                "Name: " + name + "\n" +
                "Surname: " + surname + "\n" +
                "Pet name: " + petName + "\n" +
                "Pet species: " + petSpecies + "\n" +
                "Illness: " + illness + "\n";
    }
}

