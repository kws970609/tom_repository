package com.study.model.store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.study.commons.db.DBManager;

public class IconsDAO {
	DBManager manager = DBManager.getInstance();
	
	//아이콘 가져오기
	public List selectAll() {
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		List list = new ArrayList();
		
		con=manager.getConnection();
		String sql="select * from icons";
		
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Icons icons = new Icons();
				icons.setIcons_id(rs.getInt("icons_id"));
				icons.setTitle(rs.getString("title"));
				icons.setFilename(rs.getString("filename"));
				
				list.add(icons);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt);
		}
		return list;
	}
}





























