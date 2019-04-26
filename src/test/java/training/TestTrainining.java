package training;

import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.basic.training.JavaBaiscTraning;

public class TestTrainining {

	private JavaBaiscTraning jbt = new JavaBaiscTraning();

	@Before
	public void before() {
		System.out.println("测试开始");
	}

	@After
	public void after() {
		System.out.println("测试结束");
	}

	@Test
	public void testInsertSort() {
		int[] arr = { 5, 1, 6, 23, 4, 8, 6, 7 };

		int[] newArr = jbt.insertSort(arr);

		for (int i : newArr) {
			System.out.print(i + " ");
		}
	}

	@Test
	public void testBubbleSort() {
		int[] arr = { 5, 1, 6, 23, 4, 8, 6, 7 };

		int[] newArr = jbt.bubbleSort(arr);

		for (int i : newArr) {
			System.out.print(i + " ");
		}
	}

	@Test
	public void testStringCompareToStringBuilder() {
		jbt.stringCompareToStringBuilder();
	}

	@Test
	@SuppressWarnings("resource")
	public void testTransFormSum() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			String sum = sc.nextLine();
			if (sum.equals("end")) {
				break;
			} else {
				System.out.println(jbt.transformSum(sum));
			}
		}
	}
}
