package com.amazon.market.model;

import java.util.Date;


public class LocationzonesInfo {

	private int id;
	private String locationName;
	private int zoneid;
	private Date lastupdated;

	public LocationzonesInfo() {
	}

	public LocationzonesInfo(int id, String locationName, int zoneid) {
		this.id = id;
		this.locationName = locationName;
		this.zoneid = zoneid;
	}

	public LocationzonesInfo(int id, String locationName, int zoneid, Date lastupdated) {
		this.id = id;
		this.locationName = locationName;
		this.zoneid = zoneid;
		this.lastupdated = lastupdated;
	}

	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getLocationName() {
		return this.locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	
	public int getZoneid() {
		return this.zoneid;
	}

	public void setZoneid(int zoneid) {
		this.zoneid = zoneid;
	}

	
	public Date getLastupdated() {
		return this.lastupdated;
	}

	public void setLastupdated(Date lastupdated) {
		this.lastupdated = lastupdated;
	}
	
	
}
