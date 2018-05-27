package com.tonggu.util;

import java.util.HashMap;
import java.util.Map;

public class Constant {
	/*分页长度*/
	public final static int PAGE_LENGTH = 10;
	
	@SuppressWarnings("serial")
	public final static Map<String, Integer> SHAPE_DICTIONARY = new HashMap<String , Integer>(){{
	    put("圆形", 1);
	    put("公主方形", 2);
	    put("祖母绿", 3);
	    put("橄榄形", 4);
	    put("椭圆形", 5);
	    put("雷帝恩形", 6);
	    put("心形", 7);
	    put("梨形", 8);
	    put("枕形", 9);
	    put("其他", 10);
	    }};
	    
	/******状态--有效******/    
	public final static String RECORD_STATUS_VALID = "有效";
	/******状态--无效******/  
	public final static String RECORD_STATUS_INVALID = "无效";
	
	/******菜单根ID******/  
	public final static String MENU_ROOT_ID = "0";
	
	/******菜单类型--菜单******/  
	public final static String MENU_TYPE_01 = "菜单";
	/******菜单类型--按钮******/  
	public final static String MENU_TYPE_02 = "按钮";
}
