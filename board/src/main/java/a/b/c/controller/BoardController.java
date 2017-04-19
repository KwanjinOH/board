package a.b.c.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import a.b.c.service.Board;
import a.b.c.service.BoardDao;

@Controller
public class BoardController {
	
	@Autowired//주입하다
	private BoardDao dao; // new BoardDao(); 삭제 하기 위해선 BoardDao.java 에 @Re~, 그다음 @Auto~ : 객체를 가지고 있으면 알아서 만들어줘라
	//글 수정 폼 요청
	@RequestMapping(value ="/boardModify",method=RequestMethod.GET)
	public String boardModify(Model model,@RequestParam(value="boardNo",required=true) int boardNo){
		Board board = dao.getBoard(boardNo);
		model.addAttribute("board", board);
		return "boardModify";
	}
	
	//글 수정 요청
	@RequestMapping(value="/boardModify",method=RequestMethod.POST)
	public String boardModify(Board board){
		dao.updateBoard(board);
		return "redirect:/boardView?boardNo="+board.getBoardNo();
	}
	
	//글 삭제 폼 요청 (비밀번호 입력화면)
	@RequestMapping(value="/boardRemove", method = RequestMethod.GET)
	public String boardRemove( @RequestParam(value="boardNo") int boardNo){
					
		return "boardRemove";
		}
		
		//글 삭제 요청
	@RequestMapping(value="/boardRemove", method = RequestMethod.POST)
	public String boardRemove(@RequestParam(value="boardNo") int boardNo, @RequestParam(value="boardPw") String boardPw){
		dao.deleteBoard(boardNo, boardPw);
		
		return "redirect:/boardList";
		}
	
	
	// 글 상세 내용 요청
	@RequestMapping(value= "/boardView", method = RequestMethod.GET)
	public String boardView(Model model, @RequestParam(value="boardNo",required=true) int boardNo){
		Board board = dao.getBoard(boardNo);
		model.addAttribute("board", board);
		return "/boardView";
		
	}
	
	// 리스트 요청
	@RequestMapping(value={"/","/boardList"}, method = RequestMethod.GET)
	// 여러개로 요청시 배열 방식으로 할수 있다. 하지만 동일한 이름으로 요청시 오류가 발생한다. ex) "/" 와 다른곳에서 "/" 맵핑 요청시 오류
	public String boardList(Model model, @RequestParam(value="currentPage",required=false, defaultValue="1") int currentPage){ // 자동적으로 int로 형 변환
		// currrentPage가 펄스면 디폴트 값으로 1을 준다.
		int boardCount = dao.getTotalcount();
		int pagePerRow = 10;
		int lastPage = (int)(Math.ceil(boardCount / pagePerRow));
		
		List<Board> list = dao.getboardList(currentPage, pagePerRow);
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("boardCount", boardCount);
		model.addAttribute("list", list);
		
		return "/boardList";
	}
	
	
	@RequestMapping(value="/board/boardAdd", method = RequestMethod.GET)
	public String boardAdd(){
		System.out.println("boardAdd 요청");
		return "boardAdd";
		
	}
	
	@RequestMapping(value="/board/boardAdd", method = RequestMethod.POST)
	public String boardAdd(Board board){
		System.out.println("POST 요청");
		System.out.println(board);
		dao.insertBoard(board);
		return "redirect:/boardList"; //포워딩 하지 말고 리다이렉트 시켜라
		
	}

}
