/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simulatorecalciogui;

import javax.swing.JTextArea;

/**
 *
 * @author tadde
 */
public class PartitaRunnable implements Runnable {
    private MainSimulatore m;
    private Squadra s1;
    private Squadra s2;
    private JTextArea t;

    public PartitaRunnable(MainSimulatore m, Squadra s1, Squadra s2, JTextArea t) {
        this.m = m;
        this.s1 = s1;
        this.s2 = s2;
        this.t = t;
    }
    
    @Override
    public void run() {
        m.gioca(s1, s2, t);
    }
}
