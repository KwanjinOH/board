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
	
	@Autowired//�����ϴ�
	private BoardDao dao; // new BoardDao(); ���� �ϱ� ���ؼ� BoardDao.java �� @Re~, �״��� @Auto~ : ��ü�� ������ ������ �˾Ƽ� ��������
	//�� ���� �� ��û
	@RequestMapping(value ="/boardModify",method=RequestMethod.GET)
	public String boardModify(Model model,@RequestParam(value="boardNo",required=true) int boardNo){
		Board board = dao.getBoard(boardNo);
		model.addAttribute("board", board);
		return "boardModify";
	}
	
	//�� ���� ��û
	@RequestMapping(value="/boardModify",method=RequestMethod.POST)
	public String boardModify(Board board){
		dao.updateBoard(board);
		return "redirect:/boardView?boardNo="+board.getBoardNo();
	}
	
	//�� ���� �� ��û (��й�ȣ �Է�ȭ��)
	@RequestMapping(value="/boardRemove", method = RequestMethod.GET)
	public String boardRemove( @RequestParam(value="boardNo") int boardNo){
					
		return "boardRemove";
		}
		
		//�� ���� ��û
	@RequestMapping(value="/boardRemove", method = RequestMethod.POST)
	public String boardRemove(@RequestParam(value="boardNo") int boardNo, @RequestParam(value="boardPw") String boardPw){
		dao.deleteBoard(boardNo, boardPw);
		
		return "redirect:/boardList";
		}
	
	
	// �� �� ���� ��û
	@RequestMapping(value= "/boardView", method = RequestMethod.GET)
	public String boardView(Model model, @RequestParam(value="boardNo",required=true) int boardNo){
		Board board = dao.getBoard(boardNo);
		model.addAttribute("board", board);
		return "/boardView";
		
	}
	
	// ����Ʈ ��û
	@RequestMapping(value={"/","/boardList"}, method = RequestMethod.GET)
	// �������� ��û�� �迭 ������� �Ҽ� �ִ�. ������ ������ �̸����� ��û�� ������ �߻��Ѵ�. ex) "/" �� �ٸ������� "/" ���� ��û�� ����
	public String boardList(Model model, @RequestParam(value="currentPage",required=false, defaultValue="1") int currentPage){ // �ڵ������� int�� �� ��ȯ
		// currrentPage�� �޽��� ����Ʈ ������ 1�� �ش�.
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
		System.out.println("boardAdd ��û");
		return "boardAdd";
		
	}
	
	@RequestMapping(value="/board/boardAdd", method = RequestMethod.POST)
	public String boardAdd(Board board){
		System.out.println("POST ��û");
		System.out.println(board);
		dao.insertBoard(board);
		return "redirect:/boardList"; //������ ���� ���� �����̷�Ʈ ���Ѷ�
		
	}

}
