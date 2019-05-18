package com.cn.util;

public class UrlUtils {

	public static boolean matching(String reg, String input) {
		if ("/*".equals(reg))
			return true;
		//按 * 切割字符串
		String[] reg_split = reg.split("\\*");
		int index = 0, reg_len = reg_split.length;
		//b代表匹配模式的最后一个字符是否是 '*' ,因为在split方法下最后一个 * 会被舍弃
		boolean b = reg.charAt(reg.length() - 1) == '*' ? true : false;
		while (input.length() > 0) {
			//如果匹配到最后一段,比如这里reg的landingsuper
			if (index == reg_len) {
				if (b)//如果reg最后一位是 * ,代表通配,后面的就不用管了,返回true
					return true;
				else  //后面没有通配符 * ,但是input长度还没有变成0 (此时input = context=%7B%22nid%22%3...),显然不匹配
					return false;
			}
			String r = reg_split[index++];
			int indexOf = input.indexOf(r);
			if (indexOf == -1)
				return false;
			//前面匹配成功的就可以不用管了,截取掉直接从新地方开始
			input = input.substring(indexOf + r.length());
		}
		return true;
	}
	
	public static void main(String[] args) {
		boolean flag = UrlUtils.matching("*/*.ico", "/favicon.ico");
		System.out.println(flag);
	}
}
