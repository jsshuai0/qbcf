package com.ujuit.qbcf.bean;


public enum ws_code {
	
	

	UNKNOWN(-1),
	SUCCESS(0), //成功
	FAIL(1), //失败
	NO_LOGIN(2),//无权限访问
	PROG_ERROR(3), //服务器端错误
	LOST_POST_DATA(4), //数据丢失，缺失请求参数
//	NO_PERMISSION(6),
	NONET(1000),
	SLOWNET(1001),
	JSONERROR(1100);
	  

	int value;
	ws_code(int value){
		this.value = value;
	}
	
	
	public static ws_code parse(int value){
		
		switch (value) {
			case 0:
				return SUCCESS;
			case 1:
				return FAIL;
			case 2:
				return NO_LOGIN;
			case 3:
				return PROG_ERROR;
			case 4:
				return LOST_POST_DATA;
			case 1000:
				return NONET;
			case 1100:
				return JSONERROR;
				//TODO 待定
			default:
				return UNKNOWN;
		}
	}
}
