package com.amazon.market.dao;

import java.util.List;

import com.amazon.market.entity.LocationZones;
import com.amazon.market.model.LocationzonesInfo;


public interface LocationZonesDAO {
 
    public LocationZones getLocationZone(int id);
    public List<LocationzonesInfo> listLocationZoneInfos();
    public List<LocationzonesInfo> listLocationsInfosByZone(int uid);
    public int listLocationidByname(String loc);
    
}