/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simulatorecalciogui;

/**
 *
 * @author tadde
 */
import javax.sound.sampled.*;
import java.io.File;

public class AudioGoal {
    public void suonoGoal() {
        try {
            File audioFile = new File("goal.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

            // tiene il programma attivo finché l'audio finisce
            Thread.sleep(clip.getMicrosecondLength() / 1000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void suonoFischioFinale() {
        try {
            File audioFile = new File("fischioFinale.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

            // tiene il programma attivo finché l'audio finisce
            Thread.sleep(clip.getMicrosecondLength() / 1000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

