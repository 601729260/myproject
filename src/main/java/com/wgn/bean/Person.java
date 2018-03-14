package com.wgn.bean;

public class Person {
	private String name;
	private int id=0;
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	} 
	public String  getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}

	public String toString(){
		return "Person[name="+this.name+",id="+this.id+"]";
	}
}
