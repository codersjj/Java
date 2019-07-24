import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


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

	// 增加新的手机品牌信息（相当于修改DOM树）
	public void addNewPhone(){
		// 获得XML根元素
		Element root = document.getRootElement();
		// 创建<Brand>
		Element elBrand = root.addElement("Brand");
		elBrand.addAttribute("name", "华为");
		// 创建<Type name="HW123">
		Element elType = elBrand.addElement("Type");
		elType.addAttribute("name", "HW123");
		
		saveXML("newInfo.xml");
	}
	
	// 修改节点
	public void updatePhone() {
		// 先把根元素拿到
		Element root = document.getRootElement();
		Iterator eleBrands = root.elementIterator();
		int id = 0;
		while (eleBrands.hasNext()) {
			Element brand = (Element)eleBrands.next();
			id++;
			brand.addAttribute("id", id + "");
		}
		saveXML("newInfo.xml");
	}
	
	// 保存修改到XML文件
	public void saveXML(String path){
		// OutputFormat相当于转换器
		OutputFormat format = OutputFormat.createPrettyPrint();
//		format.setEncoding("GBK");
//		XMLWriter writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(path)));
		format.setEncoding("GBK");
		XMLWriter writer = null;
		try {
			writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(path), "GBK"), format);
			writer.write(document);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭输出流
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		Dom4jDemo domDemo = new Dom4jDemo();
		domDemo.loadDocument();
//		domDemo.addNewPhone();
		domDemo.updatePhone();
		domDemo.showPhoneInfo();
	}
}
