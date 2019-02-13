package com.ibm.p1.entity;

import java.util.Vector;


public class KeyWords {
	
	private String name;
	private Vector<String> words;
	private Vector<Float> weight;
	
	
	public KeyWords(){
		this.name = "";
		this.words = new Vector<String>();
		this.weight = new Vector<Float>();
	}
	
	public KeyWords(String name){
		this.name = name;
		this.words = new Vector<String>();
		this.weight = new Vector<Float>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getWordsNum(){
		return this.words.size();
	}
	
	public String getWord(int index){
		return index < this.words.size() ? this.words.elementAt(index) : "";
	}
	
	public float getWeight(int index){
		return (float) (index < this.weight.size() ? this.weight.elementAt(index).floatValue() : 0.0);
	}
	
	public void addWord(String word, double weight){
		this.words.add(word);
		this.weight.add(new Float(weight));
	}
	

}
