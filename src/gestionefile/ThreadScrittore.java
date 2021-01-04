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
public class ThreadScrittore extends Thread{
	
	private final GestoreFile gestore;
	
	private final Dati dati;
	
	public ThreadScrittore(GestoreFile gestore, Dati dati){
		this.gestore = gestore;
		this.dati = dati;
	}
	
	public ThreadScrittore(String name, GestoreFile gestore, Dati dati){
		super(name);
		this.gestore = gestore;
		this.dati = dati;
	}
	
	/**
	 * Metodo che scrive il contenuto della risorsa condivisa su file.
	 */
	@Override
	public void run(){
		
		while(true){
				if(!dati.getContent().equals("")){
					break;
				}
			}
		
		synchronized(dati){
			
			synchronized(gestore){
				gestore.scriviFile(dati.getContent());
			}
			
			dati.setContent("");
		}
		
	}
	
}
