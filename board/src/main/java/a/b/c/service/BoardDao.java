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
//// /커넥션 풀로 실행하니 필요 없음	
//	public Connection getConnection() throws ClassNotFoundException, Exception{
//		System.out.println("00 _ BrardDao 생성자 메서드 실행 컨넥션 드라이버 로딩 추출");
//		String dbUrl = "jdbc:mysql://127.0.0.1:3306/board?useUnicode=true&characterEncoding=euckr";
//	    String dbUser = "board23";
//	    String dbPw = "board23pw";
//		Class.forName("com.mysql.jdbc.Driver");
//	    System.out.println("DB연결 확인");
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
//            System.out.print("예외 발생");
//        } finally {
//            try {statement.close();} catch(Exception e){}
//            try {connection.close();} catch(Exception e){}
//        } 
//        return row;
//    }

	
	// 글번호와 글패스워드를 입력받아 한개의 게시글 삭제
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
//            System.out.print("예외 발생");
//        } finally {
//            try {statement.close();} catch(Exception e){}
//            try {connection.close();} catch(Exception e){}
//        } 
//        return row;
//    }

	
	 // 한개의 게시글 내용보기
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
//            System.out.print("예외 발생");
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
		// boardMapper.xml 설정
//	    	try{
//	    	connection = this.getConnection();
//	    	String sql = "INSERT INTO board(board_pw, board_title, board_content, board_user, board_date) values(?,?,?,?,now())";
//	        statement = connection.prepareStatement(sql);
//	        statement.setString(1,board.getBoardPw());
//	        statement.setString(2,board.getBoardTitle());
//	        statement.setString(3,board.getBoardContent());
//	        statement.setString(4,board.getBoardUser());
//	        
//	        statement.executeUpdate(); // insert 쿼리를 실행
//	    	System.out.println("01_ insertBoard() 실행");    
//	    	} catch(Exception e){
//	    		e.printStackTrace();
//	    		System.out.println("입력 예외 발생");
//	    	} finally{
//	    		 try {statement.close();} catch(Exception e){}
//	    	     try {connection.close();} catch(Exception e){}
//	    	}   
	    
	
	// List Select 전체 글 카운트
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
//			System.out.println("전체 글 카운트 예외 발생");
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
//		    //	?행부터 ?개까지 셀렉트하라
//		    statement = connection.prepareStatement(listSql);
//		    statement.setInt(1, (currentPage-1)*pagePerRow); //(currentPage 는 1이고 1-1은 0)*10 은 0
//		    /* 쿼리문에 1번째 값으로 0이 들어가니 0번째  부터 10개까지의 행을 select해준다 (pagePerRow 에 담긴값 10개)  */
//		    statement.setInt(2, pagePerRow); // pagePerRow 는 10
//		    rs = statement.executeQuery(); /* 참조타입 */
//		    /* executeQuery() 결과를 listResultSet 참조타입으로 접근한다. */
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
//			System.out.println("List 추출 예외 발생");
//		} finally{
//			try {statement.close();} catch(Exception e){}
//   	     	try {connection.close();} catch(Exception e){}
//		}
//		
	
	
	
	
	}
