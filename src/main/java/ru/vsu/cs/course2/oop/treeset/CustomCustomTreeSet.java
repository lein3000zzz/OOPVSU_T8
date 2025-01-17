package ru.vsu.cs.course2.oop.treeset;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class CustomCustomTreeSet<E> implements CustomTreeSetInterface<E> {
    private TreeMap<E, Object> treeMap;
    private static final Object PRESENT = new Object();

    public CustomCustomTreeSet() {
        this.treeMap = new TreeMap<>();
    }

    @Override
    public boolean add(E e) {
        return treeMap.put(e, PRESENT) == null;
    }
    @Override
    public boolean addAll(List<? extends E> c) {
        boolean modified = false;
        for (E e : c) {
            if (add(e)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public void clear() {
        treeMap.clear();
    }

    @Override
    public boolean contains(Object o) {
        return treeMap.containsKey(o);
    }

    @Override
    public boolean containsAll(List<? extends E> c) {
        for (E e : c) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomCustomTreeSet)) return false;
        CustomCustomTreeSet<?> other = (CustomCustomTreeSet<?>) o;
        return this.treeMap.keySet().equals(other.treeMap.keySet());
    }

    @Override
    public int hashCode() {
        return treeMap.keySet().hashCode();
    }

    @Override
    public boolean isEmpty() {
        return treeMap.isEmpty();
    }

    @Override
    public boolean remove(Object o) {
        return treeMap.remove(o) == PRESENT;
    }

    @Override
    public boolean removeAll(List<? extends E> c) {
        boolean modified = false;
        for (Object e : c) {
            if (remove(e)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public int size() {
        return treeMap.size();
    }

    @Override
    public Object[] toArray() {
        return treeMap.keySet().toArray();
    }
}
