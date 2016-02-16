package com.esports.restresources;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.esports.service.nosql.TeamService;
import com.esports.service.nosql.impl.TeamsServiceImpl;
import com.esports.team.nosql.Team;


@Path("/teams")
@Singleton
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class TeamResource { //extends Application {
	
	private static Logger logger = Logger.getLogger(TeamResource.class.getName());
	
	@GET
	public Response getTeams() {
		
		TeamService teamService = new TeamsServiceImpl();
		List<Team> teamList = teamService.getAllTeams();
		
		logger.info("done getting team list");
		for (Team team : teamList) {
			logger.info(team.getTeamname());
		}

		Response response =  Response.ok(teamList.toArray(new Team[teamList.size()])).build();
		
		return response;
		
	}
	
	@POST
	@Path("/state")
	public Response getTeamsByState(Team team) {
		
		logger.info("team: " + team);
		TeamService teamService = new TeamsServiceImpl();
		List<Team> teamList = teamService.getTeamsByState(team);
		
		logger.info("don getting team list");
		for (Team teamVal : teamList) {
			logger.info(teamVal.getTeamname());
		}
		
		Response response =  Response.ok(teamList.toArray(new Team[teamList.size()])).header("Access-Control-Allow-Origin", "*").
				header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
		
		return response;
		
	}
	
	@POST
	public Response createTeam(Team team) {
		
		logger.info("inserting team: " + team.getTeamname());
		TeamService teamService = new TeamsServiceImpl();
		teamService.createTeam(team);		
		logger.info("don getting team list: " + team.getTeamname());	
		
		Response response =  Response.ok(team).header("Access-Control-Allow-Origin", "*").
				header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
		
		return response;
	}

}
