package com.musicplayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CircularDoublyLinkedListTest {
    private CircularDoublyLinkedList playlist;

    @BeforeEach
    void setUp() {
        playlist = new CircularDoublyLinkedList();
    }

    @Test
    void testAddNode() {
        assertTrue(playlist.isEmpty());
        playlist.addNode("Song1");
        assertFalse(playlist.isEmpty());
        assertEquals("Song1", playlist.getCurrentSong());
    }

    @Test
    void testPlayNextSong() {
        playlist.addNode("Song1");
        playlist.addNode("Song2");
        assertEquals("Song1", playlist.getCurrentSong());
        playlist.playNextSong();
        assertEquals("Song2", playlist.getCurrentSong());
        playlist.playNextSong();
        assertEquals("Song1", playlist.getCurrentSong()); // It's a circular list
    }

    @Test
    void testPlayPreviousSong() {
        playlist.addNode("Song1");
        playlist.addNode("Song2");
        assertEquals("Song1", playlist.getCurrentSong());
        playlist.playPreviousSong();
        assertEquals("Song2", playlist.getCurrentSong()); // It's a circular list
        playlist.playPreviousSong();
        assertEquals("Song1", playlist.getCurrentSong());
    }
}