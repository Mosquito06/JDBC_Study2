import java.awt.EventQueue;

import Frame.MainFrame;

public class ControlSysyemMain {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						MainFrame frame = new MainFrame();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

	}


