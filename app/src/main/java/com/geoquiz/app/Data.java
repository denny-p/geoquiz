/*
Copyright (c) 2052 Denny Placzek
All rights reserved. This program and the accompanying materials
are made available under the terms of the Eclipse Public License v1.0
which accompanies this distribution, and is available at
http://www.eclipse.org/legal/epl-v10.html

Contributors:
    Denny Placzek - initial API and implementation
*/

package com.geoquiz.app;

public class Data {
	
	private int _id;
	private String _country;
	private String _capital;
	private int _population;
	
	public Data(){
		
	}
	
	public Data(String _country, String _capital, int _population) {
		super();
		this._country = _country;
		this._capital = _capital;
		this._population = _population;
	}
	
	

	public int getId() {
		return _id;
	}

	public void setId(int _id) {
		this._id = _id;
	}

	public String getCountry() {
		return _country;
	}

	public void setCountry(String _country) {
		this._country = _country;
	}

	public String getCapital() {
		return _capital;
	}

	public void setCapital(String _capital) {
		this._capital = _capital;
	}

	public int getPopulation() {
		return _population;
	}

	public void setPopulation(int _population) {
		this._population = _population;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Data [country=" + _country + ", capital=" + _capital
                + ", population=" + _population + "]";
	}

	
}
