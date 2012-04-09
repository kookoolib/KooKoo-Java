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
import org.w3c.dom.Node;

public class Response {

    DocumentBuilderFactory dbfac;
    DocumentBuilder docBuilder;
    Document doc;
    Element response;

    public Response() {
        try {
            this.dbfac = DocumentBuilderFactory.newInstance();
            this.docBuilder = this.dbfac.newDocumentBuilder();
            this.doc = this.docBuilder.newDocument();

            this.response = this.doc.createElement("response");
            this.doc.appendChild(this.response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSid(String sid) {
        this.response.setAttribute("sid", sid);
    }

    public void setFiller(String filler) {
        this.response.setAttribute("filler", filler);
    }

    public void addPlayText(String playText) {
        Element pt = this.doc.createElement("playtext");
        pt.setTextContent(playText);
        pt.setAttribute("lang", "EN");
        pt.setAttribute("speed", "" + 2);
        this.response.appendChild(pt);
    }

    public void sendSms(String text, String number) {
        Element ss = this.doc.createElement("sendsms");
        ss.setTextContent(text);
        ss.setAttribute("to", number);
        this.response.appendChild(ss);
    }
    
    public void sendSms(String text, String number,String unicode) {
        Element ss = this.doc.createElement("sendsms");
        ss.setTextContent(text);
        ss.setAttribute("to", number);
        ss.setAttribute("unicode", unicode);
        this.response.appendChild(ss);
    }

    public void addPlayText(String playText, String lang) {
        Element pt = this.doc.createElement("playtext");
        pt.setTextContent(playText);
        pt.setAttribute("speed", "" + 2);
        pt.setAttribute("lang", lang);
        this.response.appendChild(pt);
    }

    public void addPlayText(String playText, int speed) {
        Element pt = this.doc.createElement("playtext");
        pt.setTextContent(playText);
        pt.setAttribute("speed", "" + speed);
        pt.setAttribute("lang", "EN");
        this.response.appendChild(pt);
    }

    public void addPlayText(String playText, int speed, String lang) {
        Element pt = this.doc.createElement("playtext");
        pt.setTextContent(playText);
        pt.setAttribute("speed", "" + speed);
        pt.setAttribute("lang", lang);
        this.response.appendChild(pt);
    }

    public void addConference(String confno) {
        Element cf = this.doc.createElement("conference");
        cf.setTextContent(confno);
        this.response.appendChild(cf);
    }

    public void addRecord(Record r) {
        Node c = this.doc.importNode(r.getRoot(), true);
        this.response.appendChild(c);
    }

    public void addHangup() {
        Element hu = this.doc.createElement("hangup");
        this.response.appendChild(hu);
    }

    public void addContext(String context) {
        Element ctx = this.doc.createElement("context");
        ctx.setTextContent(context);
        this.response.appendChild(ctx);
    }

    public void addPlayAudio(String playAudio) {
        Element pt = this.doc.createElement("playaudio");
        pt.setTextContent(playAudio);
        this.response.appendChild(pt);
    }

    public void addGotoNEXTURL(String url) {
        Element pt = this.doc.createElement("gotourl");
        pt.setTextContent(url);
        this.response.appendChild(pt);
    }

    public void addCollectDtmf(CollectDtmf cd) {
        Node c = this.doc.importNode(cd.getRoot(), true);
        this.response.appendChild(c);
    }

    public void addDial(Dial dial) {
        Node c = this.doc.importNode(dial.getRoot(), true);
        this.response.appendChild(c);
    }

    public String getXML() {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xmlString;
    }

    public Element getRoot() {
        return this.response;
    }
}
