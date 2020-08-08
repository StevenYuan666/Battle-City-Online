
public class Main {
	//扔出报错
	public static void main(String[] args) throws InterruptedException {
		
		//新建一个坦克窗口
		TankFrame tf = new TankFrame();
		
		//初始化地方坦克的容器
		for(int i = 0; i < 5; i++) {
			tf.tanks.add(new Tank(50 + i * 80, 200, Dir.DOWN, tf));
		}
		
		//使用一个死循环，让敌人坦克一直移动起来
		while(true) {
			Thread.sleep(50);
			tf.repaint();
		}
		
	}
}
