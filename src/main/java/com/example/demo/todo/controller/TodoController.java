package com.example.demo.todo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.todo.service.TodoService;
import com.example.demo.todo.vo.TodoVo;

@Controller
public class TodoController {

	@Autowired
	TodoService todoService;
	
	@RequestMapping("/")
	@ResponseBody
	public String index(HttpServletRequest request) {
		System.out.println(request.getRequestURI());
		System.out.println(request.getRequestURL());
		return "index";
	}
	
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
	
	@DeleteMapping("/todo")
	public void deleteTodo(@RequestBody TodoVo todoVo) throws Exception {
		todoService.deleteTodo(todoVo);
	}
}
