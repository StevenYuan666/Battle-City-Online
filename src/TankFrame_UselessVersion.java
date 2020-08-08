import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame_UselessVersion {

	public static void main(String[] args) {
		//新建一个窗口
		Frame f = new Frame();
		//调整窗口的大小
		f.setSize(800,600);
		//设置不可改变大小
		f.setResizable(false);
		//设置窗口的名称
		f.setTitle("Tank War Online");
		//显示窗口
		f.setVisible(true);
		//新建窗口监听器，来实现关闭窗口 尝试
		f.addWindowListener(new WindowAdapter() {
			//方法重写
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

}
