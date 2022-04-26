package by.issoft.store.XMLpackage;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class XMLReader {

    public static Map<String, String> readXML() throws ParserConfigurationException, IOException, SAXException {

        String PATH = "store/src/main/resources/config";

        Map<String, String> xmlDocSort = new LinkedHashMap<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(PATH);

        Node node = document.getElementsByTagName("sort").item(0);
        NodeList sortOptions = node.getChildNodes();

        for (int i = 0; i < sortOptions.getLength(); i++) {
            if (sortOptions.item(i).getNodeType() == Node.ELEMENT_NODE) {
                xmlDocSort.put(sortOptions.item(i).getNodeName(), sortOptions.item(i).getTextContent());
            }
        }
        return xmlDocSort;
    }
}

