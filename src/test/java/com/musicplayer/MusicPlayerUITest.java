package com.musicplayer;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MusicPlayerUITest {
    private FrameFixture window;

    @BeforeEach
    void setUp() {
        MusicPlayerUI frame = GuiActionRunner.execute(() -> new MusicPlayerUI());
        window = new FrameFixture(frame);
        window.show(); // shows the frame to test
    }

    @Test
    void testPlayButton() {
        window.button("Play").click();
        // Add assertions to check the effect of clicking the play button
    }

    @Test
    void testStopButton() {
        window.button("Stop").click();
        // Add assertions to check the effect of clicking the stop button
    }

    @AfterEach
    void tearDown() {
        window.cleanUp();
    }
}