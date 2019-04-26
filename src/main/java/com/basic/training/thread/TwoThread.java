package com.basic.training.thread;

public class TwoThread implements Runnable {
	@Override
	public void run() {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + System.currentTimeMillis());
	}

}