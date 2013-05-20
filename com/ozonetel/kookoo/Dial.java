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

public class Dial {

    public enum MusicOnHold {

        /**
         * for music
         */
        DEFAULT,
        /**
         * KooKoo ring
         */
        RING,
        /**
         * Teleco Ring
         */
        NORMAL
    }
    DocumentBuilderFactory dbfac;
    DocumentBuilder docBuilder;
    Document doc;
    Element dial;

    public Dial() {
        try {
            this.dbfac = DocumentBuilderFactory.newInstance();
            this.docBuilder = this.dbfac.newDocumentBuilder();
            this.doc = this.docBuilder.newDocument();

            this.dial = this.doc.createElement("dial");
            this.dial.setAttribute("moh", "" + MusicOnHold.NORMAL.name().toLowerCase());
            this.doc.appendChild(this.dial);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Dial(Boolean record) {
        try {
            this.dbfac = DocumentBuilderFactory.newInstance();
            this.docBuilder = this.dbfac.newDocumentBuilder();
            this.doc = this.docBuilder.newDocument();

            this.dial = this.doc.createElement("dial");
            this.dial.setAttribute("record", record.toString());
            this.dial.setAttribute("moh", MusicOnHold.NORMAL.name().toLowerCase());
            this.doc.appendChild(this.dial);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Dial(String number, Boolean record) {
        try {
            this.dbfac = DocumentBuilderFactory.newInstance();
            this.docBuilder = this.dbfac.newDocumentBuilder();
            this.doc = this.docBuilder.newDocument();

            this.dial = this.doc.createElement("dial");
            this.dial.setTextContent(number);
            this.dial.setAttribute("record", record.toString());
            this.dial.setAttribute("moh", MusicOnHold.NORMAL.name().toLowerCase());
            this.doc.appendChild(this.dial);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setRecord(Boolean record) {
        this.dial.setAttribute("record", record.toString());
    }

    public void setNumber(String number) {
        this.dial.setTextContent(number);
    }

 

    public void setTimeout(int timeout) {
        if (timeout < 0) {
            timeout = 30;
        }
        this.dial.setAttribute("timeout", String.valueOf(timeout));
    }

    public void setLimitTime(int limittime) {
        this.dial.setAttribute("limittime", String.valueOf(limittime));
    }

    /**
     * Sets the music on hold. Possible values are:<ul>
     * <li><code>Dial.MusicOnHold.NORMAL</code> (Teleco ring)
     * <li><code>Dial.MusicOnHold.DEFAULT</code> (music)
     * <li><code>Dial.MusicOnHold.RING</code> (KooKoo ring) </ul>
     *
     * @param musicOnHold music on hold
     */
    public void setMusicOnHold(MusicOnHold musicOnHold) {
        this.dial.setAttribute("moh", musicOnHold.name().toLowerCase());
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
        return this.dial;
    }
}
