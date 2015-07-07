package kz.petsclinic.clinic.palist;

import java.util.Iterator;

/**
 * ������� ���������� ���������
 */
public class SimpleIterator<T> implements Iterator<T> {
    private  final T[] data;
    private int index = 0;

    public SimpleIterator(final T[] data) {
        this.data = data;
    }

    /**
     * �������� �������
     * ���������� ��������
     * � ������� ������ �� �� Null
     * @return ���������� true
     * ���� ���� ��������� �������
     * � �� �� null, false � ��������
     * ������
     */
    @Override
    public boolean hasNext() {
        return index < data.length && data[index] != null;
    }

    /**
     * ���������� ������� ������
     * ���������� �������� ����
     * index
     * @return ������ �� data
     * �� index
     */
    @Override
    public T next() {
        return data[index++];
    }

    /**
     * �������� �� �������������
     * ������� ������������ ������
     * ���������� � ���������������� ��������
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("�������� �� ��������������");
    }
}
