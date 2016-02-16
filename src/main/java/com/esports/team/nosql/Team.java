package com.esports.team.nosql;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Team {
	
	private String teamname;
	private String state;
	private String country;
	private String teammember;
    private int points;
    private String bio;
    private String logopath;
    private String captain;
    private String vicecaptain;
    private String availability;
    
	public String getTeamname() {
		return teamname;
	}
	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getTeammember() {
		return teammember;
	}
	public void setTeammember(String teammember) {
		this.teammember = teammember;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public String getLogopath() {
		return logopath;
	}
	public void setLogopath(String logopath) {
		this.logopath = logopath;
	}
	public String getCaptain() {
		return captain;
	}
	public void setCaptain(String captain) {
		this.captain = captain;
	}
	public String getVicecaptain() {
		return vicecaptain;
	}
	public void setVicecaptain(String vicecaptain) {
		this.vicecaptain = vicecaptain;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
    
    

}
