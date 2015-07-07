package kz.petsclinic.clinic.palist;

import java.util.Iterator;

/**
 * Простая реализация итератора
 */
public class SimpleIterator<T> implements Iterator<T> {
    private  final T[] data;
    private int index = 0;

    public SimpleIterator(final T[] data) {
        this.data = data;
    }

    /**
     * проверка наличия
     * следующего элемента
     * и наличия ссылки не на Null
     * @return возвращает true
     * если есть следующий элемент
     * и он не null, false в обратном
     * случае
     */
    @Override
    public boolean hasNext() {
        return index < data.length && data[index] != null;
    }

    /**
     * Возвращает текущую запись
     * производит итерацию поля
     * index
     * @return запись из data
     * по index
     */
    @Override
    public T next() {
        return data[index++];
    }

    /**
     * удаление не перопределено
     * поэтому выбрасываетя ошибка
     * сообщающая о неподдерживаемой операции
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("операция не поддерживается");
    }
}
