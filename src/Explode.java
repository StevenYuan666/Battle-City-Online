import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Explode {
	//爆炸的坐标属性
	private int x;
	private int y;
	//爆炸的大小
	static final int WIDTH = ResourceMgr.explosion[0].getWidth();
	static final int HEIGHT = ResourceMgr.explosion[0].getHeight();;
	//持有TankFrame的引用
	private TankFrame tf;
	//记录爆炸数组中图片画到第几个了
	private int step = 0;
	
	//构造器
	//也持有TankFrame的引用
	public Explode(int x, int y, TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.tf = tf;
		//倒入声音文件
		new Thread(()->new Audio("audio/explode.wav").play()).start();
	}
	public void paint(Graphics g) {
		//一次画出爆炸数组中的一张图片
		g.drawImage(ResourceMgr.explosion[step], x, y, null);
		//更新step的值
		step ++;
		//设置停止画的条件
		if(step >= ResourceMgr.explosion.length) {
			step = 0;
			this.tf.explodes.remove(this);
		}
	}
	
}
