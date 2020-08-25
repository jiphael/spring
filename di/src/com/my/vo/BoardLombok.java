package com.my.vo;
import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Component
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class BoardLombok {
	private int level;
	private int board_no;
	private int parent_no;
	private String board_title;
	private String board_writer;
	private Date board_dt;
	private String board_content;
//	public BoardLombok() {
//		super();
//	}
//	public BoardLombok(int level, int board_no, int parent_no, String board_title, String board_writer, Date board_dt,
//			String board_content) {
//		super();
//		this.level = level;
//		this.board_no = board_no;
//		this.parent_no = parent_no;
//		this.board_title = board_title;
//		this.board_writer = board_writer;
//		this.board_dt = board_dt;
//		this.board_content = board_content;
//	}
	public BoardLombok(String board_title, String board_writer, String board_content) {
		super();
		this.board_title = board_title;
		this.board_writer = board_writer;
		this.board_content = board_content;
	}
	public BoardLombok(int parent_no, String board_title, String board_writer, String board_content) {
		super();
		this.parent_no = parent_no;
		this.board_title = board_title;
		this.board_writer = board_writer;
		this.board_content = board_content;
	}

	
	
}
