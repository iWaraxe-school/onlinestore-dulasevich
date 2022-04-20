package by.issoft.store.XMLpackage;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;


public class XMLReader {

    public Map<String, String> readXML() throws FileNotFoundException, XMLStreamException {

        Map<String, String> xmlDocSort = new LinkedHashMap<>();
        String productElement = null;
        String order = null;

        String fileName = "C:\\TC - Java For Auto\\store\\src\\main\\resources\\config";
        XMLStreamReader xmlReader = XMLInputFactory.newInstance().
                createXMLStreamReader(fileName, new FileInputStream(fileName));
        while (xmlReader.hasNext()){
            xmlReader.next();
            if(xmlReader.isStartElement()){
                productElement = xmlReader.getLocalName();
            } else if(xmlReader.hasText() && xmlReader.getText().trim().length()>0){
                order = xmlReader.getText();
            } else if(order != null){
                xmlDocSort.put(productElement, order);
            }
        }
        return xmlDocSort;
    }
}
