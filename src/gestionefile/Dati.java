/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionefile;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe che fa da risorsa condivisa tra i thread.
 * @author user
 */
public class Dati {
	/**
	 * Contenuto di un file
	 */
	private String content;
	
	private ArrayList<HashMap<String,String>> document;
	
	private String ext;
	
	public Dati(){
		this.content = "";
		this.document = null;
	}
	
	public synchronized String getExt() {
		return ext;
	}
	
	public synchronized String getContent(){
		return this.content;
	}
	
	public synchronized ArrayList<HashMap<String,String>> getDocument(){
		return this.document;
	}

	public synchronized void setExt(String ext) {
		this.ext = ext;
	}
	
	public synchronized void setContent(String content){
		this.content = content;
	}
	
	public synchronized void setDocument(ArrayList<HashMap<String,String>> document){
		this.document = document;
	}
}
