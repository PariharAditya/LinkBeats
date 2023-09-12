package com.musicplayer.MusicController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class Player 
{
public static void main(String[] args) throws FileNotFoundException, JavaLayerException 
{
    // sPlayer("Enemy");
}   
public static void sPlayer(String song) throws FileNotFoundException, JavaLayerException 
{
    FileInputStream file = new FileInputStream(song);
    AdvancedPlayer player = new AdvancedPlayer(file);
    player.play();    
} 
}
