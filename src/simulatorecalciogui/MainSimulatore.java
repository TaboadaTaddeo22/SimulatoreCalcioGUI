/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simulatorecalciogui;

import java.awt.Image;
import java.util.*;
import java.io.*;
import java.net.URL;
import javax.swing.*;

/**
 * Classe MainSimulatore
 * @author casolaro.diego
 */
public class MainSimulatore {
    // Attributi
    private ArrayList<Squadra> squadre;
    private HashSet<String> campionati;
    private AudioGoal aG;

    public MainSimulatore() {
        this.squadre = new ArrayList<>();
        this.campionati = new HashSet<>();
        this.aG = new AudioGoal();
    }
    
    public void avvia() {
        try (BufferedReader br = new BufferedReader(new FileReader("calciatoriFOFA.txt"))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] campi = linea.split(",");

                String nome = campi[0];
                String cognome = campi[1];
                int overall = Integer.parseInt(campi[2]);
                String ruolo = campi[3];
                String squadra = campi[4];
                Giocatore g;
                Squadra s = null;
                
                switch (ruolo) {
                    case "Portiere": g = new Portiere(nome, cognome, overall, overall);
                        break;
                    case "Difensore": g = new Difensore(nome, cognome, overall);
                        break;
                    case "Centrocampista": g = new Centrocampista(nome, cognome, overall);
                        break;
                    case "Attaccante": g = new Attaccante(nome, cognome, overall);
                        break;
                    default: g = null;
                        break;
                }
                
                for (Squadra sq : squadre) {
                    if (sq.getNome().equals(squadra)) {
                    s = sq;
                    break;
                    }
                }
                
                if (s == null) {
                    s = new Squadra(squadra, squadra);
                    squadre.add(s);
                }
                
                if (g != null) {
                    s.aggiungiGiocatore(g);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader("squadreCampionatiFOFA.txt"))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] campi = linea.split(",");

                String squadra = campi[0];
                String campionato = campi[1];
                ImageIcon image;
                
                for (Squadra s : squadre) {
                    if (squadra.equals(s.getNome())) {
                        s.setCampionato(campionato);
                        
                        //
                        String squadraTrim = s.getNome().trim();
                        String campionatoTrim = s.getCampionato().trim();
                        String path = "/immagini/immagini/" + squadraTrim + ", " + campionatoTrim + " logo png transparent logo png/";
                        
                        
                        URL url = getClass().getResource(path + "Image_1.png");

                        if (url == null) {
                            url = getClass().getResource(path + "Image_1.jpg");
                        }
                        if (url == null) {
                            url = getClass().getResource(path + "Image_1.gif");
                        }
                        if (url == null) {
                            url = getClass().getResource(path + "Image_1.webp");
                        }

                        if (url == null) {
                            System.out.println("IMMAGINE NON TROVATA per: " + squadraTrim);
                        } else {
                            image = new ImageIcon(url);
                            s.setImmagine(image);
                        }
                    }
                }
                
                campionati.add(campionato);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Stampa di controllo
        for (Squadra sq : squadre) {
            System.out.println(sq.getNome());
        }
        
        System.out.println("\n");
        
        for (String s : campionati) {
            System.out.println(s);
        }
    }
    
    public void gioca(Squadra sCasa, Squadra sTrasferta, JTextArea tA, JLabel lblC, JLabel lblT) {
        GestoreMessaggi gM = new GestoreMessaggi(tA);
        Scanner input = new Scanner(System.in);
        Random rnd = new Random();
        
        Random rand = new Random();
        Squadra sCasaTitolare = new Squadra(sCasa.getNome(), sCasa.getStadio());
        boolean portiereInserito = false;

        while (sCasaTitolare.getGiocatori().size() < 11) {
            int indice = rand.nextInt(sCasa.getGiocatori().size());
            Giocatore g = sCasa.getGiocatori().get(indice);

            while (sCasaTitolare.getGiocatori().contains(g)) {
                indice = rand.nextInt(sCasa.getGiocatori().size());
                g = sCasa.getGiocatori().get(indice);
            }
            
            while (!portiereInserito) {
                if (g instanceof Portiere portiere) {
                    sCasaTitolare.aggiungiGiocatore(g);
                    portiereInserito = true;
                } else {
                    indice = rand.nextInt(sCasa.getGiocatori().size());
                    g = sCasa.getGiocatori().get(indice);
                }
            }

            if (!(g instanceof Portiere portiere)) {
                sCasaTitolare.aggiungiGiocatore(g);
            }
        }       
        
        Squadra sTrasfertaTitolare = new Squadra(sTrasferta.getNome(), sTrasferta.getStadio());
        portiereInserito = false;
        
        while (sTrasfertaTitolare.getGiocatori().size() < 11) {
            int indice = rand.nextInt(sTrasferta.getGiocatori().size());
            Giocatore g = sTrasferta.getGiocatori().get(indice);

            while (sCasaTitolare.getGiocatori().contains(g)) {
                indice = rand.nextInt(sCasa.getGiocatori().size());
                g = sCasa.getGiocatori().get(indice);
            }
            
            while (!portiereInserito) {
                if (g instanceof Portiere portiere) {
                    sTrasfertaTitolare.aggiungiGiocatore(g);
                    portiereInserito = true;
                } else {
                    indice = rand.nextInt(sTrasferta.getGiocatori().size());
                    g = sTrasferta.getGiocatori().get(indice);
                }
            }

            if (!(g instanceof Portiere portiere)) {
                sTrasfertaTitolare.aggiungiGiocatore(g);
            }
        }  
        
        tA.append("\n");
        tA.append(sCasaTitolare.toString() + "\n");
        tA.append("\n");
        tA.append(sTrasfertaTitolare.toString() + "\n");
        tA.append("\n");
        gM.stampaStadio(sCasaTitolare, sTrasfertaTitolare);
        Partita p = new Partita(sCasaTitolare, sTrasfertaTitolare, tA, lblC, lblT);
        p.gioca();
        tA.append(p.toString() + "\n");
        aG.suonoFischioFinale();
    }
    
    public static ImageIcon scaleIconKeepRatio(ImageIcon icon, int maxW, int maxH) {
        int w = icon.getIconWidth();
        int h = icon.getIconHeight();

        double ratio = Math.min((double) maxW / w, (double) maxH / h);

        int newW = (int) (w * ratio);
        int newH = (int) (h * ratio);

        Image img = icon.getImage().getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
    
    public ArrayList<Squadra> getSquadre() {
        return squadre;
    }

    public void setSquadre(ArrayList<Squadra> squadre) {
        this.squadre = squadre;
    }

    public HashSet<String> getCampionati() {
        return campionati;
    }

    public void setCampionati(HashSet<String> campionati) {
        this.campionati = campionati;
    }
}