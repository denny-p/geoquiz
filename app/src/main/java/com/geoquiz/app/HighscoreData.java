package com.geoquiz.app;


public class HighscoreData {

	private int _userid;
	private String _username;
	private String _userdate; 
	private int _highscore;
	
	public HighscoreData(){
	
	}


	public HighscoreData(String user_name, String user_date, int highscore) {
		super();
		this._username = user_name;
		this._userdate = user_date;
		this._highscore = highscore;
	}

	
	
	public int get_userid() {
		return _userid;
	}


	public void set_userid(int _userid) {
		this._userid = _userid;
	}


	public int getHighscore() {
		return _highscore;
	}


	public void setHighscore(int highscore) {
		this._highscore = highscore;
	}

	

	public String getUser_name() {
		return _username;
	}


	public void setUser_name(String user_name) {
		this._username = user_name;
	}


	public String getUser_date() {
		return _userdate;
	}


	public void setUser_date(String user_date) {
		this._userdate = user_date;
	}

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "HighscoreData [user_id=" + _userid + ", user_name =" + _username + ", highscore=" + _highscore
                + ", user_date=" + _userdate + "]";
	}
	
}
