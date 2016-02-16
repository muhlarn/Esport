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
import com.esports.restresources.TeamResource;
import com.esports.service.nosql.TeamService;
import com.esports.team.nosql.Team;
import com.esports.util.EsportSorter;
import com.google.gson.Gson;

public class TeamsServiceImpl implements TeamService {
	
	private static Logger logger = Logger.getLogger(TeamsServiceImpl.class.getName());
	
	public List<Team> getAllTeams() {
		
		Gson gson = new Gson();
		String teamsJson = null;
		List<Team> teamList = new ArrayList<Team>();
		Session session = CassandraConnector.getInstance().getSession();
		
		final ResultSet results = session.execute(
			      "SELECT json * from esports.teams");
		 
		   for (Row row : results) {
			   
			   teamsJson = row.toString().substring(4, row.toString().length() - 1);
			   //System.out.println(teamsJson);

			   Team teamObj = gson.fromJson(teamsJson, Team.class);			   
			   //System.out.println(teamObj.getTeamname());
			   teamList.add(teamObj);
		   }
		   
		   String jsonList = gson.toJson(teamList);
		   System.out.println(jsonList);
		   EsportSorter.teamSorter(teamList, "points");
		   
		   session.close();		   
		   
		   return teamList;
		   
	}
	

	public List<Team> getTeamsByState(Team team) {
		Gson gson = new Gson();
		String teamsJson = null;
		List<Team> teamList = new ArrayList<Team>();
		Session session = CassandraConnector.getInstance().getSession();
		
		String select = "SELECT json * from esports.teams_by_state where state = ?";
		if (null != team && null != team.getTeamname() && team.getTeamname().length() > 0) {
			select = select + " and teamname = ?";
		}
		
		ResultSet results = null;//CassandraConnector.getInstance().getSession().execute(select);
		
		PreparedStatement statement = CassandraConnector.getInstance().getSession().prepare(select);
		BoundStatement boundStatement = new BoundStatement(statement);
		
		logger.info("sql statement: " + select);
		logger.info("sql state: " + team.getState());
		if (null != team && null != team.getTeamname() && team.getTeamname().length() > 0) {
			results = session.execute(boundStatement.bind(team.getState(), team.getTeamname()));
		} else {
			results = session.execute(boundStatement.bind(team.getState()));
		}
	   for (Row row : results) {
		   
		   teamsJson = row.toString().substring(4, row.toString().length() - 1);
		   Team teamObj = gson.fromJson(teamsJson, Team.class);
		   teamList.add(teamObj);
	   }
	   
	   String jsonList = gson.toJson(teamList);
	   logger.info(jsonList);
	   EsportSorter.teamSorter(teamList, "points");
	   
	   session.close();
	   
	   return teamList;
	}


	public Team createTeam(Team team) {
		
		Session session = CassandraConnector.getInstance().getSession();
		Gson gson = new Gson();
        String jsonString = gson.toJson(team);
        
        logger.info("inserting this json team: \n " + jsonString);		
		session.execute("INSERT INTO esports.teams JSON '" + jsonString + "'");		

        logger.info("inserting this json teams_by_state: \n " + jsonString);        
		session.execute("INSERT INTO esports.teams_by_state JSON '" + jsonString + "'");
		
		session.getCluster().close();
		
		System.out.println("done inserting record");
		
		return team;
		
	}


	public Team updateTeam(Team team) {
		createTeam(team);
		
		return team;
		
	}


}
