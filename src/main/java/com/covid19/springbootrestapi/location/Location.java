package com.covid19.springbootrestapi.location;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LOCATION_DETAILS")
public class Location {

	@Id 
    @Column(name="userid")
	private String userID;
	@Column(name="longtitude")
	private String longtitude;
	@Column(name="latitude")
	private String latitude;
	@Column(name="createdt")
	private String createDt;
	private boolean findCrowd;
	
	@Override
	public String toString() {
		return "Location [ userId=" + userID + ", longtitude=" + longtitude + ", latitude=" + latitude
				+ ", createDt=" + createDt + "]";
	}
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getCreateDt() {
		return createDt;
	}
	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}

	public boolean isFindCrowd() {
		return findCrowd;
	}

	public void setFindCrowd(boolean findCrowd) {
		this.findCrowd = findCrowd;
	}

	
	
	
}
