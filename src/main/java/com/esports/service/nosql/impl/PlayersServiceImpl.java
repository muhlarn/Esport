package com.esports.service.nosql.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.esports.dataaccess.CassandraConnector;
import com.esports.service.nosql.PlayerService;
import com.esports.team.nosql.Player;
import com.esports.util.EsportSorter;
import com.google.gson.Gson;

public class PlayersServiceImpl implements PlayerService {
	
	private static Logger logger = Logger.getLogger(PlayersServiceImpl.class.getName());


	@Override
	public List<Player> getAllPlayers() {
		
		Gson gson = new Gson();
		String playerJson = null;
		List<Player> playerList = new ArrayList<Player>();
		Session session = CassandraConnector.getInstance().getSession();
		
		final ResultSet results = session.execute(
			      "SELECT json * from esports.players");
		 
		   for (Row row : results) {
			   
			   playerJson = row.toString().substring(4, row.toString().length() - 1);
			   //System.out.println(teamsJson);

			   Player teamObj = gson.fromJson(playerJson, Player.class);			   
			   //System.out.println(teamObj.getTeamname());
			   playerList.add(teamObj);
		   }
		   
		   String jsonList = gson.toJson(playerList);
		   System.out.println(jsonList);
		   EsportSorter.playerSorter(playerList, "points");
		   
		   session.close();
		   
		   return playerList;
	}


	@Override
	public List<Player> getPlayersByName(String playerName) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Player createPlayer(Player player) {
		
		Session session = CassandraConnector.getInstance().getSession();
		Gson gson = new Gson();
        String jsonString = gson.toJson(player);
        
        logger.info("inserting this json player: \n " + jsonString);	
        session.execute("INSERT INTO esports.players JSON '" + jsonString + "'");
		
		logger.info("inserting this json player_by_state: \n " + jsonString);	
		session.execute("INSERT INTO esports.players_by_state JSON '" + jsonString + "'");
		
        logger.info("inserting this json user: \n " + jsonString);		
        session.execute("INSERT INTO esports.user JSON '" + jsonString + "'");
		
		session.getCluster().close();
		
		System.out.println("done inserting record");
		
		return player;
		
	}

	//TODO this is temporary and must be removed
	public Player createUser(Player player) {
		Session session = CassandraConnector.getInstance().getSession();
		Gson gson = new Gson();
		String jsonString = gson.toJson(player);
		
		logger.info("inserting this json players: \n " + jsonString);	
		PreparedStatement Statement = session.prepare("INSERT INTO esports.players JSON ?");		
		BoundStatement boundStatement = new BoundStatement(Statement);
		session.execute(boundStatement.bind(jsonString));		
		
		session.close();
		
		System.out.println("done inserting test record");
		
		return player;
		
	}


	@Override
	public Player updateTeam(Player player) {
		createPlayer(player);
		
		return player;
		
	}


}
