package no.hvl.dat100.oppgave5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import no.hvl.dat100.common.TODO;
import no.hvl.dat100.oppgave1.*;
import no.hvl.dat100.oppgave2.*;
import no.hvl.dat100.oppgave3.*;

import javax.swing.JOptionPane;

public class LesBlogg {

	private static String TEKST = "TEKST";
	private static String BILDE = "BILDE";

	public static Blogg les(String mappe, String filnavn) {
		    
            Blogg blogg = null;
    
            try {
                File file = new File(mappe + filnavn);
                Scanner scanner = new Scanner(file);
                int antall = Integer.parseInt(scanner.nextLine());
                blogg = new Blogg(antall);
    
                for (int i = 0; i < antall; i++) {
                    String type = scanner.nextLine();
                    int id = Integer.parseInt(scanner.nextLine());
                    String bruker = scanner.nextLine();
                    String dato = scanner.nextLine();
                    int likes = Integer.parseInt(scanner.nextLine());
                    String tekst = scanner.nextLine();
    
                    Innlegg innlegg = null;
    
                    if (type.equals(TEKST)) {
                        innlegg = new Tekst(id, bruker, dato, likes, tekst);
                    } else if (type.equals(BILDE)) {
                        String url = scanner.nextLine();
                        innlegg = new Bilde(id, bruker, dato, likes, tekst, url);
                    }
    
                    blogg.leggTil(innlegg);
                }
    
                scanner.close();
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Filen ble ikke funnet");
            }
    
            return blogg;
        }

	}

