package kz.petsclinic.clinic.palist;

import kz.petsclinic.clinic.Person;

import java.util.Iterator;

/**
 * ����������� ArrayList ���
 * ������� Person
 */
public class PArrayList<E> implements Iterable<Person> {

    /**
     * ������ ������� �� ���������,
     * ����������� � ������������ ���
     * ����������
     */
    private static final int DEFAULT_CAPACITY = 10;
    private Person[] data;

    /**
     * ���������� �������� �������
     * � �������
     */
    private int size;

    public PArrayList() {
        this.data = new Person[DEFAULT_CAPACITY];
    }

    public PArrayList(final Person[] data) {
        this.data = data;
    }

    /**
     * ���������� ����� ������
     * � ������ ����������� ���������� ��������
     * ���������� �� ��������� �������� ���������
     * ��������� �� ����� ������, ��������
     * ���� ����������� ���������� �������� �������
     * @param person ����� ������
     * @return true
     */
    public boolean add(final Person person) {
        checkCapacity(size + 1);
        data[size++] = person;
        return true;
    }

    /**
     * ���������� ������ �� �������
     * @param index ������
     * @return ������ �� �������
     */
    public Person get(final int index) {
        checkRange(index);
        return data[index];
    }

    /**
     * ������������� �����
     * ������ �� �������
     *
     * @param index ������
     * @param person ������
     * @return ���������� ������
     * ������
     */
    public Person set(final int index, final Person person) {
        checkRange(index);
        Person oldPerson = data[index];
        data[index] = person;
        return oldPerson;
    }

    /**
     * ������������� ����� ��� �������
     * ��� ������ �� �������
     * @param index ������
     * @param newNameOfPerson ����� ��� �������
     * @return ���������� ������ �� �������
     */
    public Person setPersonName(int index, String newNameOfPerson) {
        if (index < 0) return null;
        else {
            Person oldPerson = data[index];
            data[index].setNameOfPerson(newNameOfPerson);
            return oldPerson;
        }
    }

    /**
     * ������������� ����� ��� ���������
     * ��� ������ �� �������
     * @param index ������
     * @param newNameOfPet ����� ��� ���������
     * @return ���������� ������ �� �������
     */
    public Person setPetName(int index, String newNameOfPet) {
        if (index < 0) return null;
        else {
            Person oldPerson = data[index];
            data[index].setNameOfPet(newNameOfPet);
            return oldPerson;
        }
    }

    /**
     * �������� ������� �� �������
     * @param index ������
     * @return ���������� ������ ��
     * ��������� �� ������� ������
     */
    public Person remove(final int index) {
        checkRange(index);
        Person oldPerson = data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        data[--size] = null;
        return oldPerson;
    }

    /**
     * ��������� ������� ������ ��
     * ����� �������
     * @param nameOfPerson ���
     * @return ���������� ������ ������
     * ��� -1 ���� ������� � �����
     * ������ �� �������
     */
    public int getIndexByPersonName(String nameOfPerson) {
        for (int i = 0; i < size; i++) {
            if (nameOfPerson.equals(data[i].getNameOfPerson()))
                return i;
        }
        return -1;
    }

    /**
     * ��������� ������� ������ ��
     * ����� ��������
     * @param nameOfPet ��� ���������
     * @return ���������� ������ ������
     * ��� -1 ���� ������� � �����
     * ������ �� �������
     */
    public int getIndexByPetName(String nameOfPet) {
        for (int i = 0; i < size; i++) {
            if (nameOfPet.equals(data[i].getPetName()))
                return i;
        }
        return -1;
    }

    /**
     * �������� �� ��������� �������
     * � ������� �������� �������
     * @param index ����������� ������
     */
    private void checkRange(final int index) {
        if (index >= size || (index < 0))
            throw new IndexOutOfBoundsException(
                    "index " + index + " is out of 0.." + size);
    }

    /**
     * ���������� ������ ������ ��
     * �������� �������
     * @param person �������� �������
     * @return ���������� ������ ������
     * ��� -1 ���� ������� � �����
     * ������ �� �������
     */
    public int indexOf(final Person person) {
        for (int i = 0; i < size; i++) {
            if (person.equals(data[i]))
                return i;
        }
        return -1;
    }

    /**
     * �������� �� ���������� ��������
     * ������� � ������� �������
     * @param person �������� �������
     * @return ���������� true ���� �������
     * ������� ����� �������, false ���� ���
     */
    public boolean isContains(final Person person) {
        return indexOf(person) >= 0;
    }

    /**
     * �������� �� ����� �� ������� �������
     * ����������� �������,
     * �������� ����� ������������� ��������
     * ������ �������.
     * @param index ��������� ������
     */
    private void checkCapacity(final int index) {
        if (index > data.length) changeDataCapacity();
    }

    /**
     * ����� ���������� ��������� ������� �������
     * ����� �������� ������ ������� �������� �������
     * � ������������ � � ���� ����������� �������
     * �������� ������� arraycopy()
     */
    private void changeDataCapacity() {
        int newCapacity = (capacity() * 3) / 2 + 1;
        Person[] oldData = data;
        data = new Person[newCapacity];
        System.arraycopy(oldData, 0, data, 0, size);
    }

    /**
     * ���������� ��������
     * ������ �������
     * @return �������� ������ �������
     */
    private int capacity() {
        return data.length;
    }

    /**
     * ���������� ������ �������
     * ������� ����������� ��������
     * ��������
     * @return ���-�� ��������
     * ���������
     */
    public int size() {
        return size;
    }

    /**
     * �������� �� ������� �������.
     * @return true ���� ��� �������
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * �������� ���
     * ����������� �������������
     * foreach ��� ������ � ��������
     * @return ��������
     */
    @Override
    public Iterator<Person> iterator() {
        return new SimpleIterator(data);
    }
}
