package gallery;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtil {
	public static final int SAME = -1;
	public static final int RATIO = 0;
	
	public static void resize(File src, File dest, int width, int height) throws IOException{
		
		FileInputStream srcls = null;
		
		try {
			srcls = new FileInputStream(src);
			ImageUtil.resize(srcls, dest, width, height);
			
		}finally {
			if(srcls != null) try {srcls.close();} catch(IOException ex) {}
		}
	
	}

	
	public static void resize(InputStream src, File dest, int width, int height) throws IOException{
		
		BufferedImage srcImg = ImageIO.read(src);
		int srcWidth = srcImg.getWidth();
		int srcHeight = srcImg.getHeight();
		
		int destWidth = -1;
		int destHeight = -1;
		
		if(width == SAME) {
			destWidth = srcWidth;
		}else if(width > 0) {
			destWidth = width;
		}
		
		
		if(height == SAME) {
			destHeight = srcHeight;
		}else if(height > 0) {
			destHeight = height;
		}
		
		if(width == RATIO && height == RATIO) {
			destWidth = srcWidth;
			destHeight = srcHeight;
			
		} else if(width == RATIO) {
			double ratio = ((double)destHeight) / ((double)srcHeight);
			destWidth = (int)((double)srcWidth * ratio);
			
		} else if(height == RATIO) {
			double ratio = ((double)destWidth) / ((double)srcWidth);
			destHeight = (int)((double)srcHeight * ratio);
		}
		
		BufferedImage destImg = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g = destImg.createGraphics();
		g.drawImage(srcImg, 0, 0, destWidth, destHeight, null);
		
		ImageIO.write(destImg, "jpg", dest);
		
	}
	
	
	
}
