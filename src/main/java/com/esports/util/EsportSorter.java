package com.esports.util;

import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.BeanComparator;

import com.esports.team.nosql.Player;
import com.esports.team.nosql.Team;

public class EsportSorter {

	public static void playerSorter(List<Player> playerList, String sortParam) {
		
		BeanComparator objComparator = new BeanComparator(sortParam);
		Collections.sort(playerList, objComparator);
		Collections.reverse(playerList);
		
	}
	
	public static void teamSorter(List<Team> teamList, String sortParam) {
		
		BeanComparator objComparator = new BeanComparator(sortParam);
		Collections.sort(teamList, objComparator);
		Collections.reverse(teamList);
		
	}
	
	
}
