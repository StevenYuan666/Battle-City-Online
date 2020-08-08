import java.awt.Color;
import java.awt.Graphics;

public class Tank {
	//讲窗口设置为Tank的属性，以便可以让Tank的发射的子弹画出来，目的是让Tank获得TankFrame的引用，从而访问TankFrame中的内容
	private TankFrame tf;
	//设定坦克坐标的属性
		private int x;
		private int y;
		//设置坦克初始方向的属性
		Dir dir ;
		//设置坦克是否为静止状态的属性
		private boolean moving;
		//设置坦克的速度
		private final int SPEED = 5;
		//坦克的大小
		static final int WIDTH = ResourceMgr.tankD.getWidth();
		static final int HEIGHT = ResourceMgr.tankD.getHeight();;
		//调用方向
		public Dir getDir() {
			return dir;
		}
		//设置方向
		public void setDir(Dir dir) {
			this.dir = dir;
		}
		//返回移动状态
		public boolean isMoving() {
			return moving;
		}
		//设置是否移动
		public void setMoving(boolean moving) {
			this.moving = moving;
		}
		//构造器
		public Tank(int x, int y, Dir dir, TankFrame tf) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.moving = false;
			this.tf = tf;
		}
		public void paint(Graphics g) {
			//坐标左上角为（0，0），向右为x轴，向下为y轴
			//将坦克的初始坐标改为变量，以便让坦克动起来
			//将坦克图片画进来
			switch (this.dir){
				case LEFT:
					g.drawImage(ResourceMgr.tankL, x, y, null);
					break;
				case UP:
					g.drawImage(ResourceMgr.tankU, x, y, null);
					break;
				case RIGHT:
					g.drawImage(ResourceMgr.tankR, x, y, null);
					break;
				case DOWN:
					g.drawImage(ResourceMgr.tankD, x, y, null);
					break;
				default:
					break;
			}
			
				
			
			move();
		}
		private void move() {
			//如果坦克是静止的话，直接return，不执行后面的内容
			if(!moving) return;
			//根据坦克的方向来移动坦克
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
		}
		public void fire() {
			int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
			int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
			//每当开火的时候创建一个子弹，并且使子弹具有与坦克相同的方向和位置
			//把创建出来的子弹装到容器当中
			this.tf.bullets.add(new Bullet(bX, bY, this.dir, this.tf));
		}
		
		
		
}
