package com.devnewbie.gameslist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devnewbie.gameslist.entities.GameList;

public interface GameListRepository extends JpaRepository<GameList, Long>{
}
