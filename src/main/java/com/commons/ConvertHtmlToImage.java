package com.commons;

import java.awt.Container;
import java.awt.Graphics;
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
		public static void main(String[] args) throws IOException 
		{
			System.out.println("Start converting...");
			File piza = new File("file:src/main/resources/pizza.html");
			BufferedImage ire = ConvertHtmlToImage.create(piza.getPath(), 1024, 500);
			ImageIO.write(ire, "png", new File("src/main/output/pizza.png"));
			
			
			File base_boxed_basic_2column_query = new File("file:src/main/resources/base_boxed_basic_2column_query.html");
			BufferedImage ire1 = ConvertHtmlToImage.create(base_boxed_basic_2column_query.getPath(), 1024, 5000);
			ImageIO.write(ire1, "png", new File("src/main/output/base_boxed_basic_2column_query.png"));
			
			File Template_Thankyou_Checkin = new File("file:src/main/resources/Template_Thankyou_Checkin.html");
			BufferedImage ire2 = ConvertHtmlToImage.create(Template_Thankyou_Checkin.getPath(), 1024, 5000);
			ImageIO.write(ire2, "png", new File("src/main/output/Template_Thankyou_Checkin.png"));
			
			System.out.println("Done, please check output folder");
		}
}
