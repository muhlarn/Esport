package com.esports.team.nosql;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Player {
	
    private String playername ; 
    private String state ; 
    private String country ; 
    private String teamname ; 
    private int points; 
    private String bio ; 
    private String email ; 
    private String username ; 
    private String password ; 
    private String nickname ;
    private String dateofbirth ;
    private String visibility ;
    private String notificationtype ;
    private String iscaptain ;
    
    
	public String getPlayername() {
		return playername;
	}
	public void setPlayername(String playername) {
		this.playername = playername;
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
	public String getTeamname() {
		return teamname;
	}
	public void setTeamname(String teamname) {
		this.teamname = teamname;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public String getNotificationtype() {
		return notificationtype;
	}
	public void setNotificationtype(String notificationtype) {
		this.notificationtype = notificationtype;
	}
	public String getIscaptain() {
		return iscaptain;
	}
	public void setIscaptain(String iscaptain) {
		this.iscaptain = iscaptain;
	}
    

}
