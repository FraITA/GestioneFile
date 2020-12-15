/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionefile;

/**
 *
 * @author user
 */
public class GestioneFile {
	
	public static String content = "";
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
			GestoreFile gestore = new GestoreFile();
			ThreadLettore tr = new ThreadLettore(gestore);
			ThreadScrittore tw = new ThreadScrittore(gestore);
			
			tr.start();
			tw.start();
	}
	
}
