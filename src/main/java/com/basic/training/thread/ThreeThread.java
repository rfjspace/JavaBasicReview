package com.basic.training.thread;

import java.util.concurrent.Callable;

public class ThreeThread implements Callable<String> {
	@Override
	public String call() throws Exception {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return Thread.currentThread().getName() + System.currentTimeMillis();
	}

}
