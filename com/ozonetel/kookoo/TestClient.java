 package com.ozonetel.kookoo;
 
 public class TestClient
 {
   public static void main(String[] args)
   {
     Response resp = new Response();
 
     CollectDtmf cd = new CollectDtmf();
     cd.addPlayText("Welcome to adder. Please enter the first number. Terminate with #");
     resp.addCollectDtmf(cd);
 
     System.out.println(resp.getXML());
   }
 }
