package com.basic.training;

public class JavaBaiscTraning {

	/**
	 * 插入排序法 T(n) = n*n
	 * 
	 * @param arr
	 * @return Arrays
	 */
	public int[] insertSort(int[] arr) {
		if (arr == null) {
			return null;
		}
		int j, temp;
		for (int i = 1; i < arr.length; i++) {
			j = i - 1;
			temp = arr[i];
			while (j >= 0 && arr[j] > temp) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = temp;
		}
		return arr;
	}

	/**
	 * 冒泡排序法 T(n) = n*n
	 * 
	 * @param arr
	 * @return Arrays
	 */
	public int[] bubbleSort(int[] arr) {
		if (arr == null) {
			return null;
		}
		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j < arr.length - i; j++) {
				if (arr[j] > arr[j + 1]) {
					arr[j] = arr[j] ^ arr[j + 1];
					arr[j + 1] = arr[j + 1] ^ arr[j];
					arr[j] = arr[j] ^ arr[j + 1];
				}
			}
		}
		return arr;
	}

	/**
	 * String类与Stringbuilder类的比较
	 */
	public void stringCompareToStringBuilder() {
		String str = new String("");
		long strStartTime = System.currentTimeMillis();
		for (int i = 0; i < 99999; i++) {
			str = str + i;
		}
		long strEndTime = System.currentTimeMillis();
		System.out.println("String类追加操作用时：" + (strEndTime - strStartTime));
		StringBuilder builder = new StringBuilder("");
		long builderStartTime = System.currentTimeMillis();
		for (int i = 0; i < 99999; i++) {
			builder.append(i);
		}
		long builderEndTime = System.currentTimeMillis();
		System.out.println("StringBuilder类追加操作用时：" + (builderEndTime - builderStartTime));
	}

	// 常量的定义
	final String[] number = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
	final String[] unit = { "亿", "万", "仟", "佰", "拾", "元", "角", "分", "正", "整" };

	/**
	 * 数字金额转换为大写
	 * 
	 * @param sum
	 * @return
	 */
	public String transformSum(String sum) {

		String regexNum = "[1-9][0-9]{0,}$";
		String regexDecOne = "[0][.][0-9]{0,2}$";
		String regexDecTwo = "[0]{0,}[.][0]{0,}$";
		String regexMix = "[1-9][0-9]{0,}[.][0-9]{0,2}$";

		boolean isNum = sum.matches(regexNum);
		boolean isDecOne = sum.matches(regexDecOne);
		boolean isDecTwo = sum.matches(regexDecTwo);
		boolean isMix = sum.matches(regexMix);

		// 判断入力值的正确性
		if ((!isNum && !isDecOne && !isMix) || isDecTwo) {
			return new String("请正确输入金额");
		}

		// 整数位
		String num = "";
		// 小数位
		String dec = "";

		// 单线程中比StringBuffer和String类要快
		StringBuilder builder = new StringBuilder();

		// 123
		if (isNum) {
			builder.append(this.transformNum(sum, false));
			builder.append(unit[9]);
			return new String(builder);
		}

		// 将整数与小数分离
		String[] sumSp = String.valueOf(sum).split("\\."); // 特殊字符需要转义 ！@#￥%^等
		num = sumSp[0];
		dec = sumSp[1];
		if (dec.length() < 2) {
			dec = dec + "0";
		}

		// 0.12
		if (isDecOne) {
			builder.append(this.transformDec(dec, true));
		}

		// 123.12
		if (isMix) {
			builder.append(this.transformNum(num, false));
			builder.append(this.transformDec(dec, false));
		}

		return new String(builder);
	}

	/**
	 * 整数位转换
	 * 
	 * @param num
	 * @param isDecCheck
	 * @return
	 */
	private StringBuilder transformNum(String num, boolean isDec) {
		StringBuilder builder = new StringBuilder();
		if (isDec) {
			builder.append(unit[9]);
			return builder;
		}
		for (int i = num.length() - 1; i >= 0; i--) {
			// 10的i次幂
			int p = (int) Math.pow(10, i);
			String str = String.valueOf(Integer.parseInt(num) / p);
			int index = Integer.parseInt(String.valueOf(str.charAt(str.length() - 1)));
			builder.append(number[index]);
			if (p > 10000 && p < 100000000) {
				p = p / 10000;
			}
			if (p > 100000000) {
				p = p / 100000000;
			}
			if (index == 0) {
				continue;
			}
			switch (p) {
			case 10:
				builder.append(unit[4]);
				break;
			case 100:
				builder.append(unit[3]);
				break;
			case 1000:
				builder.append(unit[2]);
				break;
			case 10000:
				builder.append(unit[1]);
				break;
			case 100000000:
				builder.append(unit[0]);
				break;
			}
		}
		this.deleteMidsWithZero(builder);
		this.deleteEndsWithZero(builder);
		return builder.append(unit[5]);
	}

	/**
	 * 小数位转换
	 * 
	 * @param dec
	 * @return
	 */
	private StringBuilder transformDec(String dec, boolean isDec) {
		StringBuilder builder = new StringBuilder();
		if (dec.equals("00") && !isDec) {
			builder.append(unit[9]);
			return builder;
		}
		int len = dec.length();
		for (int i = len - 1; i >= 0; i--) {
			int p = (int) Math.pow(10, i);
			String str = String.valueOf(Integer.parseInt(dec) / p);
			int index = Integer.parseInt(String.valueOf(str.charAt(str.length() - 1)));
			if (!isDec) {
				builder.append(number[index]);
			} else if (isDec && index != 0) {
				builder.append(number[index]);
			}
			if (index == 0) {
				continue;
			}
			switch (p) {
			case 1:
				builder.append(unit[7]);
				break;
			case 10:
				builder.append(unit[6]);
				break;
			}
		}
		this.deleteEndsWithZero(builder);
		return builder;
	}

	private void deleteEndsWithZero(StringBuilder sb) {
		String bs = String.valueOf(sb);
		int index = bs.length();
		while (bs.endsWith(number[0])) {
			index = index - 1;
			sb.deleteCharAt(index);
			bs = String.valueOf(sb);
		}
	}

	private void deleteMidsWithZero(StringBuilder sb) {
		String bs = String.valueOf(sb);
		for (int i = 0; i < bs.length() - 1; i++) {
			if (String.valueOf(bs.charAt(i)).equals(number[0]) && String.valueOf(bs.charAt(i + 1)).equals(number[0])) {
				sb.deleteCharAt(i + 1);
				bs = String.valueOf(sb);
				i = i - 1;
			}
		}
	}
}
