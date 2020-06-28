package com.study.model.store2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.study.commons.db.DBManager;

public class StoreDAO {
	DBManager manager = DBManager.getInstance();
	
	//모두 가져오기 Read
	public List selectAll() {
		List<Store> list = new ArrayList();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql ="select * from store s, icons i";
		sql+=" where s.icons_id = i.icons_id";
		
		con = manager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				Icons icons = new Icons();
				icons.setIcons_id(rs.getInt("icons_id"));
				icons.setTitle(rs.getString("title"));
				icons.setFilename(rs.getString("filename"));
				
				Store store = new Store();
				store.setStore_id(rs.getInt("store_id"));
				store.setName(rs.getString("name"));
				store.setAddr(rs.getString("addr"));
				store.setLati(rs.getDouble("lati"));
				store.setLongi(rs.getDouble("longi"));
				store.setMemo(rs.getString("memo"));
				store.setIcons(icons); //결합
				
				list.add(store);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt, rs);
		}
		return list;
	}
	
	//한건만 가져오기 Read
	public Store select(int store_id) {
		Store store=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		con = manager.getConnection();
		String sql ="select * from store s, icons i";
		sql+=" where s.icons_id = i.icons_id and s.store_id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, store_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Icons icons = new Icons();
				icons.setIcons_id(rs.getInt("icons_id"));
				icons.setTitle(rs.getString("title"));
				icons.setFilename(rs.getString("filename"));
				
				store = new Store();
				store.setStore_id(rs.getInt("store_id"));
				store.setName(rs.getString("name"));
				store.setAddr(rs.getString("addr"));
				store.setLati(rs.getDouble("lati"));
				store.setLongi(rs.getDouble("longi"));
				store.setMemo(rs.getString("memo"));
				store.setIcons(icons);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt, rs);
		}
		return store;
	}
	
	//등록하기 Create
	public int insert(Store store) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql ="insert into store(store_id,name,addr,lati,longi,icons_id,memo)";
		sql+=" values(seq_store.nextval,?,?,?,?,?,?)";
		int result=0;
		con = manager.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, store.getName());
			pstmt.setString(2, store.getAddr());
			pstmt.setDouble(3, store.getLati());
			pstmt.setDouble(4, store.getLongi());
			pstmt.setInt(5, store.getIcons().getIcons_id());
			pstmt.setString(6, store.getMemo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt);
		}
		return result;
	}
	
	//수정하기 Update
	public int update(Store store) {
		String name = store.getName();
		String addr = store.getAddr();
		double lati = store.getLati();
		double longi = store.getLongi();
		int icons_id = store.getIcons().getIcons_id();
		String memo = store.getMemo();
		int store_id = store.getStore_id();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql ="update store set name=?, addr=?, lati=?, longi=?, icons_id=?, memo=? where store_id";
		int result=0;
		
		try {
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, addr);
			pstmt.setDouble(3, lati);
			pstmt.setDouble(4, longi);
			pstmt.setInt(5, icons_id);
			pstmt.setString(6, memo);
			pstmt.setInt(7, store_id);
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt);
		}
		return result;
	}
	
	//삭제하기 Delete
	public int Delete(Store store) {
		int result=0;
		Connection con =null;
		PreparedStatement pstmt = null;
		int store_id = store.getStore_id();
		String sql= "delete from store where store_id=?";
		
		con = manager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, store_id);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt);
		}
		return result;
	}
}



















