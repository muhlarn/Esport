package co.za.esport.nosql.impl;

import java.util.ArrayList;
import java.util.List;

import com.esports.service.nosql.TeamService;
import com.esports.service.nosql.impl.TeamsServiceImpl;
import com.esports.team.nosql.Team;

import junit.framework.TestCase;

public class TeamServiceTest extends TestCase {

	private List<Team> teamList;
	
	protected void setUp() throws Exception {
		
		teamList = new ArrayList<Team>();
		
		Team team = new Team();
		team.setTeamname("aces");
		team.setTeammember("Khune");
		team.setState("PE");
		team.setCountry("south africa");
		team.setBio("uselesss team");
		teamList.add(team);
	}
	
	public void testInsertTeam() {
	    
		TeamService teamService = new TeamsServiceImpl();
		Team teamCreate = teamList.get(0);
		teamService.createTeam(teamCreate);
		
	    String expected = "aces";
	    String actual = teamCreate.getTeamname();
	    assertEquals("inserting aces as a team ", expected, actual);
	  }
	
	public void testTeamByState() {
		
		TeamService teamService = new TeamsServiceImpl();
		Team team = new Team();
		team.setState("plk");
		team.setTeamname("RegieTeam");	
		List<Team> teamList = teamService.getTeamsByState(team);
		
		String expected = "RegieTeam";
		for (Team teamVal : teamList) {
			assertEquals("inserting aces as a team ", expected, teamVal.getTeamname());
		} 
	}
}
