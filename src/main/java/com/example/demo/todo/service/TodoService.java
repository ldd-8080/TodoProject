package com.example.demo.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.todo.mapper.TodoMapper;
import com.example.demo.todo.vo.TodoVo;

@Service
public class TodoService {
	
	@Autowired
	TodoMapper todoMapper;
	
	public List<TodoVo> getTodoList() {
		return todoMapper.getTodoList();
	}

	public void createTodo(TodoVo todoVo) {
		System.out.println(todoVo.getTitle());
		todoMapper.insertTodo(todoVo);
	}

	public void updateTodo(TodoVo todoVo) {
		todoMapper.updateTodo(todoVo);
	}

	public void deleteTodo(TodoVo todoVo) {
		todoMapper.deleteTodo(todoVo);
	}

}
