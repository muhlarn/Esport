package com.esports.service.nosql;

import java.util.List;

import com.esports.team.nosql.Player;
import com.esports.team.nosql.Team;

public interface PlayerService {
	
	public List<Player> getAllPlayers();
	public List<Player> getPlayersByName(String playerName);
	public Player createPlayer(Player player);
	public Player updateTeam(Player player);

}
