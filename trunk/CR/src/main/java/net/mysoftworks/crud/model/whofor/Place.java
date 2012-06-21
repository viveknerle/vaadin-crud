package net.mysoftworks.crud.model.whofor;

import javax.persistence.Entity;

import net.mysoftworks.crud.model.common.PrimaryKey;

@Entity
public class Place extends PrimaryKey {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String description;
	
	private double lat,lon; // EAST,NORTH
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	
}
