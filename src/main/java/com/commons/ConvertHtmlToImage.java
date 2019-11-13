package com.commons;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JEditorPane;
import javax.swing.SwingUtilities;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

public abstract class  ConvertHtmlToImage {
	static class Kit extends HTMLEditorKit  {
		public Document createDefaultDocument() {
			HTMLDocument doc = (HTMLDocument) super.createDefaultDocument();
			doc.setTokenThreshold(Integer.MAX_VALUE);
			doc.setAsynchronousLoadPriority(-1);
			return doc;
		}
	}
	public static BufferedImage create(String pageurl, int width, int height)
	{
		BufferedImage image = null;
		JEditorPane pane = new JEditorPane();
		Kit kit = new Kit();
		pane.setEditorKit(kit);
		pane.setEditable(false);
		pane.setMargin(new Insets(0,0,0,0));
		try {
			pane.setPage(pageurl);
			image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = image.createGraphics();
			Container c = new Container();
			SwingUtilities.paintComponent(g, pane, c, 0, 0, width, height);
			g.dispose();
		} catch (Exception e) {
			System.out.println(e);
		}

		return image;
	}

	private static BufferedImage resizeImage(BufferedImage originalImage, int type, int IMG_WIDTH , int IMG_HEIGHT ){
		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();

		return resizedImage;
	}
	
	public static void main(String[] args) throws IOException 
	{
		System.out.println("Start converting...");
		
		//######## 1........
		File piza = new File("file:src/main/resources/pizza.html");
		BufferedImage ire = ConvertHtmlToImage.create(piza.getPath(), 800, 1000);
		ImageIO.write(ire, "png", new File("src/main/output/original/pizza.png"));
		
		//we are resize orignal images...
		int type = ire.getType() == 0? BufferedImage.TYPE_INT_ARGB : ire.getType();
		BufferedImage ire_ = ConvertHtmlToImage.resizeImage(ire, type, 300, 500);
		ImageIO.write(ire_, "png", new File("src/main/output/pizza.png"));

		//######## 2........
		File base_boxed_basic_2column_query = new File("file:src/main/resources/base_boxed_basic_2column_query.html");
		BufferedImage ire1 = ConvertHtmlToImage.create(base_boxed_basic_2column_query.getPath(), 800, 1000);
		ImageIO.write(ire1, "png", new File("src/main/output/original/base_boxed_basic_2column_query.png"));
		
		//we are resize orignal images...
		int type1 = ire1.getType() == 0? BufferedImage.TYPE_INT_ARGB : ire1.getType();
		BufferedImage ire1_ = ConvertHtmlToImage.resizeImage(ire1, type1, 300, 500);
		ImageIO.write(ire1_, "png", new File("src/main/output/base_boxed_basic_2column_query.png"));

		//######## 3........
		File Template_Thankyou_Checkin = new File("file:src/main/resources/Template_Thankyou_Checkin.html");
		BufferedImage ire2 = ConvertHtmlToImage.create(Template_Thankyou_Checkin.getPath(), 800, 1000);
		ImageIO.write(ire2, "png", new File("src/main/output/original/Template_Thankyou_Checkin.png"));
		
		//we are resize orignal images...
		int type2 = ire2.getType() == 0? BufferedImage.TYPE_INT_ARGB : ire2.getType();
		BufferedImage ire2_ = ConvertHtmlToImage.resizeImage(ire2, type2, 300, 500);
		ImageIO.write(ire2_, "png", new File("src/main/output/Template_Thankyou_Checkin.png"));

		System.out.println("Done, please check output folder");
	}
}
