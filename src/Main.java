
public class Main {
	//扔出报错
	public static void main(String[] args) throws InterruptedException {
		
		//新建一个坦克窗口
		TankFrame tf = new TankFrame();
		
		//初始化地方坦克的容器
		int initialTankAccount = (int) PropertyMgr.get("initialTankAccount");
		for(int i = 0; i < initialTankAccount; i++) {
			tf.tanks.add(new Tank(50 + i * 80, 200, Dir.DOWN, tf, Group.BAD));
		}
		//TODO:记得把声音加上
		new Thread(()->new Audio("audio/war1.wav").loop()).start();
		
		//使用一个死循环，让敌人坦克一直移动起来
		while(true) {
			Thread.sleep(25);
			tf.repaint();
		}
		
	}
}
