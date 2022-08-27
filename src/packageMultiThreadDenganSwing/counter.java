package packageMultiThreadDenganSwing;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class counter extends Thread {
	
	public counter() {
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		JFrame f = new JFrame();
		f.setSize(400,400);
		f.setEnabled(true);
		f.setVisible(true);
		f.setTitle("Counter");
//		f.setLocationRelativeTo(null);
		f.setResizable(true);
		f.setLayout(new BorderLayout(10,10));
//		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		int hour = 0;
		int minute = 0;
		int second = 0;
		
		JLabel Header, hourL, minuteL, secondL;
		Header = new JLabel("Thread run time", SwingConstants.CENTER);
		hourL = new JLabel(String.valueOf(hour), SwingConstants.CENTER);
		minuteL = new JLabel(String.valueOf(minute), SwingConstants.CENTER);
		secondL = new JLabel(String.valueOf(second), SwingConstants.CENTER);
		
		f.setLayout(new GridLayout(4,1));
		f.add(Header);
		f.add(hourL);
		f.add(minuteL);
		f.add(secondL);
		
		
		while(second < 5) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			second++;
			minute += (second == 60) ? 1 : 0;
			hour += (minute == 60) ? 1 : 0;
			
			hourL.setText(String.valueOf(hour));
			minuteL.setText(String.valueOf(minute));
			secondL.setText(String.valueOf(second));
			
			second = (second == 60 ) ? 1 : second;
			minute = (minute == 60) ? 1 : minute;
		}
		
//		if (second == 5) {
			f.dispose();
//		}
		
		
	}

}
