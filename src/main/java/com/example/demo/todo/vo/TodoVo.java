package com.example.demo.todo.vo;

import org.apache.ibatis.type.Alias;

@Alias("todoVo")
public class TodoVo {

	private String id;
	private String title;
	private boolean done;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean getDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
}
