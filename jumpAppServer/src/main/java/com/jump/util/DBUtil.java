package com.jump.util;

import com.jump.util.DBConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author Saraph1nes
 * @Date 2020/5/21 14:21
 * @Version 1.0
 */

public class DBUtil {

    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public Connection getConnection(){
        String driver = DBConfig.getValue("driver");
        String url = DBConfig.getValue("url");
        String user = DBConfig.getValue("user");
        String pwd = DBConfig.getValue("pwd");
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,pwd);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public void closeAll() {
        if(rs != null) {
            try {
                rs.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
        if(pstmt != null) {
            try {
                pstmt.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
        if(con != null) {
            try {
                con.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //查询
    public ResultSet executeQuery(String preparedSql, Object[] param) {
        try {
            pstmt = con.prepareStatement(preparedSql);
            if(param != null) {
                for(int i=0;i<param.length;i++) {
                    pstmt.setObject(i+1, param[i]);
                }
            }
            rs = pstmt.executeQuery();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    //增删改
    public int executeUpdate(String preparedSql,Object[] param) {
        int num = 0;
        try {
            pstmt = con.prepareStatement(preparedSql);
            if(param != null) {
                for(int i=0;i<param.length;i++) {
                    pstmt.setObject(i+1, param[i]);
                }
            }
            num = pstmt.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return num;
    }
}
