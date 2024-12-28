package ru.vsu.cs.course2.oop.treeset;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomCustomTreeSetTest {

    private CustomCustomTreeSet<Integer> treeSet;

    @BeforeEach
    public void setUp() {
        treeSet = new CustomCustomTreeSet<>();
    }

    @Test
    public void testAddElement() {
        assertTrue(treeSet.add(5));
        assertTrue(treeSet.add(3));
        assertEquals(2, treeSet.size());
    }

    @Test
    public void testAddDuplicateElement() {
        treeSet.add(5);
        assertFalse(treeSet.add(5));
        assertEquals(1, treeSet.size());
    }

    @Test
    public void testAddAllElements() {
        List<Integer> elements = Arrays.asList(1, 2, 3);
        assertTrue(treeSet.addAll(elements));
        assertEquals(3, treeSet.size());
    }

    @Test
    public void testAddAllWithDuplicates() {
        treeSet.add(2);
        List<Integer> elements = Arrays.asList(2, 3, 4);
        assertTrue(treeSet.addAll(elements));
        assertEquals(3, treeSet.size());
    }

    @Test
    public void testClear() {
        treeSet.add(1);
        treeSet.add(2);
        treeSet.clear();
        assertTrue(treeSet.isEmpty());
    }

    @Test
    public void testContainsElement() {
        treeSet.add(3);
        assertTrue(treeSet.contains(3));
        assertFalse(treeSet.contains(5));
    }

    @Test
    public void testContainsAll() {
        treeSet.addAll(Arrays.asList(1, 2, 3));
        assertTrue(treeSet.containsAll(Arrays.asList(1, 2)));
        assertFalse(treeSet.containsAll(Arrays.asList(1, 4)));
    }

    @Test
    public void testRemoveElement() {
        treeSet.add(5);
        assertTrue(treeSet.remove(5));
        assertFalse(treeSet.contains(5));
    }

    @Test
    public void testRemoveNonExistentElement() {
        assertFalse(treeSet.remove(10));
    }

    @Test
    public void testRemoveAllElements() {
        treeSet.addAll(Arrays.asList(1, 2, 3));
        assertTrue(treeSet.removeAll(Arrays.asList(2, 3)));
        assertFalse(treeSet.contains(2));
        assertEquals(1, treeSet.size());
    }

    @Test
    public void testRemoveAllWithNonExistingElements() {
        treeSet.addAll(Arrays.asList(1, 2, 3));
        assertFalse(treeSet.removeAll(Arrays.asList(4, 5)));
    }

    @Test
    public void testSize() {
        assertEquals(0, treeSet.size());
        treeSet.add(1);
        assertEquals(1, treeSet.size());
    }

    @Test
    public void testToArray() {
        treeSet.addAll(Arrays.asList(3, 1, 2));
        Object[] array = treeSet.toArray();
        assertArrayEquals(new Object[]{1, 2, 3}, array);
    }

    @Test
    public void testEquals() {
        CustomCustomTreeSet<Integer> otherSet = new CustomCustomTreeSet<>();
        treeSet.clear();
        treeSet.addAll(Arrays.asList(1, 2, 3));
        otherSet.addAll(Arrays.asList(1, 2, 3));
        assertEquals(otherSet, treeSet);
    }

    @Test
    public void testNotEquals() {
        CustomCustomTreeSet<Integer> otherSet = new CustomCustomTreeSet<>();
        treeSet.addAll(Arrays.asList(1, 2, 3));
        otherSet.addAll(Arrays.asList(4, 5, 6));
        assertNotEquals(treeSet, otherSet);
    }

    @Test
    public void testIsEmpty() {
        assertTrue(treeSet.isEmpty());
        treeSet.add(1);
        assertFalse(treeSet.isEmpty());
    }

    @Test
    public void testHashCode() {
        treeSet.addAll(Arrays.asList(1, 2, 3));
        int expectedHashCode = new HashSet<>(Arrays.asList(1, 2, 3)).hashCode();
        assertEquals(expectedHashCode, treeSet.hashCode());
    }
}