package com.geoquiz.app;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class HighscoreListe extends ListActivity {
	
	private ArrayList<String> results = new ArrayList<String>();
	private List<HighscoreData> hlist;
    private int count;
	private String username;
	private String userdate;
	private int hscore;
	private int id;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
				
		displayResultList();
		
		
		}
		
	private void displayResultList() {
		
		
		count=0;
				
		Highscore hiscore = new Highscore(this);
		//userdate = new SimpleDateFormat("dd:MM:yyyy").format(new Date());
		
		hiscore.addData(new HighscoreData("test1", "242", 210));
		hiscore.addData(new HighscoreData("test2", "123", 250));
		
		hlist = hiscore.getAllData();
		
		if(count<hlist.size())
		{
			
			HighscoreData highscore = hlist.get(count);	
			
			id = highscore.get_userid();
			username = highscore.getUser_name();
			hscore = highscore.getHighscore();

			results.add(id + " " + username + " " + hscore +  " " + userdate);
		    count++;
		}
		
		
		TextView tView = new TextView(this);
        tView.setText("Highscore Liste");
        getListView().addHeaderView(tView);
        
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, results));
        
		
	}
		
		
}
