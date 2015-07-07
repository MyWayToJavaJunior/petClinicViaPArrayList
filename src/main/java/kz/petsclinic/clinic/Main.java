package kz.petsclinic.clinic;

import kz.petsclinic.clinic.inputotput.IO;
import kz.petsclinic.clinic.palist.PArrayList;
import kz.petsclinic.clinic.pets.Bird;
import kz.petsclinic.clinic.pets.Cat;
import kz.petsclinic.clinic.pets.Dog;
import kz.petsclinic.clinic.pets.Pet;

/**
 * Основной исполняемый класс
 */
public class Main {

    private static boolean mainMenuSelector = true;
    private static Pet pet;

    public static void main(String[] args) {
        PArrayList<Person> clinic = new PArrayList<>();
        showAll(clinic);
        while (mainMenuSelector) {
            MainMenu(clinic);
        }

    }

    /**
     * основное меню в котором выводится
     * список операций, и пользователь
     * делает выбор посредством введения
     * номера операции
     * выполняется в цикле, пока пользователь
     * не выберет выход в меню
     * @param clinic клиника
     */
    private static void MainMenu(final PArrayList<Person> clinic) {
        IO.showMainMenu();
        String userChoice = IO.getUserChoice();
        switch (userChoice) {
            default:    break;
            case "1":   showAll(clinic);
                        break;
            case "2":   add(clinic);
                        break;
            case "3":   removeByPerson(clinic);
                        break;
            case "4":   renameByPerson(clinic);
                        break;
            case "5":   viewByPerson(clinic);
                        break;
            case "6":   removeByPet(clinic);
                        break;
            case "7":   renameByPet(clinic);
                        break;
            case "8":   viewByPet(clinic);
                        break;
            case "0":   turnOffSelector();
                         break;
        }
    }

    /**
     * Выводит первую запись
     * содержащую животное с именем
     * которое введет пользователь
     * @param clinic клиника
     */
    private static void viewByPet(final PArrayList<Person> clinic) {
        int indexViewPerson = clinic.getIndexByPetName(IO.askAndGetNameOfPet());
        if (indexViewPerson < 0) IO.notFound();
        else IO.print(clinic.get(indexViewPerson).toString());
    }

    /**
     * Удаляет первую запись
     * содержащю животное с именем
     * которое введет пользователь
     * @param clinic клиника
     */
    private static void removeByPet(final PArrayList<Person> clinic) {
        int indexRemPerson = clinic.getIndexByPetName(IO.askAndGetNameOfPetForRemoving());
        if (indexRemPerson < 0) IO.notFound();
        else IO.personRemoved(clinic.remove(indexRemPerson).toString());
    }

    /**
     * Переименовывание животного
     * @param clinic клиника
     */
    private static void renameByPet(final PArrayList<Person> clinic) {
        int indexRenPerson = clinic.getIndexByPetName(IO.askAndGetNameOfPetForRename());
        if (indexRenPerson < 0) IO.notFound();
        else {
            IO.personRemoved(clinic.setPetName(indexRenPerson, IO.askAndGetNewNameForPet()).toString());
        }
    }

    /**
     * Выводит запись
     * содержащую Персону с именем
     * которое введет пользователь
     * @param clinic клиника
     */
    private static void viewByPerson(final PArrayList<Person> clinic) {
        int indexViewPerson = clinic.getIndexByPersonName(IO.askAndGetNameOfPersonForView());
        if (indexViewPerson < 0) IO.notFound();
        else IO.print(clinic.get(indexViewPerson).toString());
    }

    /**
     * Удаляет первую запись
     * содержащю Персону с именем
     * которое введет пользователь
     * @param clinic клиника
     */
    private static void removeByPerson(final PArrayList<Person> clinic) {
        int indexRemPerson = clinic.getIndexByPersonName(IO.askAndGetNameOfPersonForRemoving());
        if (indexRemPerson < 0) IO.notFound();
        else {
            IO.personRemoved(clinic.remove(indexRemPerson).toString());
        }
    }

    /**
     * Переименование Персоны
     * @param clinic клиника
     */
    private static void renameByPerson(final PArrayList<Person> clinic) {
        int indexRenPerson = clinic.getIndexByPersonName(IO.askAndGetNameOfPersonForRename());
        if (indexRenPerson < 0) IO.notFound();
        else {
            IO.personRenamed(clinic.setPersonName(indexRenPerson, getValidNameForPerson(clinic)).toString());
        }
    }

    /**
     * Получение нового имени для
     * Персоны посредством пользовательского
     * ввода, происходит проверка используется
     * ли уже введенное имя
     * @param clinic клиника
     * @return новое имя для Персоны
     */
    private static String getValidNameForPerson(final PArrayList<Person> clinic) {
        String newNameForPerson;
        boolean selector = true;
        do {
            newNameForPerson = IO.askAndGetNewNameForPerson();
            if (clinic.getIndexByPersonName(newNameForPerson) < 0) selector = false;
            else IO.nameAlreadyUsed();
        } while (selector);
        return newNameForPerson;
    }

    /**
     * Метод переводит поле отвечающее
     * за проверку продолжения исполнения цикла
     * основного меню в "выключенное состояние"
     * присваивая ему значение false
     */
    private static void turnOffSelector() {
        mainMenuSelector = false;
    }

    /**
     * Добавление новой записи
     * в клинику, позльзовательский
     * ввод
     * @param clinic клиника
     */
    private static void add(final PArrayList<Person> clinic) {
        String nameForPerson = getValidNameForPerson(clinic);
        Pet newPet = getPetFromUserInput();
        Person person = new Person(nameForPerson, newPet);
        clinic.add(person);
    }

    /**
     * Выводит все записи клиники
     * если клиника не пустая
     * @param clinic клиника
     */
    public static void showAll(final PArrayList<Person> clinic) {
        if (clinic.isEmpty()) IO.clinicIsEmpty();
        else
        {
            for (Person person : clinic) {
                System.out.println(person.toString());
            }
        }
    }

    /**
     * Часть процедуры добавления новой
     * записи в клинику, отвечает за ввод
     * пользователем данных животного
     * @return возвращает животное
     */
    public static Pet getPetFromUserInput() {
        String petName = IO.askAndGetNameForPet();
        IO.askForPetType();
        Pet pet;
        switch (IO.getUserChoice()) {
            default:
            case "1":   pet = new Cat(petName);
                        break;
            case "2":   pet = new Bird(petName);
                        break;
            case "3":   pet = new Dog(petName);
        }
        return pet;
    }
}
