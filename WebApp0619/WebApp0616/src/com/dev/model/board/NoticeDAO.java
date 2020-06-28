package com.dev.model.board;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dev.model.notice.Notice;

/*
 DAO란? 오직 데이터베이스와 관련된 C(insert)R(select)U(update)D(delete) 즉, DML을 수행하는 객체를 가리켜
 어플리케이션 설계분야 에서는 DAO라 부른다
 이 객체를 중립적으로 정의해 놓으면, javaSE, javaEE 모두 사용 가능하게 되서, 
 재 사용성이 높아지게 된다..(자바분야에서는 이 객체를 모르면 간첩)
 */
public class NoticeDAO {
	
	// 게시물 모두 가져오기
	public List selectAll() {
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String id = "c##java";
		String pw = "android";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<Notice> list = new ArrayList<Notice>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, id, pw);
			String sql = "select * from notice order by notice_id desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// rs의 모든 데이터를 list에 담자!!

			while (rs.next()) {
				Notice notice = new Notice();
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setTitle(rs.getString("title"));
				notice.setWriter(rs.getString("writer"));
				notice.setContent(rs.getString("content"));
				notice.setRegdate(rs.getString("regdate"));
				notice.setHit(rs.getInt("hit"));

				list.add(notice);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
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
		return list;
	}

	// 한건 등록(글 등록)
	// 아래의 메서드는 웹이건, 응용이건 모두 사용해야하므로,
	// 특정 플랫폼에 국한되서는 안되며, 기술에 중립적이어야 한다!!
	public int insert(Notice notice) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String id = "c##java";
		String pw = "android";

		String sql = "insert into notice(notice_id, title,writer,content)";
		sql += " values(seq_notice.nextval,?,?,?)";

		int result = 0; // 메서드 호출자 로 하여금, 이 등록의 성패여부를 알수 있도록 반환하기 위함

		// 드라이버 로드, 접속, 쿼리, 해제
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, id, pw);
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, notice.getTitle());
			pstmt.setString(2, notice.getWriter());
			pstmt.setString(3, notice.getContent());

			result = pstmt.executeUpdate();// 쿼리 수행

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
		return result;
	}

	// 한건 가져오기(상세보기)
	public Notice select(int notice_id) {
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//ResultSet을반환 해도 되지만, finally 에서 닫힐 예정이기때문에 사용불가, 또한 객체지향적인
		//객체로 레코드를 담는것이 더 직관적이다!!(DTO,VO적극 활용하자!!)
		Notice notice =null;
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String id = "c##java";
		String pw = "android";
		
		String sql ="select * from notice where notice_id=?";
		
		//드라이버 로드,접속, 쿼리, 닫기
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, id, pw);
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, notice_id);	
			rs=pstmt.executeQuery(); //쿼리 실행
			//rs에 레코드가 한건이라도 있다면 그때 VO를 new 하자
			if(rs.next()) {
				notice = new Notice();
				//rs의 레코드를 vo에 옮겨심자..rs가 죽기전에
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setTitle(rs.getString("title"));
				notice.setWriter(rs.getString("writer"));
				notice.setContent(rs.getString("content"));
				notice.setRegdate(rs.getString("regdate"));
				notice.setHit(rs.getInt("hit"));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
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
		return notice;
		
	}

	// 한건 수정

	@SuppressWarnings("finally")
	public int edit(Notice notice) {
		String title= notice.getTitle();
		String writer= notice.getWriter();
		String content= notice.getContent();
		int notice_id= notice.getNotice_id();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String id = "c##java";
		String pw = "android";
		
		String sql="update notice set title=?, writer=?, content=?";
		
		int result=0;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con= DriverManager.getConnection(url, id, pw);
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1,title);
			pstmt.setString(2,writer);
			pstmt.setString(3,content);
			pstmt.setInt(4,notice_id); //숫자 바인드 변수
			
			result = pstmt.executeUpdate();
			
			} catch (SQLException e) {
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			}finally {
				if(pstmt!=null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(con!=null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				return result;
			}
		} 
		
	// 한건 삭제
	public int delete(int notice_id) {

		Connection con=null;
		PreparedStatement pstmt=null;
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String id = "c##java";
		String pw = "android";
		
		int result =0;
		
		String sql ="delete from notice where notice_id=?";
		
		try {
			con = DriverManager.getConnection(url,id, pw);
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, notice_id);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}



















