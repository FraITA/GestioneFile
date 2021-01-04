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
		synchronized(dati){
			votaCanzone();
		}
	}
	
	private void votaCanzone(){
		ArrayList<HashMap<String,String>> canzoni = dati.getDocument();
		
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
	
		String risposta = "";
		
		switch(ext){
			case "xml":
				risposta = formattaRispostaXML(i);
				break;
		}
		
		dati.setContent(risposta);
	}
	
	private String formattaRispostaXML(int i){
		String content = "";
		
		HashMap<String,String> canzone = dati.getDocument().get(i);
		
		content += "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n";
		
		content += "<canzone_votata>\n";
		
		content += "<titolo>" + canzone.get("titolo") + "</titolo>\n";
		
		content += "<autore>" + canzone.get("autore") + "</autore>\n";
		
		content += "<anno>" + canzone.get("anno") + "</anno>\n";
				
		content += "</canzone_votata>\n";
		
		return content;
	}
	
}
