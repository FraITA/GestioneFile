/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionefile;

import java.io.File;
import java.util.HashMap;

/**
 *
 * @author user
 */
public class GestoreFileBuilder {
	
	public static HashMap<String, GestoreFile> fileMap = new HashMap();
	
	public static GestoreFile createGestoreFile(String fileName){
		GestoreFile gestore;
		
		for(String s : fileMap.keySet()){
			if(s.equals(fileName)){
				return fileMap.get(s);
			}
		}
		
		gestore = new GestoreFile(fileName);
		
		fileMap.put(fileName, gestore);
		
		return gestore;
		
	}
	
	public static GestoreFile createGestoreFile(File file){
		GestoreFile gestore;
		String fileName = file.getName();
		
		System.out.println(fileName);
		
		for(String s : fileMap.keySet()){
			if(s.equals(fileName)){
				return fileMap.get(s);
			}
		}
	
		gestore = new GestoreFile(file);
		
		fileMap.put(file.getName(), gestore);
		
		return gestore;
		
	}
	
}
