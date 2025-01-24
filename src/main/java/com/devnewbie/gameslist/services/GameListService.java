package com.devnewbie.gameslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devnewbie.gameslist.dto.GameListDTO;
import com.devnewbie.gameslist.entities.GameList;
import com.devnewbie.gameslist.projections.GameMinProjection;
import com.devnewbie.gameslist.repositories.GameListRepository;
import com.devnewbie.gameslist.repositories.GameRepository;

@Service
public class GameListService {

	@Autowired
	private GameListRepository gameListRepository;
	
	@Autowired
	private GameRepository gameRepository;
	
	@Transactional(readOnly = true)
	public List<GameListDTO> findAll(){
		List<GameList> result = gameListRepository.findAll();
		return result.stream().map(x -> new GameListDTO(x)).toList();
	}
	
	@Transactional
	public void move(Long listId, int soucerIndex, int destinationIndex) {
		
		List<GameMinProjection> list = gameRepository.searchByList(listId);
		
		GameMinProjection obj = list.remove(soucerIndex);
		list.add(destinationIndex, obj);
		
		int min = soucerIndex < destinationIndex ? soucerIndex : destinationIndex;
		int max = soucerIndex < destinationIndex ? destinationIndex : soucerIndex;	
		
		for(int i = min; i <= max; i++) {
			gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
		}
	}
}
