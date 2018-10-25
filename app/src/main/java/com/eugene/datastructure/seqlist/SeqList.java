package com.eugene.datastructure.seqlist;

public class SeqList<T> implements ISeqList<T> {

    private Object[] table;
    /**
     * 当前元素个数
     */
    private int length;

    private static final int DEF_CAPACITY = 16;

    public SeqList() {
        table = new Object[DEF_CAPACITY];
    }

    public SeqList(int capacity) {
        table = new Object[capacity];
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= length)
            return null;
        return (T) table[index];
    }

    @Override
    public T set(int index, T data) {
        if (index < 0 || index >= length || data == null)
            return null;
        T old = (T) table[index];
        table[index] = data;
        return old;
    }

    @Override
    public boolean add(int index, T data) {
        if (data == null)
            return false;

        //插入下标的容错判断,插入在最前面
        if (index < 0)
            index = 0;

        //插入下标的容错判断,插入在最后面
        if (index > length)
            index = length;

        //判断内部数组是否已满，如果数组满了，需要对其进行扩容
        if (length == table.length) {
            growCapacity();
        }

        //从最后一个位置开始，整体后移一位，知道index位置
        for (int i = length - 1; i >= index; i--) {
            table[i + 1] = table[i];
        }

        table[index] = data;
        length++;
        return true;
    }

    @Override
    public T remove(int index) {
        if (length == 0 && index < 0 || index > length)
            return null;

        T old = (T) table[index];

        //从被删除的元素位置开,其后的元素都依次往前移动
        for (int i = index; i < length - 1; i++) {
            table[i] = table[i + 1];
        }

        //把末尾位置设置为null
        table[length - 1] = null;
        length--;
        return old;
    }

    @Override
    public boolean remove(T data) {
        if (length == 0 || data == null)
            return false;

        //依次进行查找
        for (int i = 0; i < length; i++) {
            if (table[i].equals(data)) {
                //查找到目标就删除，不再继续查找
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeAll(T data) {
        if (length == 0 || data == null)
            return false;

        boolean done = false;
        int i = 0;
        while (i < length) {
            //找出数据相同的选项
            if (table[i].equals(data)) {
                //根据下标删除
                remove(i);
                done = true;
            } else {
                //继续查找
                i++;
            }
        }
        return done;
    }

    @Override
    public void clear() {
        length = 0;
    }

    @Override
    public boolean contains(T data) {
        return indexOf(data) >= 0;
    }

    @Override
    public int indexOf(T data) {
        if (data != null) {
            for (int i = 0; i < length; i++) {
                if (table[i].equals(data)) {
                    //相当则返回下标
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T data) {
        if (data != null) {
            //从末尾开始查找
            for (int i = length - 1; i >= 0; i--) {
                if (table[i].equals(data)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 扩容数组
     */
    private void growCapacity() {
        Object[] temp = new Object[table.length * 2];
        System.arraycopy(table, 0, temp, 0, table.length);
        table = temp;
    }

}
