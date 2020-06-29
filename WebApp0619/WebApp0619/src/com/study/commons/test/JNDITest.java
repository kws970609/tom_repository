package com.study.commons.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/*요청이 있을때마다 오라클등의 DBMS에 접속을 시도하여 Connection을 얻고, 이렇게 힘들게 얻어진
Connection을 매 요청마다 얻고 끊고 하는 작업은 동시접속자가 많을수록 부하가 걸린다.
 이러한 문제를 해결하기위해서는 접속자가 없더라도 Connection을 확보해놓자
데이터베이스 관련 요청이있을때는 새로 접속하는게 아니라, 이미 접속이 확보해놓은 Connection 객체를 Pool로 부터 얻고
 작업후 다시 업무가 끝난후에  Pool로 돌려놓자!! -> Connection Pooling이라 한다!!
커넥션 풀을 구현하려면 원래 개발자가 알고리즘을 작성해야 하지만, 이미 톰캣등의 컨테이너 서버나 WAS등에서는 시스템 차원에서
지원을 하고있다..
JNDI: Java Naming Directory Interface
MS의 Active Directory 와 비슷한 기술
클래스내에 자원에대한 정보를 두지 말고, 외부 설정파일에 정보를 두어, 유지보수성을 높이고자하는 기술을 의미..
예) 데이터베이스와 관련된 접속 정보를 자바 클래스내에 두면??
*/
public class JNDITest extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			//xml을 검색 할 수 있는 객체 생성
			InitialContext ct = new InitialContext();
			
			//검색
			DataSource ds= (DataSource)ct.lookup("java:comp/env/jdbc/oracle");
			
			//ds(커넥션 풀)로 부터 Connection 한개 대여하기!!
			Connection con = ds.getConnection();
			String sql ="select * from animal";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			System.out.println(rs.getString("name"));
			
			con.close();//다시 풀로 돌려보내기
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}































