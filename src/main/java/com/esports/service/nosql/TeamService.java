package com.esports.service.nosql;

import java.util.List;

import com.esports.team.nosql.Team;

public interface TeamService {
	
	public List<Team> getAllTeams();
	public List<Team> getTeamsByState(Team team);
	public Team createTeam(Team team);
	public Team updateTeam(Team team);

}
