/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionefile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class GestioneFile {
	
	public static String content = "";
	
	public static String ext = "";
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
			GestoreFile gestore = new GestoreFile();
			
			InputStreamReader inputReader = new InputStreamReader(System.in);

			BufferedReader bufferedReader = new BufferedReader(inputReader);
			
			ThreadLettore tr = new ThreadLettore(gestore);
			ThreadScrittore tw = new ThreadScrittore(gestore);
			
			String ext;
			boolean flag = false;
			String[] exts = {"xml", "csv", "json"};
					
			do{
				try {
					System.out.println("Inserire nome file (con estensione XML, JSON o CSV)");
					ext = bufferedReader.readLine();
					
					for(String s : exts){
						if(ext.equals(s)){
							GestioneFile.ext = ext;
							flag = true;
						}
					}
					
					if(!flag){
						System.err.println("Inserimento non valido");
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}while(!flag);
			
			tr.start();
			tw.start();
	}
	
}
