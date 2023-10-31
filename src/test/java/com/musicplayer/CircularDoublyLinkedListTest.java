package com.musicplayer;

import org.junit.Test;
import static org.junit.Assert.*;

public class CircularDoublyLinkedListTest {
    @Test
    public void testAddNodeToEmptyList() {
        CircularDoublyLinkedList list = new CircularDoublyLinkedList();

        // Add the first node
        list.addNode("A");
        assertEquals("A", list.getFirst().getData());
        assertEquals(list.getFirst(), list.getFirst().getNext());
        assertEquals(list.getFirst(), list.getFirst().getPrevious());
    }

}