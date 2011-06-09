 package com.ozonetel.kookoo;
 
 import java.io.StringWriter;
 import javax.xml.parsers.DocumentBuilder;
 import javax.xml.parsers.DocumentBuilderFactory;
 import javax.xml.transform.Transformer;
 import javax.xml.transform.TransformerFactory;
 import javax.xml.transform.dom.DOMSource;
 import javax.xml.transform.stream.StreamResult;
 import org.w3c.dom.Document;
import org.w3c.dom.Element;
 
 public class Dial
 {
   DocumentBuilderFactory dbfac;
   DocumentBuilder docBuilder;
   Document doc;
   Element dial;
 
   public Dial()
   {
     try
     {
       this.dbfac = DocumentBuilderFactory.newInstance();
      this.docBuilder = this.dbfac.newDocumentBuilder();
      this.doc = this.docBuilder.newDocument();
 
       this.dial = this.doc.createElement("dial");
       this.doc.appendChild(this.dial);
     }
     catch (Exception e) {
       e.printStackTrace();
     }
   }
 
   public Dial(Boolean record)
   {
     try
     {
       this.dbfac = DocumentBuilderFactory.newInstance();
       this.docBuilder = this.dbfac.newDocumentBuilder();
       this.doc = this.docBuilder.newDocument();
 
       this.dial = this.doc.createElement("dial");
       this.dial.setAttribute("record", record.toString());
       this.doc.appendChild(this.dial);
     }
     catch (Exception e) {
       e.printStackTrace();
     }
   }
   
   
   public Dial(String nuber,Boolean record)
   {
     try
     {
       this.dbfac = DocumentBuilderFactory.newInstance();
       this.docBuilder = this.dbfac.newDocumentBuilder();
       this.doc = this.docBuilder.newDocument();
 
       this.dial = this.doc.createElement("dial");
       this.dial.setTextContent(nuber);
       this.dial.setAttribute("record", record.toString());
       this.doc.appendChild(this.dial);
     }
     catch (Exception e) {
       e.printStackTrace();
     }
   }
 
   public void setRecord(Boolean record) {
     this.dial.setAttribute("record", record.toString());
   }
   
   public void setNumber(String number){
       this.dial.setTextContent(number);
   }
 

   public String getXML()
   {
     String xmlString = "";
     try {
       TransformerFactory transfac = TransformerFactory.newInstance();
       Transformer trans = transfac.newTransformer();
       trans.setOutputProperty("omit-xml-declaration", "yes");
       trans.setOutputProperty("indent", "yes");
 
       StringWriter sw = new StringWriter();
       StreamResult result = new StreamResult(sw);
       DOMSource source = new DOMSource(this.doc);
       trans.transform(source, result);
       xmlString = sw.toString();
     }
     catch (Exception e) {
       e.printStackTrace();
     }
     return xmlString;
   }
 
   public Element getRoot() {
     return this.dial;
   }
 }
