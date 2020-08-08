import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.image.*;
import java.io.IOException;
import java.awt.*;

import javax.imageio.ImageIO;

public class ResourceMgr {
	//初始坦克四个方向的图片
	public static BufferedImage tankL;
	public static BufferedImage tankU;
	public static BufferedImage tankR;
	public static BufferedImage tankD;
	//初始子弹四个方向的图片
	public static BufferedImage bulletL;
	public static BufferedImage bulletU;
	public static BufferedImage bulletR;
	public static BufferedImage bulletD;	
	//初始化爆炸图片数组
	public static BufferedImage[] explosion = new BufferedImage[16];
	
	//当当前类被导入到内存的时候，静态模块自动执行
	static {
		try {
			//用静态模块倒入坏坦克的图片
			tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
			tankL = ImageUtil.rotateImage(tankU, -90);
			tankR = ImageUtil.rotateImage(tankU, 90);
			tankD = ImageUtil.rotateImage(tankU, 180);
			//用静态模块倒入子弹的图片
			bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
			bulletL = ImageUtil.rotateImage(bulletU, -90);
			bulletR = ImageUtil.rotateImage(bulletU, 90);
			bulletD = ImageUtil.rotateImage(bulletU, 180);
			//用静态模块倒入爆炸的图片
			for(int i = 0; i < 16; i ++) {
				explosion[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i+1) + ".gif"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
