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
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
			GestoreFile gestore = new GestoreFile();
			
			InputStreamReader inputReader = new InputStreamReader(System.in);

			BufferedReader bufferedReader = new BufferedReader(inputReader);
			
			ThreadLettore tr = new ThreadLettore(gestore);
			ThreadScrittore tw = new ThreadScrittore(gestore);
			
			String ext = "";
			boolean flag = false;
			String[] exts = {"xml", "csv", "json"};
			String str = "";
					
			do{
				try {
					System.out.println("Inserire nome file (con estensione XML, JSON o CSV)");
					str = bufferedReader.readLine();
					
					for(String s : exts){
						ext = str.split(".")[1];
						if(ext.equals(s)){
							flag = true;
						}
					}
					
					if(!flag){
						System.err.println("Inserimento non valido");
					}
				} catch (IOException ex) {
					Logger.getLogger(GestioneFile.class.getName()).log(Level.SEVERE, null, ex);
				}
			}while(!flag);
			
			tr.start();
			tw.start();
	}
	
}
