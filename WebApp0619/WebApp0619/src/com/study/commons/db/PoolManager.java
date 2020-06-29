package com.study.commons.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//JNDI로 커넥션풀을 이용하려면 자원을 검색하는 등의 코드가 번거로움으로
//JNDI를 좀더 쉽고, 재사용성을 높이기위한 클래스를 정의하자!!
public class PoolManager {
	private static PoolManager instance;
	InitialContext context; //검색객체!!
	DataSource ds;
	
	private PoolManager() {
		try {
			context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	//커넥션 객체 풀에서 꺼내기!!(대여)
	public static PoolManager getInstance() {
		if(instance==null) {
			instance = new PoolManager();
		}
		return instance;
	}
	
	//커넥션 객체를 풀에서 꺼내기(대여)
	public Connection getConnection() {
		Connection con=null;
		try {
			con=ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
	
	
	
	//커넥션 객체 풀로 돌려보내기(반납)
	public void freeConnection(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void freeConnection(Connection con, PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void freeConnection(Connection con, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}





































