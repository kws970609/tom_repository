package com.study.model.news;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.Manager;

import com.study.commons.db.DBManager;

//오직 Comments 테이블에대한 CRUD만 담당!!
public class CommentsDAO {
	DBManager manager =DBManager.getInstance();
	
	public int insert(Comments comments) {
		int result=0;
		Connection con =null;
		PreparedStatement pstmt = null;
		
		String sql="insert into comments(comments_id, news_id, msg, cwriter)";
		sql+=" values(seq_comments.nextval,?,?,?)";
		
		con=manager.getConnection();
		try {
			pstmt= con.prepareStatement(sql);
			pstmt.setInt(1, comments.getNews().getNews_id());
			pstmt.setString(2, comments.getMsg());
			pstmt.setString(3, comments.getCwriter());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt);
		}
		return result;
	}
	
	//모두가져오기
	public List selectAll(int news_id) {
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Comments> list = new ArrayList<Comments>();
		
		con = manager.getConnection();
		String sql="select * from comments ";
		sql+= "where news_id=? order by comments_id desc";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, news_id);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Comments comments = new Comments();
				News news = new News();
				news.setNews_id(rs.getInt("news_id"));
				comments.setNews(news);
				comments.setComments_id(rs.getInt("comments_id"));
				comments.setMsg(rs.getString("msg"));
				comments.setCwriter(rs.getString("cwriter"));
				comments.setCregdate(rs.getString("cregdate"));
				
				list.add(comments);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt, rs);
		}
		return list;
	}
}



















