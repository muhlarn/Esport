package com.esports.service.nosql;

import java.util.List;

import com.esports.team.nosql.Player;
import com.esports.team.nosql.Team;
import com.esports.team.nosql.User;

public interface UsersService {
	
	public Player login(User user); 
	
}
