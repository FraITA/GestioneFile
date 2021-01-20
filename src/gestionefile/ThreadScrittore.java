/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionefile;


/**
 * Thread che permette la scrittura su file dei contenuti elaborati.
 * @author user
 */
public class ThreadScrittore extends Thread{
	
	/**
	 * Gestore del file su cui scrivere.
	 */
	private final GestoreFile gestore;
	
	/**
	 * Risorsa condivisa tra i thread.
	 */
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
		
		//Se il file è di tipo xml, il semaforo controllerà se il Document ha dei nodi figlio
		switch(dati.getExt()){
			case "xml":
				while(true){
					if(dati.getDom().hasChildNodes()){
						break;
					}
				}
				break;
				
			//Altrimenti controlla la variabile con il contenuto di tipo Stringa
			default:
				while(true){
					if(!dati.getContent().equals("")){
						break;
					}
				}
				break;
		}
		
		synchronized(dati){
			
			switch(dati.getExt()){
				case "xml":
					scriviFileXML();
					break;
				default:
					scriviFile();
					break;
			}
		}
		
	}
	
	/**
	 * Metodo che scrive il contenuto di tipo stringa sul file.
	 */
	private void scriviFile(){
		synchronized(gestore){
			gestore.scriviFile(dati.getContent());
		}
			
		dati.setContent("");
	}
	
	/**
	 * Metodo che scrive il documento formattato in XML sul file.
	 */
	private void scriviFileXML(){
		synchronized(gestore){
			gestore.scriviXML(dati.getDom());
		}
	}
	
	
}
