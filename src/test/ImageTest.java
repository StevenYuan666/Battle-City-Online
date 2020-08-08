package test;
//这个类看不太懂是干什么的，先尝试理解

import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

class ImageTest {

	@Test
	void test() {
		
		try {
			BufferedImage image = ImageIO.read(new File("/Users/stevenyuan/Documents/个人编程项目/TankWar/src/images/BadTank1.png"));
			assertNotNull(image);
			
			BufferedImage image2 = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
			assertNotNull(image2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
