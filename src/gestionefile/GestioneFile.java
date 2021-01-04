/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionefile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author user
 */
public class GestioneFile {
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		
		String ext = "";
		boolean flag = false;
		String[] exts = {"xml", "csv", "json"};

		InputStreamReader inputReader = new InputStreamReader(System.in);
		BufferedReader bufferedReader = new BufferedReader(inputReader);

		Dati dati = new Dati();

		do{
			try {
				System.out.println("Inserire nome file (con estensione XML, JSON o CSV)");
				ext = bufferedReader.readLine();

				for(String s : exts){
					if(ext.equals(s)){
						flag = true;
						dati.setExt(ext);
					}
				}

				if(!flag){
					System.err.println("Inserimento non valido");
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}while(!flag);
		
		GestoreFile gestoreReader = GestoreFileBuilder.createGestoreFile("src/files/playlist." + ext);
		GestoreFile gestoreWriter = GestoreFileBuilder.createGestoreFile("src/files/votazione." + ext);


		ThreadLettore tr = new ThreadLettore(gestoreReader, dati);
		ThreadElaboratore te = new ThreadElaboratore(dati);
		ThreadScrittore tw = new ThreadScrittore(gestoreWriter, dati);

		tr.start();
		te.start();
		tw.start();
	}
	
}
