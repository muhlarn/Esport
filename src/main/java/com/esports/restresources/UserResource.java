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
import com.esports.service.nosql.UsersService;
import com.esports.service.nosql.impl.TeamsServiceImpl;
import com.esports.service.nosql.impl.UsersServiceImpl;
import com.esports.team.nosql.Player;
import com.esports.team.nosql.Team;
import com.esports.team.nosql.User;


//@RequestScoped
@Path("/userresource")  //TODO Rename this to login
@Singleton
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class UserResource { //extends Application {
	
	private static Logger logger = Logger.getLogger(UserResource.class.getName());
	
		
	@POST
	@Path("/login") // TODO remove this 
	public Response login(User user) {
		
		logger.info("user: " + user);
		UsersService userService = new UsersServiceImpl();
		Player teamList = userService.login(user);
		
		logger.info("don logging in");
		
		Response response =  Response.ok(teamList).header("Access-Control-Allow-Origin", "*").
				header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
		
		return response;
		
	}
	
	@POST
	@Path("/test")
	public void test(String strVal) {
		
		logger.info("strVal: " + strVal);
		
	}

}
