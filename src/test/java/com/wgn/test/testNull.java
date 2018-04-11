package com.wgn.test;


public class testNull {
	static public void main(String args[]){
		String a=null;
		String b=null;
		if(a.equals(b)){
			System.out.println("null can compair");
		}else{
			System.out.println("null is not equal");
		}
		
		test1 t1=null;
		test1 t2=null;
		if(t1==t2){
			System.out.println("null can compair");
		}else{
			System.out.println("null is not equal");
		}
	}
	
	private class test1{
		private String a=null;
		public String getA(){
			return this.a;
		}
	}
}
