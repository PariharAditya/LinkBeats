package com.musicplayer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlaylistManagerTest {

    @Test
    void testGetPlaylistEmpty() {
        String result = PlaylistManager.getPlaylist();
        assertEquals("Playlist is empty", result);
    }

    @Test
    void testGetPlaylistNotEmpty() {
        PlaylistManager.addSong("testSong1.mp3");
        PlaylistManager.addSong("testSong2.mp3");
        String result = PlaylistManager.getPlaylist();
        String expected = "testSong1.mp3\ntestSong2.mp3\n";
        assertEquals(expected, result);
    }
}