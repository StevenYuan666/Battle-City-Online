package Game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Tank {
	//添加成员变量FireStrategy
	FireStrategy fs;
	//设置坦克的rectangle
	Rectangle rect = new Rectangle();
	//设置随机变量
	private Random random = new Random();
	//设置坦克的组别，好的还是坏的
	Group group;
	//讲窗口设置为Tank的属性，以便可以让Tank的发射的子弹画出来，目的是让Tank获得TankFrame的引用，从而访问TankFrame中的内容
	TankFrame tf;
	//设定坦克坐标的属性
		int x;
		int y;
		//坐标x的getter和setter
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		//坐标y的getter和setter
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		//设置坦克初始方向的属性
		Dir dir ;
		//设置坦克是否为静止状态的属性
		private boolean moving = true;
		//设置坦克的速度
		private final int SPEED = Integer.parseInt((String) PropertyMgr.get("tankSpeed"));;
		//坦克的大小
		static final int WIDTH = ResourceMgr.badTankU.getWidth();
		static final int HEIGHT = ResourceMgr.badTankU.getHeight();;
		//设置坦克的存亡属性
		private boolean living;
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
		public Tank(int x, int y, Dir dir, TankFrame tf, Group group) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.tf = tf;
			this.living = true;
			this.group = group;
			rect.x = this.x;
			rect.y = this.y;
			rect.width = Tank.WIDTH;
			rect.height = Tank.HEIGHT;
			//区分敌我的开火方式
			if(this.group == Group.GOOD) {
				String goodFSName = (String) PropertyMgr.get("goodFS");
				//把这个名字代表的类load到内存
				try {
					fs = (FireStrategy) Class.forName(goodFSName).newInstance();
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			else {
				String badFSName = (String) PropertyMgr.get("badFS");
				try {
					fs = (FireStrategy) Class.forName(badFSName).newInstance();
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			
		}
		public void paint(Graphics g) {
			//如果坦克死了的话，就不画了
			if(!this.living) {
				this.tf.tanks.remove(this);
			}
			//坐标左上角为（0，0），向右为x轴，向下为y轴
			//将坦克的初始坐标改为变量，以便让坦克动起来
			//将坦克图片画进来
			//要区分好坦克和坏坦克的图片
			switch (this.dir){
				case LEFT:
					g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
					break;
				case UP:
					g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankU :ResourceMgr.badTankU, x, y, null);
					break;
				case RIGHT:
					g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankR :ResourceMgr.badTankR, x, y, null);
					break;
				case DOWN:
					g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankD :ResourceMgr.badTankD, x, y, null);
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
			//随机发射子弹
			if(this.group == Group.BAD && random.nextInt(100) > 95) {
				this.fire();
			}
			if(this.group == Group.BAD && random.nextInt(100) > 95) {
				randomDir();
			}
			//坦克的边界检测
			boundsCheck();
			//更新rectangle的值
			rect.x = this.x;
			rect.y = this.y;
		}
		private void boundsCheck() {
			if(x < 0) {
				this.x = 0;
			}
			//上面有菜单栏的位置
			if(y < 30) {
				this.y = 30;
			}
			if(x > TankFrame.GAME_WIDTH - Tank.WIDTH) {
				this.x = TankFrame.GAME_WIDTH - Tank.WIDTH;
			}
			if(y > TankFrame.GAME_HEIGHT - Tank.HEIGHT) {
				this.y = TankFrame.GAME_HEIGHT - Tank.HEIGHT;
			}
		}
		//随机改变方向
		private void randomDir() {
			this.dir = Dir.values()[random.nextInt(4)];
		}
		//每次调用的时候传参数就要new一个新的object，这样会浪费内存空间，所以最好把DefaultFireStrategy设计成单例
		//或者设计成成员变量，作为这个类的一个属性
		//这里采用成员变量的设计方法
		public void fire() {
			fs.fire(this);
		}
		//group的getter和setter
		public Group getGroup() {
			return group;
		}
		public void setGroup(Group group) {
			this.group = group;
		}
		public void die() {
			this.living = false;
		}
		
		
		
}