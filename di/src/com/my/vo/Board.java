package com.my.vo;
import java.util.Date;

import org.springframework.stereotype.Component;
public class Board {
	private int level;
	private int board_no;
	private int parent_no;
	private String board_title;
	private String board_writer;
	private Date board_dt;
	private String board_content;
	public Board() {
		super();
	}
	public Board(int level, int board_no, int parent_no, String board_title, String board_writer, Date board_dt,
			String board_content) {
		super();
		this.level = level;
		this.board_no = board_no;
		this.parent_no = parent_no;
		this.board_title = board_title;
		this.board_writer = board_writer;
		this.board_dt = board_dt;
		this.board_content = board_content;
	}
	public Board(String board_title, String board_writer, String board_content) {
		super();
		this.board_title = board_title;
		this.board_writer = board_writer;
		this.board_content = board_content;
	}
	public Board(int parent_no, String board_title, String board_writer, String board_content) {
		super();
		this.parent_no = parent_no;
		this.board_title = board_title;
		this.board_writer = board_writer;
		this.board_content = board_content;
	}
	
	@Override
	public String toString() {
		return "Board [level=" + level + ", board_no=" + board_no + ", parent_no=" + parent_no + ", board_title="
				+ board_title + ", board_writer=" + board_writer + ", board_dt=" + board_dt + ", board_content="
				+ board_content + "]";
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public int getParent_no() {
		return parent_no;
	}
	public void setParent_no(int parent_no) {
		this.parent_no = parent_no;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_writer() {
		return board_writer;
	}
	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}
	public Date getBoard_dt() {
		return board_dt;
	}
	public void setBoard_dt(Date board_dt) {
		this.board_dt = board_dt;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	
	
}
