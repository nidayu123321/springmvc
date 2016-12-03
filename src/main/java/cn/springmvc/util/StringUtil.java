/*
 * PROPRIETARY/CONFIDENTIAL
 */
package cn.springmvc.util;

import java.util.Scanner;

/**
 * @author think
 * @date 2014年7月24日
 */
public class StringUtil {

	public static String subStrIgnoreFirst(String stext, String etext, String text) {
		int sindex = text.indexOf(stext);
		if (sindex >= 0) {
			int eindex = text.indexOf(etext, sindex+stext.length());
			if (eindex >= 0) {
				String ctext = text.substring(sindex + stext.length(), eindex);
				return ctext;
			}
		}
		return "";
	}

	public static String inputYanzhengma(){
		// 输出的文件流
		return inputYanzhengma("请输入验证码:");
	}
	public static String inputYanzhengma(String label){
		// 输出的文件流
		System.out.println(label);
		Scanner in = new Scanner(System.in);
		String name = in.nextLine();
		return name;
	}

}
