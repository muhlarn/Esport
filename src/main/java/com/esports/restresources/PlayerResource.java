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

import com.esports.service.nosql.PlayerService;
import com.esports.service.nosql.TeamService;
import com.esports.service.nosql.UsersService;
import com.esports.service.nosql.impl.PlayersServiceImpl;
import com.esports.service.nosql.impl.TeamsServiceImpl;
import com.esports.service.nosql.impl.UsersServiceImpl;
import com.esports.team.nosql.Player;
import com.esports.team.nosql.Team;
import com.esports.team.nosql.User;


@Path("/players")
@Singleton
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class PlayerResource { //extends Application {
	
	private static Logger logger = Logger.getLogger(PlayerResource.class.getName());
	
		
	@GET
	public Response getPlayers() {
		
		PlayerService playerService = new PlayersServiceImpl();
		List<Player> playersList = playerService.getAllPlayers();
		
		logger.info("don retrieving players");
		
		Response response =  Response.ok(playersList.toArray(new Player[playersList.size()])).header("Access-Control-Allow-Origin", "*").
				header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
		
		return response;
		
	}
	
	@POST
	public Response createPlayer(Player player) {
		
		logger.info("player: " + player);
		PlayerService playerService = new PlayersServiceImpl();
		playerService.createPlayer(player);
		
		logger.info("don creating playeyer");
		
		Response response =  Response.ok(player).header("Access-Control-Allow-Origin", "*").
				header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
		
		return response;
		
	}
	
	@POST
	@Path("/playertest")
	public void test(String strVal) {
		
		logger.info("strVal: " + strVal);
		
	}
	
	@POST
	@Path("/insertusers")
	public Response createUser(Player player) {
		
		logger.info("player: " + player);		
		new PlayersServiceImpl().createUser(player);
		
		logger.info("don creating user");
		
		Response response =  Response.ok(player).header("Access-Control-Allow-Origin", "*").
				header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
		
		return response;
		
	}

}
