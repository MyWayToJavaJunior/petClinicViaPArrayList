package kz.petsclinic.clinic.palist;

import java.util.Iterator;

/**
 * ѕроста€ реализаци€ итератора
 */
public class SimpleIterator<T> implements Iterator<T> {
    private  final T[] data;
    private int index = 0;

    public SimpleIterator(final T[] data) {
        this.data = data;
    }

    /**
     * проверка наличи€
     * следующего элемента
     * и его валидности(не Null)
     * @return возвращает true
     * если есть следующий элемент
     * и он валиден (не null), false в обратном
     * случае
     */
    @Override
    public boolean hasNext() {
        return index < data.length && data[index] != null;
    }

    /**
     * ¬озвращает текущую запись
     * производит итерацию пол€
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
     * поэтому выбрасывает€ исключение
     * сообщающа€ о неподдерживаемой операции
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("операци€ не поддерживаетс€");
    }
}
