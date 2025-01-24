package com.devnewbie.gameslist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devnewbie.gameslist.dto.GameListDTO;
import com.devnewbie.gameslist.dto.GameMinDTO;
import com.devnewbie.gameslist.dto.ReplacementDTO;
import com.devnewbie.gameslist.services.GameListService;
import com.devnewbie.gameslist.services.GameService;

@RestController
@RequestMapping(value = "/lists")
public class GameListController {

	@Autowired
	private GameListService gameListService;
	
	@Autowired
	private GameService gameService;
	
	
	@GetMapping
	public List<GameListDTO> findAll(){
		List<GameListDTO> dto = gameListService.findAll();
		return dto;
	}	
	
	@GetMapping(value = "/{listId}/games")
	public List<GameMinDTO> findByList(@PathVariable Long listId){
		List<GameMinDTO> dto = gameService.findByList(listId);
		return dto;
	}
	
	@PostMapping(value = "/{listId}/replacement")
	public void move(@PathVariable Long listId, @RequestBody ReplacementDTO body){
		gameListService.move(listId, body.getSourceIndex(), body.getDestinationIndex());
	}	
}
