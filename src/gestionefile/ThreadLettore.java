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
public class ThreadLettore extends Thread{
	
	public final GestoreFile gestore;
	
	public ThreadLettore(GestoreFile gestore){
		this.gestore = gestore;
	}
	
	public ThreadLettore(String name, GestoreFile gestore){
		super(name);
		this.gestore = gestore;
	}
	
	@Override
	public void run(){
		synchronized(gestore){
			String content;
		
			gestore.setFile("src/files/playlist." + GestioneFile.ext);
		
			content = gestore.leggiFile();
		
			content = content.split("\\r?\\n")[0];
		
			GestioneFile.content = content;
		}
	}
	
}
