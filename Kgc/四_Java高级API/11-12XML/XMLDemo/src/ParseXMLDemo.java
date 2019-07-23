import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

// 使用DOM解析XML文件
public class ParseXMLDemo {
	// “收藏信息.xml”对应的Document对象
	private Document document;
	
	// 获得DOM树，获得Document对象
	public void getDom() {
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
	
	// 获取手机品牌及型号相关信息
	public void showInfo() {
		// 以Document做起点，先拿到所有的Brand节点
		NodeList brands = document.getElementsByTagName("Brand");
		// 遍历brands，从中拿出每一个Brand
		for (int i = 0; i < brands.getLength(); i++) {
			Node node = brands.item(i);
			Element eleBrand = (Element)node;
			// 通过属性名获取属性值
			String brandName = eleBrand.getAttribute("name");
			System.out.println(brandName);
			
			// 继续查找，找每个Brand的子节点出来。
			NodeList types = eleBrand.getChildNodes();
			for (int j = 0; j < types.getLength(); j++) {
				Node typeNode = types.item(j);
				// 判断该子节点是否为元素节点
				if (typeNode.getNodeType() == Node.ELEMENT_NODE) {
					Element typeEle = (Element)typeNode;
					System.out.println("\t" + typeEle.getAttribute("name"));
				}
				
			}
		}
	}
	
	public static void main(String[] args){
		ParseXMLDemo pd = new ParseXMLDemo();
		pd.getDom();
		pd.showInfo();
	}
}
