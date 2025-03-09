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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class Splash extends Activity{

	
	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		setContentView(R.layout.splash);
			
		Thread timer = new Thread(){
			public void run(){
				try {
					sleep(3000);
					
				} catch (InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent openStartingPoint = new Intent("com.geoquiz.app.MENU");
					startActivity(openStartingPoint);
				}
			}
	};
	timer.start();
  }

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}
