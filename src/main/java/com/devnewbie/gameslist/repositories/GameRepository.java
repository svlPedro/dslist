package com.devnewbie.gameslist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devnewbie.gameslist.entities.Game;

public interface GameRepository extends JpaRepository<Game, Long>{
}
