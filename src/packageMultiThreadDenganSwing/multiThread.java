package packageMultiThreadDenganSwing;

public class multiThread {

//	public multiThread() {
		// TODO Auto-generated constructor stub
		
		
//	}
	
	public static void main(String[] args) {
		Thread thread1 = new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i<= 10; i++) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					System.out.println("Detik ke " + i + " dari thread 1");
				}
			}
		};
		Thread thread2 = new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i<= 3; i++) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					System.out.println("Detik ke " + i + " dari thread 2");
				}
			}
		};
		
		counter thread3 = new counter();
		
		thread1.setDaemon(false);
		thread2.setDaemon(true);
		thread3.start();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		thread1.start();
		thread2.start();
		
	}

}
