package kz.petsclinic.clinic.palist;

import kz.petsclinic.clinic.Person;

import java.util.Iterator;

/**
 * Реазлизация ArrayList для
 * массива Person
 */
public class PArrayList<E> implements Iterable<Person> {

    /**
     * размер массива по умолчанию,
     * ипользуется в конструкторе без
     * параметров
     */
    private static final int DEFAULT_CAPACITY = 10;
    private Person[] data;

    /**
     * количество валидных записей
     * в массиве
     */
    private int size;

    public PArrayList() {
        this.data = new Person[DEFAULT_CAPACITY];
    }

    public PArrayList(final Person[] data) {
        this.data = data;
    }

    /**
     * Добавление новой записи
     * в массив посредством присвоения элементу
     * следующему за последним валидным элементом
     * указателя на новую запись, итерация
     * поля содержащего количество валидных записей
     * @param person новая запись
     * @return true
     */
    public boolean add(final Person person) {
        checkCapacity(size + 1);
        data[size++] = person;
        return true;
    }

    /**
     * Возвращает запись по индексу
     * @param index индекс
     * @return запись по индексу
     */
    public Person get(final int index) {
        checkRange(index);
        return data[index];
    }

    /**
     * Устанавливает новую
     * запись по индексу
     *
     * @param index индекс
     * @param person запись
     * @return возвращает старую
     * запись
     */
    public Person set(final int index, final Person person) {
        checkRange(index);
        Person oldPerson = data[index];
        data[index] = person;
        return oldPerson;
    }

    /**
     * Устанавливает новое имя Персоны
     * для записи по индексу
     * @param index индекс
     * @param newNameOfPerson новое имя Персоны
     * @return возвращает сслыку на персону
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
     * Устанавливает новое имя животного
     * для записи по индексу
     * @param index индекс
     * @param newNameOfPet новое имя животного
     * @return возвращает сслыку на персону
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
     * Удаление персоны по индексу
     * @param index индекс
     * @return возвращает ссылку на
     * удаленную из клиники записб
     */
    public Person remove(final int index) {
        checkRange(index);
        Person oldPerson = data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        data[--size] = null;
        return oldPerson;
    }

    /**
     * Получение индекса записи по
     * имени Персоны
     * @param nameOfPerson имя
     * @return возвращает индекс записи
     * или -1 если персона с таким
     * именем не найдена
     */
    public int getIndexByPersonName(String nameOfPerson) {
        for (int i = 0; i < size; i++) {
            if (nameOfPerson.equals(data[i].getNameOfPerson()))
                return i;
        }
        return -1;
    }

    /**
     * Получение индекса записи по
     * имени животого
     * @param nameOfPet имя животного
     * @return возвращает индекс записи
     * или -1 если персона с таким
     * именем не найдена
     */
    public int getIndexByPetName(String nameOfPet) {
        for (int i = 0; i < size; i++) {
            if (nameOfPet.equals(data[i].getPetName()))
                return i;
        }
        return -1;
    }

    /**
     * Проверка на вхождение индекса
     * в область валидных записей
     * @param index проверяемый индекс
     */
    private void checkRange(final int index) {
        if (index >= size || (index < 0))
            throw new IndexOutOfBoundsException(
                    "index " + index + " is out of 0.." + size);
    }

    /**
     * Возвращает индекс записи по
     * заданной Персоне
     * @param person заданная Персона
     * @return возвращает индекс записи
     * или -1 если персона с таким
     * именем не найдена
     */
    public int indexOf(final Person person) {
        for (int i = 0; i < size; i++) {
            if (person.equals(data[i]))
                return i;
        }
        return -1;
    }

    /**
     * проверка на содержание заданной
     * персоны в записях клиники
     * @param person заданная персона
     * @return возвращает true если персона
     * найдена среди записей, false если нет
     */
    public boolean isContains(final Person person) {
        return indexOf(person) >= 0;
    }

    /**
     * проверка на выход за пределы массива
     * переданного индекса,
     * вызывает метод увеличивающий реальный
     * размер массива.
     * @param index переданый индекс
     */
    private void checkCapacity(final int index) {
        if (index > data.length) changeDataCapacity();
    }

    /**
     * Метод увеличения реального размера массива
     * путем создания нового массива большего размера
     * с копированием в в него содержимого старого
     * нативным методом arraycopy()
     */
    private void changeDataCapacity() {
        int newCapacity = (capacity() * 3) / 2 + 1;
        Person[] oldData = data;
        data = new Person[newCapacity];
        System.arraycopy(oldData, 0, data, 0, size);
    }

    /**
     * Возвращает реальный
     * размер массива
     * @return реальынй размер массива
     */
    private int capacity() {
        return data.length;
    }

    /**
     * Возвращает размер участка
     * массива содержащего валидные
     * элементы
     * @return кол-во валидных
     * элементов
     */
    public int size() {
        return size;
    }

    /**
     * Проверка на наличие записей.
     * @return true если нет записей
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Итератор для
     * возможности использования
     * foreach при работе с записями
     * @return итератор
     */
    @Override
    public Iterator<Person> iterator() {
        return new SimpleIterator(data);
    }
}
