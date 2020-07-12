package com.soecode.lyf.util;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

import org.junit.Test;

public class BigDataDispose  {

	public void run() {
		String url = "jdbc:mysql://192.168.10.48:3306/master?useUnicode=true&amp;characterEncoding=utf-8";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "root";

        java.sql.Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, userName, password);// 获取连接
            conn.setAutoCommit(false);// 关闭自动提交，不然conn.commit()运行到这句会报错
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        // 开始时间
        Long begin = new Date().getTime();
        // SQL前缀
        String prefix = " insert into user (id, real_name) VALUES";
        // 保存SQL后缀
        StringBuffer suffix = new StringBuffer();
        try {
            // 设置事务为非自动提交
            conn.setAutoCommit(false);
            // 比起st，pst会更好些
            java.sql.PreparedStatement  pst = conn.prepareStatement("");//准备执行语句
            // 外层循环，总提交事务次数
            for (int i = 1; i <= 10; i++) {
                suffix = new StringBuffer();
                // 第j次提交步长
                for (int j = 1; j <= 10000; j++) {
                    String num = String.valueOf(i * j);
                    if (num.length() < 7) {
                        for (int a = num.length(); a < 7; a++) {
                            num = "0" + num;
                        }
                    }
                    num = "DJ" + num;
                    // 构建SQL后缀
                    suffix.append("('" + UUID.randomUUID().toString() + "', '" + num + "'),");
                }
                // 构建完整SQL
                String sql = prefix + suffix.substring(0, suffix.length() - 1);
                // 添加执行SQL
                pst.addBatch(sql);
                // 执行操作
                pst.executeBatch();
                // 提交事务
                conn.commit();
                // 清空上一次添加的数据
                suffix = new StringBuffer();
            }
            // 关闭连接
            pst.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 结束时间
        Long end = new Date().getTime();
        // 耗时
        System.out.println("30万条数据插入花费时间 : " + (end - begin) + " s"+"  插入完成");
	}

	
	@Test
	public void delete() {
		String url = "jdbc:mysql://192.168.10.48:3306/master?useUnicode=true&amp;characterEncoding=utf-8";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "root";

        java.sql.Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, userName, password);// 获取连接
            conn.setAutoCommit(false);// 关闭自动提交，不然conn.commit()运行到这句会报错
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        // 开始时间
        Long begin = new Date().getTime();
        try {
            // 设置事务为非自动提交
            conn.setAutoCommit(false);
            // 比起st，pst会更好些
            java.sql.PreparedStatement  pst = conn.prepareStatement("");//准备执行语句
            String sql="truncate user";
                // 执行SQL
                pst.addBatch(sql);
                // 执行操作
                pst.executeBatch();
                // 提交事务
                conn.commit();
            // 关闭连接
            pst.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e+"====");
        }
        // 结束时间
        Long end = new Date().getTime();
        // 耗时
        System.out.println("删除70万数据花费时间 : " + (end - begin) + " s"+"哈哈哈");
	}

}
