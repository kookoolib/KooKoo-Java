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

public class Record {

    DocumentBuilderFactory dbfac;
    DocumentBuilder docBuilder;
    Document doc;
    Element record;

    public Record() {
        try {
            this.dbfac = DocumentBuilderFactory.newInstance();
            this.docBuilder = this.dbfac.newDocumentBuilder();
            this.doc = this.docBuilder.newDocument();

            this.record = this.doc.createElement("record");
            this.doc.appendChild(this.record);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Record(String fileName, String format, long silence, long maxDuration, char option) {
        try {
            this.dbfac = DocumentBuilderFactory.newInstance();
            this.docBuilder = this.dbfac.newDocumentBuilder();
            this.doc = this.docBuilder.newDocument();

            this.record = this.doc.createElement("record");
            this.record.setTextContent(fileName);
            this.record.setAttribute("format", format);
            this.record.setAttribute("silence", String.valueOf(silence));
            this.record.setAttribute("maxduration", String.valueOf(maxDuration));
            this.record.setAttribute("option", String.valueOf(option));

            this.doc.appendChild(this.record);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setFormat(String format) {
        this.record.setAttribute("format", format);
    }

    public void setSilence(long silence) {
        this.record.setAttribute("silence", String.valueOf(silence));
    }

    public void setMaxDuration(long maxDuration) {
        this.record.setAttribute("maxduration", String.valueOf(maxDuration));
    }

    public void setFileName(String fileName) {
        this.record.setTextContent(fileName);
    }

    public void setOptions(char option) {
        this.record.setAttribute("option", String.valueOf(option));
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
        return this.record;
    }
}
