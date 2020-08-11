import java.awt.*;
import java.awt.List;
import java.util.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TankFrame extends Frame{
	//创建一个新的我的坦克
	Tank myTank = new Tank(200, 400, Dir.DOWN, this, Group.GOOD);
	//创建一个装子弹的容器
	ArrayList<Bullet> bullets = new ArrayList<>();
	//创建一个敌人坦克的容器
	ArrayList<Tank> tanks = new ArrayList<>();
	//创建一个装爆炸的容器
	ArrayList<Explode> explodes = new ArrayList<>();
	
	//设置游戏画面的大小
	static final int GAME_WIDTH = 1200;
	static final int GAME_HEIGHT = 800;
	
	//构造器,这样可以通过在主函数中新建一个TankFrame的对象来实现新建窗口
	public TankFrame(){
		//调整窗口的大小
		this.setSize(GAME_WIDTH,GAME_HEIGHT);
		//设置不可改变大小
		this.setResizable(false);
		//设置窗口的名称
		this.setTitle("Tank War Online");
		//显示窗口
		this.setVisible(true);
		//添加一个键盘监听器
		this.addKeyListener(new MyKeyListener());
		//新建窗口监听器，来实现关闭窗口 尝试
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	
	//解决坦克和子弹的闪烁问题，双缓冲
	//游戏中常用的解决闪烁的方法
	//原理相当于，我们现在内存上画好一张图片，然后再把画好的整张图片画到我们的窗口上，就可以消除闪烁问题
	Image offScreenImage = null;
	//update调用的优先级是高于paint的
	@Override
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	@Override
	//paint方法，每当重新画画布的时候，就会被自动调用
	//Graphics相当于是画笔
	public void paint(Graphics g){
		//看看子弹和坦克的数量
		g.drawString("Number of Bullets " + this.bullets.size(),10, 60);
		g.drawString("Number of Tanks " + this.tanks.size() , 10, 100);
		g.drawString("Number of Explodes " + this.explodes.size() , 10, 140);
		//让坦克自己给自己画出来
		myTank.paint(g);
		//让容器中的每个子弹都给自己画出来
		//这里不要用for each方法
		for(int i = 0; i < bullets.size(); i ++) {
			bullets.get(i).paint(g);
		}
		//让容器中的每个敌人都给自己画出来
		for(int i = 0; i < tanks.size(); i ++) {
			tanks.get(i).paint(g);
		}
		//让容器中的每个爆炸都给自己画出来
		for(int i = 0; i < explodes.size(); i ++) {
			explodes.get(i).paint(g);
		}
		//做每颗子弹和每辆坦克的碰撞检测//使用嵌套循环
		for(int i = 0; i < bullets.size(); i ++) {
			for(int j = 0; j < tanks.size(); j ++) {
				bullets.get(i).collideWith(tanks.get(j));
			}
		}
	}
	//编写内部类键盘监听器，来管理我们希望键盘监听器干的事情，从Key adapter继承
	class MyKeyListener extends KeyAdapter{
		//初始四个布尔值来判断坦克方向的状态
		boolean bL = false;
		boolean bU = false;
		boolean bR = false;
		boolean bD = false;
		
		//按下任意一个建的时候会自动调用
		@Override
		public void keyPressed(KeyEvent e) {
			//来获取按键的代码，从而得知用户按下了哪个建
			int key = e.getKeyCode();
			//然后使用switch语句来分情况讨论
			//通过改变布尔值，来确定坦克面对的方向，从而使坦克可以斜着走
			switch(key) {
			case KeyEvent.VK_LEFT:
				bL = true;
				break;
			case KeyEvent.VK_UP:
				bU = true;
				break;
			case KeyEvent.VK_RIGHT:
				bR = true;
				break;
			case KeyEvent.VK_DOWN:
				bD = true;
				break;
			default:
				break;
			}
			//改变坦克的方向
			setMainTankDir();
			//让主战坦克走的时候有声音
			//new Thread(()->new Audio("audio/tank_move.wav").play()).start();
		}
		//松开任意一个建的时候会自动调用
		@Override
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			//再给布尔值设置会false
			switch(key) {
			case KeyEvent.VK_LEFT:
				bL = false;
				break;
			case KeyEvent.VK_UP:
				bU = false;
				break;
			case KeyEvent.VK_RIGHT:
				bR = false;
				break;
			case KeyEvent.VK_DOWN:
				bD = false;
				break;
			//在抬起这里设置抬起control建会发射子弹，否则一直按着的话就会一直发射
			case KeyEvent.VK_CONTROL:
				//调用坦克的开火方法
				myTank.fire();
				break;
			default:
				break;
			}
			//改变坦克的方向
			setMainTankDir();
		}
		//设置主战坦克方向的方法
		private void setMainTankDir() {
			//调整坦克的移动状态
			myTank.setMoving(true);
			//根据布尔值 调整主战坦克的方向
			if(bL) myTank.setDir(Dir.LEFT);
			if(bU) myTank.setDir(Dir.UP);
			if(bR) myTank.setDir(Dir.RIGHT);
			if(bD) myTank.setDir(Dir.DOWN);
			//如果没动的话，则设置坦克状态为静止
			if(!bL && !bU && !bR && !bD) {
				myTank.setMoving(false);
			}
		}
	}
}
