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
 
 public class CollectDtmf
 {
   DocumentBuilderFactory dbfac;
   DocumentBuilder docBuilder;
   Document doc;
   Element collectdtmf;
 
   public CollectDtmf()
   {
     try
     {
       this.dbfac = DocumentBuilderFactory.newInstance();
      this.docBuilder = this.dbfac.newDocumentBuilder();
      this.doc = this.docBuilder.newDocument();
 
       this.collectdtmf = this.doc.createElement("collectdtmf");
       this.doc.appendChild(this.collectdtmf);
     }
     catch (Exception e) {
       e.printStackTrace();
     }
   }
 
   public CollectDtmf(int maxDigits, String termChar, int timeOut, String playText, String playType)
   {
     try
     {
       this.dbfac = DocumentBuilderFactory.newInstance();
       this.docBuilder = this.dbfac.newDocumentBuilder();
       this.doc = this.docBuilder.newDocument();
 
       this.collectdtmf = this.doc.createElement("collectdtmf");
       this.collectdtmf.setAttribute("l", String.valueOf(maxDigits));
       this.collectdtmf.setAttribute("t", termChar);
       this.collectdtmf.setAttribute("o", String.valueOf(timeOut));
       Element pt = null;
       if (playType.equals("text"))
         pt = this.doc.createElement("playtext");
       else if (playType.equals("audio")) {
         pt = this.doc.createElement("playaudio");
       }
       pt.setTextContent(playText);
       this.collectdtmf.appendChild(pt);
       this.doc.appendChild(this.collectdtmf);
     }
     catch (Exception e) {
       e.printStackTrace();
     }
   }
 
   public CollectDtmf(int maxDigits, String termChar, int timeOut)
   {
     try
     {
       this.dbfac = DocumentBuilderFactory.newInstance();
       this.docBuilder = this.dbfac.newDocumentBuilder();
       this.doc = this.docBuilder.newDocument();
 
       this.collectdtmf = this.doc.createElement("collectdtmf");
       this.collectdtmf.setAttribute("l", String.valueOf(maxDigits));
       this.collectdtmf.setAttribute("t", String.valueOf(termChar));
       this.collectdtmf.setAttribute("o", String.valueOf(timeOut));
       this.doc.appendChild(this.collectdtmf);
     }
     catch (Exception e) {
       e.printStackTrace();
     }
   }
 
   public void setMaxDigits(int maxDigits)
   {
     this.collectdtmf.setAttribute("l", String.valueOf(maxDigits));
   }
 
   public void setTermChar(String termChar) {
     this.collectdtmf.setAttribute("t", termChar);
   }
 
   public void setTimeOut(int timeOut) {
     this.collectdtmf.setAttribute("o", String.valueOf(timeOut));
   }
 
   public void addPlayText(String playText) {
     Element pt = this.doc.createElement("playtext");
     pt.setTextContent(playText);
     this.collectdtmf.appendChild(pt);
   }
 
   public void addPlayAudio(String playAudio) {
     Element pt = this.doc.createElement("playaudio");
     pt.setTextContent(playAudio);
     this.collectdtmf.appendChild(pt);
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
     return this.collectdtmf;
   }
 }
