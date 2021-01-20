/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionefile;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe che estende Thread che legge il file assegnato.
 * @author user
 */
public class ThreadLettore extends Thread{
	
	/**
	 * Gestore del file da leggere.
	 */
	private final GestoreFile gestore;
	
	/**
	 * Risorsa condivisa tra i thread.
	 */
	private final Dati dati;
	
	public ThreadLettore(GestoreFile gestore, Dati dati){
		this.gestore = gestore;
		this.dati = dati;
	}
	
	public ThreadLettore(String name, GestoreFile gestore, Dati dati){
		super(name);
		this.gestore = gestore;
		this.dati = dati;
	}
	
	/**
	 * Metodo che legge il file e inserisce il suo contenuto
	 * nella risorsa condivisa.
	 */
	@Override
	public void run(){
		synchronized(dati){
			ArrayList<HashMap<String,String>> canzone;
			
			synchronized(gestore){
				canzone = gestore.parse();
			}
		
			dati.setCanzone(canzone);
		}
	}
	
}
