package customer.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Soundbank;

import customer.db.DBCon;
import customer.vo.Notice;

public class NoticeDao {
	
	public void write(Notice n) throws Exception {
		//insert  작업
		String sql="insert into notices values("
			+"(select max(to_number(seq))+1 from notices)"
			+",?,?,?,sysdate,0,?)";
		//dbcon
		Connection con=DBCon.getConnection();
		//실행
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, n.getTitle());
		pstmt.setString(2, n.getWriter());
		pstmt.setString(3, n.getContent());
		pstmt.setString(4, n.getFilesrc());
		
		pstmt.executeUpdate();
		
		
		pstmt.close();
		con.close();
	}
	
	public void update(Notice notice) throws Exception {
		
		String sql="update notices"
				+" set title=?,content=? where seq=?";
		Connection con=DBCon.getConnection();
		//실행
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, notice.getTitle());
		pstmt.setString(2, notice.getContent());
		pstmt.setString(3, notice.getSeq());
		
		pstmt.executeUpdate();
		
	}
	public void update2(String seq,String tit,String content) throws Exception {
		
		String sql="update notices"
				+" set title=?,content=? where seq=?";
		Connection con=DBCon.getConnection();
		//실행
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, tit);
		pstmt.setString(2, content);
		pstmt.setString(3, seq);
		
		pstmt.executeUpdate();
		
	}
	public int delete(String seq) throws Exception {
		String sql="delete from notices where seq="+seq;
		//dbcon
		Connection con=DBCon.getConnection();
		//실행
		PreparedStatement pstmt=con.prepareStatement(sql);
		int del=pstmt.executeUpdate();
		
		return del;	
	}
	
public Notice getNotice(String seq) throws Exception {
//		edit
		String sql="select seq,title,writer,content,"
				+ "regdate,hit from notices where seq="+seq;
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
		
		rs.close();
		stmt.close();
		con.close();
		return n;	
	}
	
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
	
	public List<Notice> noticeSelAll(String field,String query) throws Exception {
//		String sql="select seq,title,writer,content,"
//		+"regdate,hit from notices order by to_number(seq) desc";
//		
		String sql="select seq,title,writer,content,regdate,"
				+"hit from notices where "+field+" like ?"
				+" order by to_number(seq) desc";
 
		//dbcon
		Connection con=DBCon.getConnection();
		//실행
//		Statement stmt=con.createStatement();
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, "%"+query+"%");
		ResultSet rs=pstmt.executeQuery();

		List<Notice> list=new ArrayList<Notice>();
		while (rs.next()) {
			Notice n=new Notice();
			n.setSeq(rs.getString("seq"));
			n.setTitle(rs.getString("title"));
			n.setWriter(rs.getString("writer"));
			n.setContent(rs.getString("content"));
			n.setRegdate(rs.getDate("regdate"));
			n.setHit(rs.getInt("hit"));
			list.add(n);
		}
		return list;
	}
	

}
