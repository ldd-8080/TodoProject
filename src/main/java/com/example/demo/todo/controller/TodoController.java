package com.example.demo.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.todo.service.TodoService;
import com.example.demo.todo.vo.TodoVo;

@RestController
public class TodoController {

	@Autowired
	TodoService todoService;
	
	@GetMapping("/todo")
	@ResponseBody
	public List<TodoVo> getTodoList() throws Exception {
		return todoService.getTodoList();
	}
	
	@PostMapping("/todo")
	public void createTodo(@RequestBody TodoVo todoVo) throws Exception {
		todoService.createTodo(todoVo);
	}
	
	@PutMapping("/todo")
	public void updateTodo(@RequestBody TodoVo todoVo) throws Exception {
		todoService.updateTodo(todoVo);
	}
}
