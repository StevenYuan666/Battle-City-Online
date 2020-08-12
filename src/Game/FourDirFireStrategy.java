package Game;
public class FourDirFireStrategy implements FireStrategy{

	@Override
	public void fire(Tank t) {
		int bX = t.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
		int bY = t.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
		//每当开火的时候创建一个子弹，并且使子弹具有与坦克相同的方向和位置
		//遍历四个方向 每个方向都发射一枚子弹
		Dir[] dirs = Dir.values();
		for(Dir dir : dirs) {
			new Bullet(bX, bY, dir, t.tf, t.group);
		}
		//倒入声音文件
		if(t.group == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
	}
	
}
