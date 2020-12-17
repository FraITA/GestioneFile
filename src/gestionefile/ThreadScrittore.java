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
	
	public final GestoreFile gestore;
	
	public ThreadScrittore(GestoreFile gestore){
		this.gestore = gestore;
	}
	
	public ThreadScrittore(String name, GestoreFile gestore){
		super(name);
		this.gestore = gestore;
	}
	
	@Override
	public void run(){
		
		while(true){
			if(!GestioneFile.content.equals("")){
				break;
			}
		}
		
		synchronized(gestore){
			gestore.setFile("src/files/header." + GestioneFile.ext);
			
			gestore.scriviFile(GestioneFile.content);
			
			GestioneFile.content = "";
		}
	}
	
}
