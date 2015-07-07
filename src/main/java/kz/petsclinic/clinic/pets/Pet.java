package kz.petsclinic.clinic.pets;

/**
 * јбстрактный класс предок дл€ всех
 * классов животных
 */
public abstract class Pet {
    private String nameOfPet;
    private String abilityOfPet;

    public Pet(final String nameOfPet, final String abilityOfPet) {
        this.nameOfPet = nameOfPet;
        this.abilityOfPet = abilityOfPet;
    }

    public String getPetName() {
        return nameOfPet;
    }

    public void setPetName(final String newNameOfPet) {
        this.nameOfPet = newNameOfPet;
    }

    /**
     *
     * @return указатель на
     * экземл€р животного
     */
    public Pet getSelf() {
        return this;
    }

    @Override
    public String toString() {
        return "Pet name is " + nameOfPet + ", ability is " + abilityOfPet;
    }

}
