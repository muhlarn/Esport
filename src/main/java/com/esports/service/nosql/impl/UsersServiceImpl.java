package com.esports.service.nosql.impl;


import java.util.logging.Logger;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.esports.dataaccess.CassandraConnector;
import com.esports.service.nosql.UsersService;
import com.esports.team.nosql.Player;
import com.esports.team.nosql.User;
import com.google.gson.Gson;

public class UsersServiceImpl implements UsersService {
	
	private static Logger logger = Logger.getLogger(UsersServiceImpl.class.getName());

	public Player login(User user) {
		
		Session session = CassandraConnector.getInstance().getSession();
		Gson gson = new Gson();
        Player player = new Player();
        
        String select = "SELECT json * from esports.user where username = ? and password = ?";
		
		ResultSet results = null;//CassandraConnector.getInstance().getSession().execute(select);
		
		PreparedStatement statement = session.prepare(select);
		BoundStatement boundStatement = new BoundStatement(statement);
		results = session.execute(boundStatement.bind(user.getUsername(), user.getPassword()));
		
		logger.info("username = " + user.getUsername());
		logger.info("password = " + user.getPassword());	
		
		for (Row row : results) {
			logger.info("Found some values: " + row.getString(0));	
			player = gson.fromJson(row.getString(0), Player.class);
		
		}
		
		session.getCluster().close();
		
		logger.info("Player Name: " +player.getPlayername());
		logger.info("Team Name: " +player.getTeamname());
		
		return player;
	}


}
