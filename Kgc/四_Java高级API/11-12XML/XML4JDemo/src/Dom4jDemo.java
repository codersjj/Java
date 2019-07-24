import java.io.File;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class Dom4jDemo {
	private Document document;
	
	//获得document对象
	public void loadDocument() {
		SAXReader saxReader = new SAXReader();
		try {
			document = saxReader.read(new File("收藏信息.xml"));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	// 显示手机的品牌及型号
	public void showPhoneInfo(){
		// 获取XML根节点
		Element root = document.getRootElement();
		// 获取所有<Brand>
		Iterator eleBrands = root.elementIterator();
		while (eleBrands.hasNext()) {
			Element brand = (Element)eleBrands.next();
			System.out.println(brand.attributeValue("name"));
			Iterator eleTypes = brand.elementIterator();
			while (eleTypes.hasNext()) {
				Element type = (Element)eleTypes.next();
				System.out.println("\t" + type.attributeValue("name"));
			}
		}
	}
	
	public static void main(String[] args) {
		Dom4jDemo domDemo = new Dom4jDemo();
		domDemo.loadDocument();
		domDemo.showPhoneInfo();
	}
}
