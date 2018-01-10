package pl.sdacademy;

public abstract class  Person {

    protected int id;
    protected String name;
    protected String surname;


    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Person() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


}


