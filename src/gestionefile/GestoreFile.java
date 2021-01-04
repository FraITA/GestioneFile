/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionefile;

import org.json.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author user
 */
public class GestoreFile {

	/**
	 * File da gestire
	 */
	private final File file;

	public GestoreFile(String fileName) {
		this.file = new File(fileName);
	}

	public GestoreFile(File file) {
		this.file = file;
	}

	/**
	 * Metodo che permette la lettura del file assegnato
	 *
	 * @return contenuto del file
	 */
	public synchronized String leggiFile() {
		String output = "";
		String str;

		if (this.file == null) {
			System.err.println("File non settato!");
			return "";
		}

		try {
			FileReader fr = new FileReader(this.file);
			BufferedReader br = new BufferedReader(fr);
			try {
				while ((str = br.readLine()) != null) {
					output += str + "\n";
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}

		return output;
	}

	/**
	 * Metodo che scrive sul file assegnato
	 *
	 * @param content contenuto da scrivere
	 */
	public synchronized void scriviFile(String content) {
		FileWriter fw = null;
		BufferedWriter bw = null;

		if (this.file == null) {
			System.err.println("File non settato!");
			return;
		}

		try {
			fw = new FileWriter(this.file);
			bw = new BufferedWriter(fw);

			bw.write(content);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					bw.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	public synchronized ArrayList<HashMap<String,String>> parse() {
		String name = this.file.getName();
		int dot = name.lastIndexOf('.');
		String ext = (dot == -1) ? "" : name.substring(dot+1);
		
		switch (ext) {
			case "xml":
				return parseFromXML();
			case "json":
				return parseFromJSON();
		}

		return null;
	}

	private synchronized ArrayList<HashMap<String,String>> parseFromXML() {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

		DocumentBuilder dBuilder;

		NodeList list;

		ArrayList canzoni = new ArrayList();
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(this.file);
			list = doc.getElementsByTagName("canzone");
			for (int i = 0; i < list.getLength(); i++) {
				Node node = list.item(i);
				Element eElement = (Element) node;
				HashMap map = new HashMap();
				map.put("titolo", eElement.getElementsByTagName("titolo").item(0).getTextContent());
				map.put("autore", eElement.getElementsByTagName("autore").item(0).getTextContent());
				map.put("anno", eElement.getElementsByTagName("anno").item(0).getTextContent());
				canzoni.add(map);
			}
			return canzoni;
		} catch (SAXException | ParserConfigurationException | IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	
	private synchronized ArrayList<HashMap<String,String>> parseFromJSON(){
		ArrayList<HashMap<String,String>> canzoni;
		String content = leggiFile().replace('\n', ' ');
		JSONObject ogg =  new JSONObject(content);
		HashMap map = (HashMap) ogg.toMap();
		canzoni = (ArrayList) map.get("playlist");
		
		return canzoni;
	}
}
