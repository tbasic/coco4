package customer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import customer.db.DBCon;
import customer.vo.Member;
import customer.vo.Notice;

public class MemberDao {
	
	public Notice getNotice(String seq,String hit) throws Exception {
//		detail
//		update notices set hit=hit+1 where seq='1'
		int hnum=Integer.parseInt(hit);
		hitupdate(seq,hnum);
		
		String sql="select seq,title,writer,content,"
				+ "regdate,hit,filesrc from notices where seq="+seq;
		//dbcon
		Connection con=DBCon.getConnection();
		//실행
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		rs.next();
		
		Notice n=new Notice();
		n.setSeq(rs.getString("seq"));
		n.setWriter(rs.getString("writer"));
		n.setTitle(rs.getString("title"));
		n.setContent(rs.getString("content"));
		n.setRegdate(rs.getDate("regdate"));
		n.setHit(rs.getInt("hit"));
		n.setFilesrc(rs.getString("filesrc"));
		
		
		rs.close();
		stmt.close();
		con.close();
		return n;	
	}
	public void hitupdate(String seq,int hnum) throws Exception {
		System.out.println("hit upppppp");
		//dbcon
		Connection con=DBCon.getConnection();
		String sql="update notices set hit=? where seq=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, hnum+1);
		pstmt.setString(2, seq);
		pstmt.executeUpdate();
	}
	public Member getMember(String uid) throws Exception{
		//회원들중 uid에 해당하는 데이터 조회
		String sql="select id,pwd,name,gender,birth,is_lunar,"
				+ "cphone,email,habit,regdate from member"
				+ " where id=?";
		Connection con=DBCon.getConnection();
		
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, uid);
		
		ResultSet rs=pstmt.executeQuery();
		Member m=null;
		if(rs.next()) {
			m=new Member();
			m.setId(rs.getString("id"));
			m.setPwd(rs.getString("pwd"));
			System.out.println("id "+m.getId());
		}		
		return m;	
	}

}
