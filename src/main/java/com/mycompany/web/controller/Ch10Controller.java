package com.mycompany.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.json.JSONObject;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.web.dto.Ch10Board;
import com.mycompany.web.dto.Ch10Member;
import com.mycompany.web.service.Ch10Service;
import com.mycompany.web.service.LoginResult;

@Controller
@RequestMapping("/ch10")
public class Ch10Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch10Controller.class);

	@Autowired
	private Ch10Service service;
	@Resource(name="dataSource")
	private DataSource dataSource;

	@RequestMapping("/content")
	public String content() {
		return "ch10/content";
	}
	@RequestMapping("/connTest")
	public void connTest(HttpServletResponse response) {
		boolean result = false;
		try {
			//커넥션 풀에서 연결된 커넥션 얻기
			Connection conn = dataSource.getConnection();
			if(conn!=null) result = true;
		}catch(Exception e){
			e.printStackTrace();
		}

		try {
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter pw = response.getWriter();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result",result);
			pw.print(jsonObject.toString());
			pw.flush();
			pw.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/boardList")
	public String boardList(Model model, @RequestParam(defaultValue = "1")int pageNo, HttpSession session) {
		session.setAttribute("pageNo", pageNo);

		//페이지당 행수
		int rowsPerPage = 10;

		//이전, 다음을 클릭했을때 나오는 페이지 수 
		int pagesPerGroup = 5;

		//전체 게시물 수 
		int totalRowNum = service.getTotalRowNo();

		//전체 페이지 수 
		int totalPageNum = totalRowNum / rowsPerPage;
		if(totalRowNum % rowsPerPage != 0) totalPageNum++;

		//전체 그룹 수 
		int totalGroupNum = totalPageNum / pagesPerGroup;
		if(totalPageNum % pagesPerGroup != 0 ) totalGroupNum++;

		//현재 페이지의 그룹 번호
		int groupNo =(pageNo -1)/pagesPerGroup +1;
		//현재 그룹의 시작 페이지 번호 
		int startPageNo = (groupNo-1)*pagesPerGroup + 1;
		//현제 그룹의 마지막 페이지 번호
		int endPageNo = startPageNo + pagesPerGroup - 1;
		if(pageNo == totalPageNum) endPageNo = totalPageNum;
		//pageNo == totalPageNum

		//현재 페이지의 시작 행 번호 
		int startRowNo = (pageNo - 1)*rowsPerPage + 1; 

		//현재 페이지의 끝 행 번호 
		int endRowNo = pageNo*rowsPerPage;
		if(groupNo == totalGroupNum) endRowNo = totalRowNum;

		//현재 페이지의 게시물 가져오기
		List<Ch10Board> boardList = service.getBoardList(startRowNo, endRowNo);

		//jsp로 페이지 정보 넘기기
		model.addAttribute("pagesPerGroup",pagesPerGroup);//5
		model.addAttribute("totalPageNum",totalPageNum);//11
		model.addAttribute("totalGroupNum",totalGroupNum); //3
		model.addAttribute("groupNo",groupNo); //현재페이지가 1 이면 1 6이면 2
		model.addAttribute("startPageNo",startPageNo); // 현재그룹이 1 이라면 1 2그룹이라면  6 3그룹이라면 11 
		model.addAttribute("endPageNo",endPageNo);
		model.addAttribute("pageNo",pageNo);//현재 페이지 번호
		model.addAttribute("boardList", boardList); 
		return "ch10/boardList";
	}

	@RequestMapping("/writeBoardForm")
	public String writeBoardForm(HttpSession session) {
		String mid = (String) session.getAttribute("mid");
		if(mid == null ) {
			return "redirect:/ch10/loginForm";
		}
		return "ch10/writeBoardForm";
	}




	@RequestMapping("/writeBoard")
	public String writeBoard(Ch10Board board,HttpSession session) {
		board.setBwriter((String)session.getAttribute("mid"));
		//서비스에서 writeBoard메서드 호출! 
		service.writeBoard(board);
		return "redirect:/ch10/boardList";	
	}

	@GetMapping("/login")
	public String loginFrom(String error,Model model ) {
		if(error != null) {
			if(error.equals("fail_mid")) {
				model.addAttribute("midError","*아이디가 존재하지 않습니다");
			}else if(error.equals("fail_mpassword")) {
				model.addAttribute("mpasswordError","*패스워드가 틀립니다");
			}
		}
		return "ch10/loginForm";
	}
	//로그인 값 세가지 
	//1) 로그인 성공
	//2) 로그인 실패 아이디
	//3) 로그인 실패 패스워드
	@PostMapping("/login")
	public String login(String mid, String mpassword, HttpSession session) {
		LoginResult result = service.login(mid,mpassword);
		if(result == LoginResult.FAIL_MID) {

			return "redirect:/ch10/loginForm?error=fail_mid";
		}else if(result == LoginResult.FAIL_MPASSWORD) {

			return "redirect:/ch10/loginForm?error=fail_mpassword";
		}
		session.setAttribute("mid", mid);
		return "redirect:/ch10/boardList";
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("mid");
		return "redirect:/ch10/boardList";
	}
	@GetMapping("/join")
	public String joinForm() {
		return "ch10/joinForm";
	}
	@PostMapping("/join")
	public String join(Ch10Member member) {
		service.join(member);
		return "redirect:/ch10/loginForm";
	}
	@RequestMapping("/checkMid")
	public void checkMid(String mid,HttpServletResponse response) throws IOException {
		boolean result = service.checkMid(mid);
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", result);
		pw.print(jsonObject.toString());
		pw.flush();
		pw.close();
	}
	@RequestMapping("/boardDetail")
	public String boardDetail(int bno, Model model) {
		service.increaseHitcount(bno);
		Ch10Board board = service.getBoard(bno);
		model.addAttribute("board",board);
		return "ch10/boardDetail";
	}
	@GetMapping("/updateBoard")
	public String updateBoard(int bno, Model model) {
		Ch10Board board = service.getBoard(bno);
		model.addAttribute("board",board);
		return "ch10/updateBoardForm";
		
	}


	@PostMapping("/updateBoard")
	public String updateBoardForm(Ch10Board board, HttpSession session) {
		service.updateBoard(board);
		int pageNo = (Integer) session.getAttribute("pageNo");
		return "redirect:/ch10/boardList?pageNo=" + pageNo;	
	}
	@RequestMapping("/deleteBoard")
	public String deleteBoard(int bno,HttpSession session) {
		service.deleteBoard(bno);
		int pageNo = (Integer) session.getAttribute("pageNo");
		return "redirect:/ch10/boardList?pageNo=" + pageNo;	
	}

}