/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionefile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author user
 */
public class GestoreFile{
	
	private File file;
	
	public synchronized String leggiFile(){
		String output = "";
		int i;
		
		if(this.file == null){
			System.err.println("File non settato!");
			return "";
		}
		
		try {
			FileReader fr = new FileReader(this.file);
			try {
				while((i = fr.read()) != -1){
					output += (char)i;
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		
		return output;
	}
	
	
	public synchronized void scriviFile(String content){
		FileWriter fw = null;
		
		if(this.file == null){
			System.err.println("File non settato!");
			return;
		}
		
		try {
			fw = new FileWriter(this.file);
			
			fw.write(content);
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			if(fw != null) try {
				fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public synchronized void setFile(String fileName){
		this.file = new File(fileName);
	}
	
	public synchronized void setFile(File file){
		this.file = file;
	}
	
}
