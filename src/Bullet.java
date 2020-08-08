import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet {
	
	//子弹的速度属性
	private static final int SPEED = 10;
	//子弹的坐标属性
	private int x;
	private int y;
	//子弹的方向属性
	private Dir dir;
	//子弹的大小
	static final int WIDTH = ResourceMgr.bulletD.getWidth();
	static final int HEIGHT = ResourceMgr.bulletD.getHeight();;
	//判断子弹是否飞出了界面
	private boolean living;
	//持有TankFrame的引用
	private TankFrame tf;
	
	//构造器
	//也持有TankFrame的引用
	public Bullet(int x, int y, Dir dir, TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.living = true;
		this.tf = tf;
	}
	public void paint(Graphics g) {
		if(!this.living) {
			return;
		}
		//让子弹可以自己把自己画出来
		switch (this.dir) {
		case LEFT:
			g.drawImage(ResourceMgr.bulletL, x, y, null);
			break;
		case UP:
			g.drawImage(ResourceMgr.bulletU, x, y, null);
			break;
		case RIGHT:
			g.drawImage(ResourceMgr.bulletR, x, y, null);
			break;
		case DOWN:
			g.drawImage(ResourceMgr.bulletD, x, y, null);
			break;
		default:
			break;
		}
		move();
	}
	
	private void move() {
		//根据子弹的方向来移动子弹
		switch(dir) {
		case LEFT:
			x -= SPEED;
			break;
		case UP:
			y -= SPEED;
			break;
		case RIGHT:
			x += SPEED;
			break;
		case DOWN:
			y += SPEED;
			break;
		default:
			break;
		}
		//修改子弹的状态
		if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
			this.living = false;
		}
	}
	public void collideWith(Tank tank) {
		//使用rectangle作为辅助
		Rectangle b = new Rectangle(this.x, this.y, Bullet.WIDTH, Bullet.HEIGHT);
		Rectangle t = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
		if(b.intersects(t)) {
			tank.die();
			this.die();
		}
	}
	private void die() {
		this.living = false;
	}
	
}
