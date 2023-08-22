package com.example.demo.repository;

import com.example.demo.domain.Board;
import com.example.demo.domain.BoardListMapping;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long> {
	
	List<BoardListMapping> findAllBy();
	
	@Query(value="SELECT board_id AS 'boardId', created_at AS 'createdAt', nickname, title, views, writer "
			+ "FROM ( "
			+ "  SELECT * "
			+ "  FROM board "
			+ "  WHERE TIMESTAMPDIFF(HOUR, created_at, NOW()) < 25 "
			+ ") board "
			+ "ORDER BY views DESC "
			+ "LIMIT 5;", nativeQuery=true)
	List<BoardListMapping> findDailyTop();
	
	@Query(value="SELECT board_id AS 'boardId', created_at AS 'createdAt', nickname, title, views, writer "
			+ "FROM ( "
			+ "  SELECT * "
			+ "  FROM board "
			+ "  WHERE TIMESTAMPDIFF(DAY, created_at, NOW()) < 8 "
			+ ") board "
			+ "ORDER BY views DESC "
			+ "LIMIT 5;", nativeQuery=true)
	List<BoardListMapping> findWeeklyTop();
	
	@Query(value="SELECT board_id AS 'boardId', created_at AS 'createdAt', nickname, title, views, writer "
			+ "FROM ( "
			+ "  SELECT * "
			+ "  FROM board "
			+ "  WHERE TIMESTAMPDIFF(MONTH, created_at, NOW()) < 2 "
			+ ") board "
			+ "ORDER BY views DESC "
			+ "LIMIT 5;", nativeQuery=true)
	List<BoardListMapping> findMonthlyTop();
}