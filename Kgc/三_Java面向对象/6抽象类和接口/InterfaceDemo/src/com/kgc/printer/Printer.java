package com.kgc.printer;

//打印机
public class Printer {
	InkBox inkBox; //墨盒
	Paper paper; //纸张
	public InkBox getInkBox() {
		return inkBox;
	}
	public void setInkBox(InkBox inkBox) {
		this.inkBox = inkBox;
	}
	public Paper getPaper() {
		return paper;
	}
	public void setPaper(Paper paper) {
		this.paper = paper;
	}
	
	public void print(){
		System.out.println("使用" + inkBox.getColor() + "的墨盒在" + paper.getSize() + "纸张上打印");
	}
	
	public static void main(String[] args) {
		InkBox inkBox = new GrayInkBox(); // 准备好墨盒   接口类型指向具体实现类的对象，类似于父类引用指向子类对象。
		Paper paper = new A4Paper(); // 准备好纸
		Printer printer = new Printer(); // 准备好打印机
		printer.setInkBox(inkBox); // 把墨盒放入打印机
		printer.setPaper(paper); // 把纸放入打印机
		printer.print(); // 开始打印
		
		inkBox = new ColorInkBox();
		paper = new B5Paper();
		printer.setInkBox(inkBox);
		printer.setPaper(paper);
		printer.print();
	}
}
