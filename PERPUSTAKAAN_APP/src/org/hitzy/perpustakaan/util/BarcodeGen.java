package org.hitzy.perpustakaan.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.configuration.DefaultConfiguration;
import org.krysalis.barcode4j.BarcodeException;
import org.krysalis.barcode4j.BarcodeGenerator;
import org.krysalis.barcode4j.BarcodeUtil;
import org.krysalis.barcode4j.impl.upcean.EAN13;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

public class BarcodeGen {

	public static void mains(String[] args) {
//		Interleaved2Of5Bean bean = new Interleaved2Of5Bean();
		EAN13 bean = new EAN13();
//	    bean.setHeight(10d);
//
//	    bean.doQuietZone(false);

	    OutputStream out;
		try {
			out = new java.io.FileOutputStream(new File("D:\\output2.png"));

	    BitmapCanvasProvider provider = new BitmapCanvasProvider(out, "image/x-png", 110, BufferedImage.TYPE_BYTE_GRAY, false, 0);
	    bean.generateBarcode(provider, "9789791600910");

	    provider.finish();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws ConfigurationException, BarcodeException, IOException {

	    BarcodeUtil util = BarcodeUtil.getInstance();
	    BarcodeGenerator gen = util.createBarcodeGenerator(buildCfg("code128"));

	    OutputStream fout = new FileOutputStream("D:\\code128.jpg");
	    int resolution = 200;
	    BitmapCanvasProvider canvas = new BitmapCanvasProvider(
	        fout, "image/jpeg", resolution, BufferedImage.TYPE_BYTE_BINARY, false, 0);

	    gen.generateBarcode(canvas, "12345678");
	    canvas.finish();
	  }

	  private static Configuration buildCfg(String type) {
	    DefaultConfiguration cfg = new DefaultConfiguration("barcode");

	    //Bar code type
	    DefaultConfiguration child = new DefaultConfiguration(type);
	      cfg.addChild(child);
	    
	      //Human readable text position
	      DefaultConfiguration attr = new DefaultConfiguration("human-readable");
	      DefaultConfiguration subAttr = new DefaultConfiguration("placement");
	        subAttr.setValue("bottom");
	        attr.addChild(subAttr);
	        
	        child.addChild(attr);
	    return cfg;
	  }
}
