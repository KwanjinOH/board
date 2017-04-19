package a.b.c.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;





@Repository
public class BoardDao {

	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
//// /Ŀ�ؼ� Ǯ�� �����ϴ� �ʿ� ����	
//	public Connection getConnection() throws ClassNotFoundException, Exception{
//		System.out.println("00 _ BrardDao ������ �޼��� ���� ���ؼ� ����̹� �ε� ����");
//		String dbUrl = "jdbc:mysql://127.0.0.1:3306/board?useUnicode=true&characterEncoding=euckr";
//	    String dbUser = "board23";
//	    String dbPw = "board23pw";
//		Class.forName("com.mysql.jdbc.Driver");
//	    System.out.println("DB���� Ȯ��");
//		return connection = DriverManager.getConnection(dbUrl, dbUser, dbPw);
//		
//	}
	
	public int updateBoard(Board board) {
		
		return sqlSessionTemplate.update("a.b.c.service.BoardMapper.updateBoard", board);		
	}
//        Connection connection = null;
//        PreparedStatement statement = null;
//        int row = 0;
//        try {
//            connection = this.getConnection();
//            String sql = "UPDATE board SET board_title=?, board_content=? WHERE board_no=? AND board_pw=?";
//            statement = connection.prepareStatement(sql);
//            statement.setString(1, board.getBoardTitle());
//            statement.setString(2, board.getBoardContent());
//            statement.setInt(3, board.getBoardNo());
//            statement.setString(4, board.getBoardPw());
//            row = statement.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.print("���� �߻�");
//        } finally {
//            try {statement.close();} catch(Exception e){}
//            try {connection.close();} catch(Exception e){}
//        } 
//        return row;
//    }

	
	// �۹�ȣ�� ���н����带 �Է¹޾� �Ѱ��� �Խñ� ����
    public int deleteBoard(int boardNo, String boardPw) {
    	Board board = new Board();
    	board.setBoardNo(boardNo);
    	board.setBoardPw(boardPw);
    	return sqlSessionTemplate.delete("a.b.c.service.BoardMapper.deleteBoard", board);
    }
//        Connection connection = null;
//        PreparedStatement statement = null;
//        int row = 0;
//        try {
//            connection = this.getConnection();
//            String sql = "DELETE FROM board WHERE board_no=? AND board_pw=?";
//            statement = connection.prepareStatement(sql);
//            statement.setInt(1, boardNo);
//            statement.setString(2, boardPw);
//            row = statement.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.print("���� �߻�");
//        } finally {
//            try {statement.close();} catch(Exception e){}
//            try {connection.close();} catch(Exception e){}
//        } 
//        return row;
//    }

	
	 // �Ѱ��� �Խñ� ���뺸��
    public Board getBoard(int boardNo) {
    	
    	return sqlSessionTemplate.selectOne("a.b.c.service.BoardMapper.getBoard", boardNo);
    }
//        Board board = null;
//        Connection connection = null;
//        PreparedStatement statement = null;
//        ResultSet rs = null;
//        try {
//            connection = this.getConnection();
//            String sql = "SELECT board_title, board_content, board_user, board_date FROM board WHERE board_no=?";
//            statement = connection.prepareStatement(sql);
//            statement.setInt(1, boardNo);
//            rs = statement.executeQuery();
//            if(rs.next()) {
//                board = new Board();
//                board.setBoardNo(boardNo);
//                board.setBoardTitle(rs.getString("board_title"));
//                board.setBoardContent(rs.getString("board_content"));
//                board.setBoardUser(rs.getString("board_user"));
//                board.setBoardDate(rs.getString("board_date"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.print("���� �߻�");
//        } finally {
//            try {statement.close();} catch(Exception e){}
//            try {statement.close();} catch(Exception e){}
//            try {connection.close();} catch(Exception e){}
//        }   
//        return board;
//    }
 




	
	
	//insert
	public int insertBoard(Board board) {
		
		return sqlSessionTemplate.insert("a.b.c.service.BoardMapper.insertBoard", board);
	}
		// boardMapper.xml ����
//	    	try{
//	    	connection = this.getConnection();
//	    	String sql = "INSERT INTO board(board_pw, board_title, board_content, board_user, board_date) values(?,?,?,?,now())";
//	        statement = connection.prepareStatement(sql);
//	        statement.setString(1,board.getBoardPw());
//	        statement.setString(2,board.getBoardTitle());
//	        statement.setString(3,board.getBoardContent());
//	        statement.setString(4,board.getBoardUser());
//	        
//	        statement.executeUpdate(); // insert ������ ����
//	    	System.out.println("01_ insertBoard() ����");    
//	    	} catch(Exception e){
//	    		e.printStackTrace();
//	    		System.out.println("�Է� ���� �߻�");
//	    	} finally{
//	    		 try {statement.close();} catch(Exception e){}
//	    	     try {connection.close();} catch(Exception e){}
//	    	}   
	    
	
	// List Select ��ü �� ī��Ʈ
	public int getTotalcount() {
		return sqlSessionTemplate.selectOne("a.b.c.service.BoardMapper.getTotalcount");
	}
//		int total = 0;
//		try{
//			connection = this.getConnection();
//			String Sql = "SELECT COUNT(*) FROM board";
//			statement = connection.prepareStatement(Sql);
//			rs = statement.executeQuery();
//			
//			if(rs.next()){
//				total = rs.getInt(1);
//			}
//		
//		} catch(Exception e){
//			e.printStackTrace();
//			System.out.println("��ü �� ī��Ʈ ���� �߻�");
//		} finally{
//			try {statement.close();} catch(Exception e){}
//   	     	try {connection.close();} catch(Exception e){}
//		}
		
	
	// List Select 
	public List<Board> getboardList(int currentPage, int pagePerRow){
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("currentPage",(currentPage-1)*pagePerRow);
		map.put("pagePerRow",pagePerRow); 
		return sqlSessionTemplate.selectList("a.b.c.service.BoardMapper.getBoardList", map);
	}
//		ArrayList<Board> list = new ArrayList<Board>();
//		try{
//			connection = this.getConnection();
//			System.out.println(currentPage+"<<< currentPage");
//			System.out.println(pagePerRow+"<<< pagePerRow");
//			
//		    String listSql = "SELECT board_no, board_title, board_user, board_date FROM board ORDER BY board_date DESC LIMIT ?, ?";
//		    //	?����� ?������ ����Ʈ�϶�
//		    statement = connection.prepareStatement(listSql);
//		    statement.setInt(1, (currentPage-1)*pagePerRow); //(currentPage �� 1�̰� 1-1�� 0)*10 �� 0
//		    /* �������� 1��° ������ 0�� ���� 0��°  ���� 10�������� ���� select���ش� (pagePerRow �� ��䰪 10��)  */
//		    statement.setInt(2, pagePerRow); // pagePerRow �� 10
//		    rs = statement.executeQuery(); /* ����Ÿ�� */
//		    /* executeQuery() ����� listResultSet ����Ÿ������ �����Ѵ�. */
//			while(rs.next()){
//				Board board = new Board();
//				board.setBoardNo(rs.getInt("board_no"));
//				board.setBoardTitle(rs.getString("board_title"));
//				board.setBoardUser(rs.getString("board_user"));
//				board.setBoardDate(rs.getString("board_date"));
//				list.add(board);
//				
//			}
//		} catch(Exception e){
//			e.printStackTrace();
//			System.out.println("List ���� ���� �߻�");
//		} finally{
//			try {statement.close();} catch(Exception e){}
//   	     	try {connection.close();} catch(Exception e){}
//		}
//		
	
	
	
	
	}
