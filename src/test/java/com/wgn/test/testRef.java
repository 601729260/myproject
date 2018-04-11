package com.wgn.test;

import org.junit.Test;

public class testRef {
	
	@Test
	public void changeRef(){
		P p=new P();
		System.out.println(p);
		changeP(p);
		System.out.println(p);
		
	}
		
	public void changeP(P p){
		p=new P();
		p.setName("p1");
		System.out.println(p);
		
	}
	public class P{
		public String name="p0";
		public void setName(String name){
			this.name=name;
		}
		public String toString(){
			return this.name;
		}
		
	}
}
