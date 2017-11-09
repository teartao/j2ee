package com.haycm.Exceptions;


public class GoodsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

    public GoodsException (String userName,String userid){
        super("查询用户失败，用户不存在！");
    }
    public GoodsException (String args[],String arg){
        super("添加用户失败，用户已存在！");
    }
    public GoodsException (String arg[]){
        super("更新用户失败，用户不存在！");
    }
    public GoodsException (String arg){
        super("删除用户失败，用户不存在！");
    }

}
