package com.ibm.p1.entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;


public class KeyWordsSet {
	private Vector<KeyWords> poi;
	
	public KeyWordsSet(){
		this.poi = new Vector<KeyWords>();
	}
	
	public KeyWordsSet(String fileName){
		this.poi = new Vector<KeyWords>();
		try {
			File f = new File(fileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			String line = "";
			String name = "";
			KeyWords kw;
			int cnt = 0;
			while ((line = br.readLine()) != null){
				if (line.startsWith("####")){
					name = line.substring(4);
					name = name.substring(0, name.indexOf("####"));
					kw = new KeyWords(name);
					this.poi.add(kw);
					cnt = 0;
				}
				else if (line.startsWith("====")){
					
				}
				else {
					if (cnt >= 5){
						continue;
					}
					String word = line.substring(0, line.indexOf(" "));
					String weight_str = line.substring(line.indexOf(" ")+1);
					word = word.substring(0, word.indexOf("/"));
					float weight = new Float(weight_str).floatValue();
					this.poi.lastElement().addWord(word, weight);
					cnt ++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getKeyWordsSetNum(){
		return this.poi.size();
	}
	
	public KeyWords getKeyWords(int index){
		if (index >= this.poi.size()){
			return null;
		}
		return this.poi.elementAt(index);
	}
	
	public String getWord(int index, int offset){
		if (index >= this.poi.size()){
			return "";
		}
		else {
			if (offset >= this.poi.elementAt(index).getWordsNum()){
				return "";
			}
			else {
				return this.poi.elementAt(index).getWord(offset);
			}
		}
	}
	
	public float getWeight(int index, int offset){
		if (index >= this.poi.size()){
			return  (float) 0.0;
		}
		else {
			if (offset >= this.poi.elementAt(index).getWordsNum()){
				return (float) 0.0;
			}
			else {
				return this.poi.elementAt(index).getWeight(offset);
			}
		}
	}
}
