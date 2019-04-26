package training;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.basic.training.thread.OneThread;
import com.basic.training.thread.ThreeThread;
import com.basic.training.thread.TwoThread;

class TestThread {

	@Before
	public void before() {

	}

	@After
	public void after() {

	}

	@Test
	public void testSimpleCreate() throws InterruptedException, ExecutionException {
		System.out.println("主线程开始");
		
		// 继承Thread类
		OneThread t1 = new OneThread();
		t1.setName("OneThread");
		t1.start();
		// 实现Runnable接口
		Thread t2 = new Thread(new TwoThread(), "TwoThread");
		t2.start();
		// 实现Callable接口
		FutureTask<String> future = new FutureTask<>(new ThreeThread());
		Thread t3 = new Thread(future, "ThreeThread");
		t3.start();
		// get()会阻塞线程
		System.out.println(future.get());
		
		System.out.println("主线程结束");
	}

}
