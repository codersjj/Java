import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

// 使用DOM解析XML文件
public class ParseXMLDemo {
	// “收藏信息.xml”对应的Document对象
	private Document document;
	
	// 获得DOM树，获得Document对象
	public void getDemo() {
		// 获得解析器工厂
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			// 根据解析器工厂获得解析器
			DocumentBuilder builder = factory.newDocumentBuilder();
			// 解析器来解析XML文件获得Document对象
			document = builder.parse("收藏信息.xml"); // 这里写成了相对路径，前提是此XML文件要直接放在当前项目下。
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
