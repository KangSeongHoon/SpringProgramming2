package com.mycompany.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.web.dao.Ch10BoardDao;
import com.mycompany.web.dao.Ch10MemberDao;
import com.mycompany.web.dto.Ch10Board;
import com.mycompany.web.dto.Ch10Member;

@Service
public class Ch10Service {
	@Autowired
	private Ch10BoardDao boardDao;
	@Autowired
	private Ch10MemberDao memberDao;
	
	
	// Ch10Board 빈을 리스트에 담는다 
	public List<Ch10Board> getBoardList(int startRowNo, int endRowNo){
	
		//  Dao에 있는 selectList메서드를 빈 리스트에 담은 boardList에 담는다
		List<Ch10Board> boardList = boardDao.selctList(startRowNo, endRowNo);
		return boardList;
	}
	public void writeBoard(Ch10Board board) {
		//dao에 있는 insert 메서드를 호출 한다
		boardDao.insert(board);
	}
	//dao에 있는 selectTotalRowno메서드를 totalRowNum에 담는다
	public int getTotalRowNo() {
		int totalRowNum = boardDao.selectTotalRowno();
		return totalRowNum;
	}
	public LoginResult login(String mid, String mpassword) {
		Ch10Member member =memberDao.selectMember(mid);
		if(member == null) {
			return LoginResult.FAIL_MID;
		}else {
			if(mpassword.equals(member.getMpassword())) {
				return LoginResult.SUCCESS;
			}else {
				return LoginResult.FAIL_MPASSWORD;
			}
		}
		
	}
	
	public boolean checkMid(String mid) {
		Ch10Member member =memberDao.selectMember(mid);
		if(member == null) {
			return true;
		}else {
			return false;
		}
		
	}
	public void join(Ch10Member member) {
		memberDao.insert(member);
		
	}
	public Ch10Board getBoard(int bno) {
		Ch10Board board = boardDao.selectBoard(bno);
		return board;
	}
	public void increaseHitcount(int bno) {
	boardDao.updateHitcount(bno);
		
	}
	public void updateBoard(Ch10Board board) {
		boardDao.updateBoard(board);
		
	}
	public void deleteBoard(int bno) {
		// TODO Auto-generated method stub
		boardDao.deleteBoard(bno);
	}
	
	
	
	
}
