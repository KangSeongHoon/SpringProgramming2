package com.mycompany.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.web.dto.Ch10Board;

@Component
public class Ch10BoardDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	public List<Ch10Board> selctList(int startRowNo, int endRowNo) {
		Map<String, Integer> map = new HashMap<>();
		map.put("startRowNo", startRowNo );
		map.put("endRowNo", endRowNo);
		List<Ch10Board> boardList = sqlSessionTemplate.selectList("board.selectList",map);
		return boardList;
	}
	//마이바티스에 있는 board.selectTotalRowNum를 selectTotalRowNum을 담는다
	public int selectTotalRowno() {
		int selectTotalRowNum = sqlSessionTemplate.selectOne("board.selectTotalRowNum");
		return selectTotalRowNum;
	}
	
	public int insert(Ch10Board board) {
		//ch10Board빈을  Ch10service에 있는  writeBoard가 호출 한다.
		int rows = sqlSessionTemplate.insert("board.insert",board);
		return rows;
	}
	public Ch10Board selectBoard(int bno) {
		// TODO Auto-generated method stub
		Ch10Board board = sqlSessionTemplate.selectOne("board.selectBoard",bno);
		return board;
	}
	public int updateHitcount(int bno) {
		//실제로 업데이트된 행의 수 
		int rows = sqlSessionTemplate.update("board.update",bno);
		return rows;
		
		
	}
	public int updateBoard(Ch10Board board) {
		int rows = sqlSessionTemplate.update("board.updateBoard", board);
		return rows;
		
	}
	public int deleteBoard(int bno) {
		// TODO Auto-generated method stub
		int rows = sqlSessionTemplate.delete("board.deleteBoard", bno);
		return rows;
	}
 
}
