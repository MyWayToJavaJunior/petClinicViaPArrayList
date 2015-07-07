package kz.petsclinic.clinic;

import kz.petsclinic.clinic.pets.Pet;

/**
 * Персона, ключевой обьект клиники
 */
public class Person {

    private String nameOfPerson;
    private Pet petOfPerson;

    public Person(final String nameOfPerson, final Pet pet) {
        this.nameOfPerson = nameOfPerson;
        this.petOfPerson = pet;
    }

    public String getNameOfPerson() {
        return nameOfPerson;
    }

    public void setNameOfPerson(final String newNameOfPerson) {
        this.nameOfPerson = newNameOfPerson;
    }

    public String getPetName() {
        return petOfPerson.getPetName();
    }

    public void setNameOfPet(final String newNameOfPet) {
        this.petOfPerson.setPetName(newNameOfPet);
    }

    @Override
    public String toString() {
        return "Pers.name: " + nameOfPerson + ", " + petOfPerson.toString();
    }
}
