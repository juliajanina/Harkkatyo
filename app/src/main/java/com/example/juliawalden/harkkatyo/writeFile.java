package com.example.juliawalden.harkkatyo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class writeFile {

    public writeFile() {

    }

    public void writeXmlFile(ArrayList<Rating> list, OutputStreamWriter osw) {

        try {

            DocumentBuilderFactory docf = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = docf.newDocumentBuilder();
            Document doc = build.newDocument();

            Element root = doc.createElement("Foods");
            doc.appendChild(root);

            Element Food = doc.createElement("Food");
            root.appendChild(Food);


            for (Rating rating : list) {

                Element name = doc.createElement("Food");
                name.appendChild(doc.createTextNode(String.valueOf(rating.getFood())));
                Food.appendChild(name);

                Element rate = doc.createElement("Rating");
                rate.appendChild(doc.createTextNode(String.valueOf(rating.getRating())));
                Food.appendChild(rate);

            }

            // Save the document to the disk file
            TransformerFactory tranFactory = TransformerFactory.newInstance();
            Transformer aTransformer = tranFactory.newTransformer();

            // format the XML nicely
            //aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

            aTransformer.setOutputProperty(
                    "{http://xml.apache.org/xslt}indent-amount", "4");
            aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            // location and name of XML file you can change as per need
            StreamResult result = new StreamResult(osw);
            aTransformer.transform(source, result);


        } catch (TransformerException ex) {
            System.out.println("Error outputting document");

        } catch (ParserConfigurationException ex) {
            System.out.println("Error building document");
        } finally {
            System.out.println("Done");
        }
    }

}

