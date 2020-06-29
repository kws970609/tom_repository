package com.study.model.reboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.study.commons.db.DBManager;
import com.study.commons.db.PoolManager;

public class ReBoardDAO {
	PoolManager manager = PoolManager.getInstance();
	
	//모두조회
	public List selectAll() {
		List<ReBoard> list = new ArrayList();
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from reboard ";
		sql+=" order by team desc, rank asc";
		
		con=manager.getConnection();
		
		try {
			pstmt= con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ReBoard reBoard = new ReBoard();
				reBoard.setReboard_id(rs.getInt("reboard_id"));
				reBoard.setTitle(rs.getString("title"));
				reBoard.setWriter(rs.getString("writer"));
				reBoard.setContent(rs.getString("content"));
				reBoard.setRegdate(rs.getString("regdate"));
				reBoard.setHit(rs.getInt("hit"));
				reBoard.setTeam(rs.getInt("team"));
				reBoard.setRank(rs.getInt("rank"));
				reBoard.setDepth(rs.getInt("depth"));
				
				list.add(reBoard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt, rs);
		}
		
		return list;
	}
	
	// 한건 조회
	public ReBoard select(int reboard_id) {
		ReBoard reBoard=null;
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from reboard where reboard_id=?";
		
		con=manager.getConnection();
		
		try {
			pstmt= con.prepareStatement(sql);
			pstmt.setInt(1, reboard_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				reBoard = new ReBoard();
				reBoard.setReboard_id(rs.getInt("reboard_id"));
				reBoard.setTitle(rs.getString("title"));
				reBoard.setWriter(rs.getString("writer"));
				reBoard.setContent(rs.getString("content"));
				reBoard.setRegdate(rs.getString("regdate"));
				reBoard.setHit(rs.getInt("hit"));
				reBoard.setTeam(rs.getInt("team"));
				reBoard.setRank(rs.getInt("rank"));
				reBoard.setDepth(rs.getInt("depth"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt, rs);
		}
		
		return reBoard;
	}
	
	//생성
	public int insert(ReBoard reBoard) {
		String title = reBoard.getTitle();
		String writer = reBoard.getWriter();
		String content = reBoard.getContent();
		Connection con = null;
		PreparedStatement pstmt=null;
		int result=0;
		String sql = "insert into reboard(reboard_id, title, writer, content, team)";
		sql+=" values(seq_reboard.nextval,?,?,?, seq_reboard.nextval)";
		con= manager.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, content);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt);
		}
		return result;
	}
	
	//삭제
	public int delete(ReBoard reBoard) {
		int result =0;
		Connection con =null;
		PreparedStatement pstmt = null;
		int reboard_id = reBoard.getReboard_id();
		String sql ="delete from reboard where reboard_id=?";
		
		con=manager.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, reboard_id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt);
		}
		return result;
	}
	
	//수정
	public int update(ReBoard reBoard) {
		String title = reBoard.getTitle();
		String writer = reBoard.getWriter();
		String content = reBoard.getContent();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql="update reboard set title=?, writer=?, content=?";
		int result=0;
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, content);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt);
		}
		return result;
	}

	//아래의 쿼리는 반드시 일어나는것이 아니다
	public int updateRank(ReBoard reBoard) {
		int result=0;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql ="update reboard set rank= rank+1";
		sql+= " where team =? and rank> ?";
		
		con=manager.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, reBoard.getTeam());
			pstmt.setInt(2, reBoard.getRank());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			manager.freeConnection(con, pstmt);
		}
		
		return result;
	}
	
	public int reply(ReBoard reBoard) {
		int result=0;
		Connection con =null;
		PreparedStatement pstmt = null;
		String sql ="insert into reboard(reboard_id, title, writer, content,team,rank,depth)";
		sql+=" values(seq_reboard.nextval,?,?,?,?,?,?)";
		
		con=manager.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, reBoard.getTitle());
			pstmt.setString(2, reBoard.getWriter());
			pstmt.setString(3, reBoard.getContent());
			pstmt.setInt(4, reBoard.getTeam());
			pstmt.setInt(5, reBoard.getRank()+1);
			pstmt.setInt(6, reBoard.getDepth()+1);
			
			result= pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt);
		}
		//team : 내본글 team
		//rank : 내본글 rank+1
		//depth :내본글 depth+1
		return result;
	}
}




















