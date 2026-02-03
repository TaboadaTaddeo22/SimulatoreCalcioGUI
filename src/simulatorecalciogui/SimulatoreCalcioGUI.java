/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package simulatorecalciogui;

/**
 *
 * @author tadde
 */
public class SimulatoreCalcioGUI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        MainSimulatore m;
        m = new MainSimulatore();
        m.avvia();
        */
        
        InterfacciaFOFA f = new InterfacciaFOFA();
        f.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        f.setVisible(true);
    }
    
}
