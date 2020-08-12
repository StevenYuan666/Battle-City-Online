import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet {
	//设置子弹的好坏，自己方的子弹是打不死己方的坦克的
	private Group group;
	//group的getter和setter
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	//子弹的速度属性
	private static final int SPEED = Integer.parseInt((String) PropertyMgr.get("bulletSpeed"));;
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
	//解决子弹碰撞检测每次都得创建新Rectangle的问题
	Rectangle rect = new Rectangle();
	
	//构造器
	//也持有TankFrame的引用
	public Bullet(int x, int y, Dir dir, TankFrame tf, Group group) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.living = true;
		this.tf = tf;
		this.group = group;
		rect.x = this.x;
		rect.y = this.y;
		rect.width = Bullet.WIDTH;
		rect.height = Bullet.HEIGHT;
		//创建完之后直接装进子弹容器里
		tf.bullets.add(this);
	}
	public void paint(Graphics g) {
		if(!this.living) {
			this.tf.bullets.remove(this);
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
		//更新rectangle的值
		rect.x = this.x;
		rect.y = this.y;
	}
	public void collideWith(Tank tank) {
		//关闭友伤
		if(this.group == tank.getGroup()) return;
		//使用rectangle作为辅助
		if(this.rect.intersects(tank.rect)) {
			tank.die();
			this.die();
			//让爆炸在坦克的中心位置显示
			int eX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
			int eY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
			tf.explodes.add(new Explode(eX, eY, this.tf));
		}
	}
	private void die() {
		this.living = false;
	}
	
}
