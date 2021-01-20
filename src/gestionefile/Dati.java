/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionefile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;

/**
 * Classe che fa da risorsa condivisa tra i thread.
 * @author user
 */
public class Dati {
	/**
	 * Contenuto di tipo stringa semplice.
	 */
	private String content;
	
	/**
	 * Contenuto del file formattato.
	 */
	private ArrayList<HashMap<String,String>> canzone;
	
	/**
	 * Estensione dei file da gestire.
	 */
	private String ext;
	
	/**
	 * Documento formattato in XML.
	 */
	private Document dom;
	
	/**
	 * Metodo costruttore che prepara tutti gli attributi per gli accessi sincronizzati.
	 */
	public Dati(){
		this.content = "";
		this.canzone = null;
		this.ext = "";
		
		//Creo un documento vuoto per permettere il controllo con il semaforo
		//per gestire gli accessi sincronizzati ai dati.
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException ex) {
			Logger.getLogger(Dati.class.getName()).log(Level.SEVERE, null, ex);
		}
		dom = db.newDocument();
	}
	
	public synchronized String getExt() {
		return ext;
	}
	
	public synchronized String getContent(){
		return this.content;
	}
	
	public synchronized ArrayList<HashMap<String,String>> getCanzone(){
		return this.canzone;
	}
	
	public synchronized Document getDom() {
		return dom;
	}

	public synchronized void setExt(String ext) {
		this.ext = ext;
	}
	
	public synchronized void setContent(String content){
		this.content = content;
	}
	
	public synchronized void setCanzone(ArrayList<HashMap<String,String>> canzone){
		this.canzone = canzone;
	}
	
	public synchronized void setDom(Document dom) {
		this.dom = dom;
	}
}
