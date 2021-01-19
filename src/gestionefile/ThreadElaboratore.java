/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionefile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author user
 */
public class ThreadElaboratore extends Thread{
	
	private final Dati dati;
	
	public ThreadElaboratore(Dati dati){
		this.dati = dati;
	}
	
	@Override
	public void run(){
		
		while(true){
			if(dati.getCanzone() != null){
				break;
			}
		}
		
		synchronized(dati){
			votaCanzone();
		}
	}
	
	private void votaCanzone(){
		ArrayList<HashMap<String,String>> canzoni = dati.getCanzone();
		
		InputStreamReader inputReader = new InputStreamReader(System.in);
		BufferedReader bufferedReader = new BufferedReader(inputReader);
		boolean flag = false;
		
		
		
		System.out.println("Vota canzone (Inserire numero canzone): ");
		
		
		
		int i = 0;
		
		for(HashMap<String,String> map : canzoni){
			String titolo, autore, anno;
			
			titolo = map.get("titolo");
			autore = map.get("autore");
			anno = map.get("anno");
			
			System.out.println(i + ". " + titolo + ", " + autore + ", " + anno);
			
			i++;
		}
		
		int num = 0;
		
		do{
			try {
				num = Integer.parseInt(bufferedReader.readLine());
				
				flag = num < 0 || num >= canzoni.size();
				
				if(flag){
					System.err.println("Inserimento non valido");
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}while(flag);
		
		formattaRisposta(num);
	}	
	
	private void formattaRisposta(int i){
		String ext = dati.getExt();
	
		Object risposta = "";
		
		switch(ext){
			case "xml":
				risposta = (Document) formattaRispostaXML(i);
				dati.setDom((Document) risposta);
				break;
			case "json":
				risposta = (String) formattaRispostaJSON(i);
				dati.setContent((String)risposta);
				break;
		}
	}
	
	private Document formattaRispostaXML(int i){
		Document dom = null;
		Element e;
		DocumentBuilderFactory dbf;
		DocumentBuilder db;
		HashMap<String,String> canzone = dati.getCanzone().get(i);
		try {
			
			// instance of a DocumentBuilderFactory
			dbf = DocumentBuilderFactory.newInstance();
			
			db = dbf.newDocumentBuilder();
			// create instance of DOM
			dom = db.newDocument();
			
			Element rootEle = dom.createElement("canzone_votata");
			
			e = dom.createElement("titolo");
			e.appendChild(dom.createTextNode(canzone.get("titolo")));
			rootEle.appendChild(e);
			
			e = dom.createElement("autore");
			e.appendChild(dom.createTextNode(canzone.get("autore")));
			rootEle.appendChild(e);
			
			e = dom.createElement("anno");
			e.appendChild(dom.createTextNode(canzone.get("anno")));
			rootEle.appendChild(e);
			
			dom.appendChild(rootEle);
			
		} catch (ParserConfigurationException ex) {
			ex.printStackTrace();
		}
		
		return dom;
	}
	
	private String formattaRispostaJSON(int i){
		String content = "";
		
		HashMap<String,String> canzone = dati.getCanzone().get(i);
		
		JSONObject obj = new JSONObject();
		JSONObject canzoneVotata = new JSONObject();
		
		canzoneVotata.put("titolo", canzone.get("titolo"));
		canzoneVotata.put("autore", canzone.get("autore"));
		canzoneVotata.put("anno", canzone.get("anno"));
		
		obj.put("canzone_votata", canzoneVotata);
		
		content = obj.toString(4);
		
		return content;
	}
}
