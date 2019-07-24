import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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
	
	// 为XML文件添加元素（只是添加到内存里，而没有真正添加到文件中）
	public void addEle(){
		// 创建<Brand name="三星">
		Element brand = document.createElement("Brand");
		brand.setAttribute("name", "三星");
		// 创建<Type name="NOTE2">
		Element type = document.createElement("Type");
		type.setAttribute("name", "NOTE2");
		// 将Type作为Brand的子元素
		brand.appendChild(type);
		// 将Brand放到PhoneInfo中去
		document.getElementsByTagName("PhoneInfo").item(0).appendChild(brand);
		saveXML("收藏信息.xml");
	}
	
	// 保存XML文件（需要借助转换器：源（最新的DOM树） --> 目的地（“收藏信息.xml”文件），实际上是借助的输出流实现）
	public void saveXML(String path){
		// 转换器工厂
		TransformerFactory factory = TransformerFactory.newInstance();
		// 设置缩进
		factory.setAttribute("indent-number", 4);
		try {
			// 转换器
			Transformer transformer = factory.newTransformer();
			// 指定转换器格式
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "YES");
			// 源（最新的DOM树） --> 目的地（“收藏信息.xml”文件）
			DOMSource source = new DOMSource(document); // 把最新的DOM树（这里的document）作为参数传进去
			
			OutputStream out = new FileOutputStream(path); // 字节流
			StreamResult result = new StreamResult(new OutputStreamWriter(out, "UTF-8")); // OutputStreamWriter(out)把字节流包成字符流
			
			transformer.transform(source, result);
			 
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		ParseXMLDemo pd = new ParseXMLDemo();
		pd.getDom();
		pd.addEle();
		pd.showInfo();
	}
}
