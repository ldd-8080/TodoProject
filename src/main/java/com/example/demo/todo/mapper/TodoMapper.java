package com.example.demo.todo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.todo.vo.TodoVo;

@Mapper
public interface TodoMapper {

	List<TodoVo> getTodoList();

	void insertTodo(TodoVo todoVo);

	void updateTodo(TodoVo todoVo);

}
