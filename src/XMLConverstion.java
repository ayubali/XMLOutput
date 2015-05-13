import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XMLConverstion {

	public static void main(String[] args) {
		DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder icBuilder;
		try {
			icBuilder = icFactory.newDocumentBuilder();
			Document doc = icBuilder.newDocument();
			Element mainRootElement = doc.createElementNS(
					"http://example.com/programming/test/book", "book");
			doc.appendChild(mainRootElement);

			// append child elements to root element
			mainRootElement.appendChild(addBook(doc, new Book("Java Effective",
					new ArrayList<Author>(), "2012", "isb14")));

			// output DOM XML to console
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			StreamResult console = new StreamResult(System.out);
			transformer.transform(source, console);

			System.out.println("\nXML DOM Created Successfully..");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Node addBook(Document doc, Book book1) {
		Element book = doc.createElement("book");
		//book.setAttribute("id", "12");
		book.appendChild(CreateBookElements(doc, "isbn", book1.getIsbn()));
		book.appendChild(CreateBookElements(doc, "name", book1.getName()));
		//
		book.appendChild(CreateBookElements(doc, "published-date",
				"th"));

		return book;
	}

	// utility method to create text node
	private static Node CreateBookElements(Document doc, String name,
			String value) {
		System.out.println(name + " - " + value);
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}
}
