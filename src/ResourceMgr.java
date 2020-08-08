import java.awt.image.BufferedImage;
import java.io.IOException;

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
	
	//当当前类被导入到内存的时候，静态模块自动执行
	static {
		try {
			//用静态模块倒入坦克的图片
			tankL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
			tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
			tankR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
			tankD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankD.gif"));
			//用静态模块倒入子弹的图片
			bulletL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
			bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
			bulletR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
			bulletD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
