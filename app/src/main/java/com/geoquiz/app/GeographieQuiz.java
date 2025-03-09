/*
Copyright (c) 2025 Denny Placzek
All rights reserved. This program and the accompanying materials
are made available under the terms of the Eclipse Public License v1.0
which accompanies this distribution, and is available at
http://www.eclipse.org/legal/epl-v10.html

Contributors:
    Denny Placzek - initial API and implementation
*/

package com.geoquiz.app;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

public class GeographieQuiz extends Activity {
	
	private List<Data> list;
	private int counter;
	private int count;
	private int listcounter;
	private int points;
	private int pop1;
	private int pop2;
	private String countr1;
	private String countr2;
	private String formatPop1;
	private String formatPop2;
	private Button eins,zwei;
	private TextView frage;
	private TextView score;
	private TextView hinweis1;
	private TextView hinweis2;
	private DecimalFormat formatter = new DecimalFormat("###,###,###");

    private static final String LOG_TAG = null;

    private Data getdata;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		counter = 0;
		eins = (Button) findViewById(R.id.button1);
		zwei = (Button) findViewById(R.id.button2);
		frage = (TextView) findViewById(R.id.frage);
		score = (TextView) findViewById(R.id.score);
			
		Geo db = new Geo(GeographieQuiz.this);

        insertData(db);

        // get all Data
          list = db.getAllData();
          frage = (TextView) findViewById(R.id.frage);
          score = (TextView) findViewById(R.id.score);
          hinweis1 = (TextView) findViewById(R.id.hinweis1);
          hinweis2 = (TextView) findViewById(R.id.hinweis2);
          eins = (Button) findViewById(R.id.button1);
          zwei = (Button) findViewById(R.id.button2);
          counter = 0;
          listcounter = Math.round((list.size()/2));
          count = list.size()-1;
          points = 0;
          
                              
          Collections.shuffle(list);
          setQuestionView();
       
	      }
	
          public void setQuestionView()
          {
          //frage.setText(list.toString());
          if(counter<listcounter){
        	  
          Data data1 = list.get(counter);
          Data data2 = list.get(count);
                         
          frage.setText("Welches Land hat mehr Einwohner? \n\n1. " + data1.getCountry() + " \n\noder\n\n 2. " + data2.getCountry());
          
          pop1 = data1.getPopulation();
          pop2 = data2.getPopulation();
          countr1 = data1.getCountry();
          countr2 = data2.getCountry();
          
          formatPop1 = formatter.format(pop1);
          formatPop2 = formatter.format(pop2);
          
          eins.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(pop1 > pop2){
					points++;
				}else{
					points +=0;
				}
				
				counter++;
				
				score.setText("Score: " + points + " / " + counter + " | Noch " + (listcounter-counter) + " Fragen");
				hinweis1.setText("Die Einwohnerzahl von " + countr1 + " beträgt " + formatPop1);
				hinweis2.setText("Die Einwohnerzahl von " + countr2 + " beträgt " + formatPop2);
				count--;
				setQuestionView();
			}
		});
          
          zwei.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(pop2 > pop1){
					points++;
				}else{
					points +=0;
				}
				
				counter++;
				score.setText("Score: " + points + " / " + counter + " | Noch " + (listcounter-counter) + " Fragen");
				hinweis1.setText("Die Einwohnerzahl von " + countr1 + " beträgt " + formatPop1);
				hinweis2.setText("Die Einwohnerzahl von " + countr2 + " beträgt " + formatPop2);
				count--;
				setQuestionView();
			}
		});
        	  
           
       }
        else
        {
        	  counter=0;
        	  count=list.size()-1;
        	  Collections.shuffle(list);
        	  setQuestionView();
        }
     }

    private void insertData(Geo db){

        if(!db.chkGeo(1)) {

            db.addData(new Data("Germany", "Berlin", 81890000));
            db.addData(new Data("France", "Paris", 65700000));
            db.addData(new Data("Spain", "Madrid", 47270000));
            db.addData(new Data("Italy", "Rome", 60920000));
            db.addData(new Data("China", "Bejing", 1363260000));
            db.addData(new Data("India", "New Delhi", 1241400000));
            db.addData(new Data("United States", "Washington,D.C.", 317677000));
            db.addData(new Data("Indonesia", "Jakarta", 249866000));
            db.addData(new Data("Brazil", "Brasilia", 201032714));
            db.addData(new Data("Pakistan", "Islamabad", 185869000));
            db.addData(new Data("Nigeria", "Abuja", 173615000));
            db.addData(new Data("Bangladesh", "Dhaka", 152518015));
            db.addData(new Data("Russia", "Moscow", 143700000));
            db.addData(new Data("Japan", "Tokio", 127180000));
            db.addData(new Data("Mexico", "Mexico City", 118395054));
            db.addData(new Data("Philippines", "Manila", 99250600));
            db.addData(new Data("Vietnam", "Hanoi", 89708900));
            db.addData(new Data("Ethiopia", "Addis Ababa", 86613986));
            db.addData(new Data("Egypt", "Cairo", 86098900));
            db.addData(new Data("Iran", "Tehran", 77276000));
            db.addData(new Data("Turkey", "Ankara", 76667864));
            db.addData(new Data("D.R. of Congo", "Kinshasa", 67514000));
            db.addData(new Data("Thailand", "Bangkok", 65926261));
            db.addData(new Data("United Kingdom", "Kinshasa", 63705000));
            db.addData(new Data("Burma", "Naypyidaw", 53259000));
            db.addData(new Data("South Africa", "Pretoria", 52981991));
            db.addData(new Data("South Korea", "Seoul", 50219669));
            db.addData(new Data("Colombia", "Bogota", 47498000));
            db.addData(new Data("Ukraine", "Kiev", 45426200));
            db.addData(new Data("Tanzania", "Dodoma", 44928923));
            db.addData(new Data("Kenya", "Nairobi", 44354000));
            db.addData(new Data("Argentina", "Buenos Aires", 41660096));
            db.addData(new Data("Algeria", "Algiers", 38700000));
            db.addData(new Data("Poland", "Algiers", 38502396));
            db.addData(new Data("Sudan", "Khartoum", 37964000));
            db.addData(new Data("Uganda", "Kampala", 35357000));
            db.addData(new Data("Canada", "Ottawa", 35295770));
            db.addData(new Data("Iraq", "Baghdad", 34035000));
            db.addData(new Data("Morocco", "Rabat", 33197400));
            db.addData(new Data("Peru", "Lima", 30475144));
            db.addData(new Data("Uzbekistan", "Tashkent", 30183400));
            db.addData(new Data("Malaysia", "Kuala Lumpur", 30034000));
            db.addData(new Data("Saudi Arabia", "Riyadh", 29994272));
            db.addData(new Data("Venezuela", "Caracas", 28946101));
            db.addData(new Data("Nepal", "Kathmandu", 26494504));
            db.addData(new Data("Afghanistan", "Kabul", 25500100));
            db.addData(new Data("Yemen", "Sanaa", 25235000));
            db.addData(new Data("North Korea", "Pyongyang", 24895000));
            db.addData(new Data("Ghana", "Accra", 24658823));
            db.addData(new Data("Mozambique", "Maputo", 23700715));
            db.addData(new Data("Australia", "Canberra", 23409084));
            db.addData(new Data("Taiwan", "Taipeh", 23377515));
            db.addData(new Data("Ivory Coast", "Yamoussoukro", 23202000));
            db.addData(new Data("Syria", "Damascus", 21898000));
            db.addData(new Data("Madagascar", "Antananarivo", 21263403));
            db.addData(new Data("Angola", "Luanda", 20609294));
            db.addData(new Data("Cameroon", "Yaound", 20386799));
            db.addData(new Data("Sri Lanka", "Colombo", 20277597));
            db.addData(new Data("Romania", "Bucharest", 20121641));
            db.addData(new Data("Burkina Faso", "Ouagadougou", 17322796));
            db.addData(new Data("Kazakhstan", "Astana", 17186000));
            db.addData(new Data("Niger", "Niamey", 17129076));
            db.addData(new Data("Netherlands", "Amsterdam", 16841500));
            db.addData(new Data("Malawi", "Lilongwe", 16363000));
            db.addData(new Data("Chile", "Santiago", 16341929));
            db.addData(new Data("Ecuador", "Quito", 15695700));
            db.addData(new Data("Guatemala", "Guatemala City", 15438384));
            db.addData(new Data("Mali", "Bamako", 15302000));
            db.addData(new Data("Cambodia", "Phnom Penh", 15135000));
            db.addData(new Data("Zambia", "Lusaka", 14580290));
            db.addData(new Data("Senegal", "Dakar", 13567338));
            db.addData(new Data("Zimbabwe", "Harare", 12973808));
            db.addData(new Data("Chad", "N'Djamena", 12825000));
            db.addData(new Data("South Sudan", "Juba", 11296000));
            db.addData(new Data("Cuba", "Havana", 11167325));
            db.addData(new Data("Belgium", "Brussels", 11132269));
            db.addData(new Data("Tunisia", "Tunis", 10886500));
            db.addData(new Data("Guinea", "Conakry", 10824200));
            db.addData(new Data("Greece", "Athens", 10815197));
            db.addData(new Data("Rwanda", "Conakry", 10537222));
            db.addData(new Data("Czech Republic", "Prague", 10513800));
            db.addData(new Data("Somalia", "Mogadishu", 10496000));
            db.addData(new Data("Portugal", "Lisbon", 10487289));
            db.addData(new Data("Haiti", "Port-au-Prince", 10413211));
            db.addData(new Data("Benin", "Porto-Novo", 10323000));
            db.addData(new Data("Burundi", "Bujumbura", 10163000));
            db.addData(new Data("Bolivia", "La Paz", 10027254));
            db.addData(new Data("Hungary", "Budapest", 9906000));
            db.addData(new Data("Sweden", "Stockholm", 9651531));
            db.addData(new Data("Azerbaijan", "Baku", 9477100));
            db.addData(new Data("Belarus", "Minsk", 9468100));
            db.addData(new Data("Dominican Republic", "Santo Domingo", 9445281));
            db.addData(new Data("Honduras", "Tegucigalpa", 8555072));
            db.addData(new Data("Austria", " Vienna", 8504850));
            db.addData(new Data("United Arab Emirates", "Abu Dhabi", 8264070));
            db.addData(new Data("Tajikistan", "Dushanbe", 8160000));
            db.addData(new Data("Israel", "Jerusalem", 8146300));
            db.addData(new Data("Switzerland", "Bern", 8112200));
            db.addData(new Data("Papua New Guinea", "Port Moresby", 7398500));
            db.addData(new Data("Bulgaria", "Sofia", 7282041));

        }

    }
	
}
