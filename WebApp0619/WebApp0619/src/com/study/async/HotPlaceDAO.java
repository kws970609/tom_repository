package com.study.async;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.study.commons.db.DBManager;

public class HotPlaceDAO {
	
	DBManager manager = DBManager.getInstance();
	
	public int insert(HotPlace hotplace) {
		Connection con =null;
		PreparedStatement pstmt = null;
		int result =0;
		String sql = "insert into hotplace(hotplace_id, name, tel, addr, food)";
		sql+= " values(seq_hotplace.nextval,?,?,?,?)";
		
		con= manager.getConnection();
		
		try {
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, hotplace.getName());
			pstmt.setString(2, hotplace.getTel());
			pstmt.setString(3, hotplace.getAddr());
			pstmt.setString(4, hotplace.getFood());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt);
		}
		return result;
	}
	
	public List selectAll() {
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List list = new ArrayList();
		
		con = manager.getConnection();
		String sql ="select * from hotplace order by hotplace_id desc";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				HotPlace hotplace = new HotPlace();
				hotplace.setHotplace_id(rs.getInt("hotplace_id"));
				hotplace.setName(rs.getString("name"));
				hotplace.setTel(rs.getString("tel"));
				hotplace.setAddr(rs.getString("addr"));
				hotplace.setFood(rs.getString("food"));
				
				list.add(hotplace);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt, rs);
		}
		return list;
	}
	
	public HotPlace select(int hotPlace_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HotPlace hotPlace = null;
		
		con = manager.getConnection();
		String sql ="select * from hotplace where hotplace_id=?";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, hotPlace_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				hotPlace = new HotPlace();
				hotPlace.setHotplace_id(rs.getInt("hotplace_id"));
				hotPlace.setName(rs.getString("name"));
				hotPlace.setTel(rs.getString("tel"));
				hotPlace.setAddr(rs.getString("addr"));
				hotPlace.setFood(rs.getString("food"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt, rs);
		}
		return hotPlace;
	}
	
	public int edit(HotPlace hotPlace) {
		int result=0;
		String name = hotPlace.getName();
		String tel = hotPlace.getTel();
		String addr = hotPlace.getAddr();
		String food = hotPlace.getFood();
		int hotPlace_id = hotPlace.getHotplace_id();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql ="update hotplace set name=?, tel=?, addr=?, food=?";
		sql+= " where hotplace_id=?";
		
		con=manager.getConnection();
		
		try {
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, name);
			pstmt.setString(2, tel);
			pstmt.setString(3, addr);
			pstmt.setString(4, addr);
			pstmt.setInt(5, hotPlace_id);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt);
		}
		return result;
	}
	
	public int delete(HotPlace hotPlace) {
		
		int result=0;
		Connection con =null;
		PreparedStatement pstmt = null;
		int hotPlace_id = hotPlace.getHotplace_id();

		String sql ="delete from hotplace where hotplace_id=?";
		
		con =manager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, hotPlace_id);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt);
		}
		return result;
	}
}




















