package com.covid19.springbootrestapi.location;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {

	private static Logger log = LoggerFactory.getLogger("com.covid19.springbootrestapi.location.LocationController");  
	@Autowired
	LocationRepository locationRepository;
	
	
	@GetMapping("/getLocation")
	public List<Location> getLocationDetails(){
		log.info("inside getlocatoin details;:");
		List<Location> locationList = new ArrayList<>();
		 locationRepository.findAll().forEach(locationList::add);
		return locationList;
	}
	@PostMapping(value ="/location", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String>  insertLocationDetails(@RequestBody Location location) {
		log.info("inside insertLocationDetails details;:"+location.getLatitude()+":"+location.getUserID());
		Date date = new Date();
		
		String isCrowded =  null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		location.setCreateDt(sdf.format(date));
		log.info("details:"+location.isFindCrowd());
		if(location.isFindCrowd()) {
			isCrowded = getClusterDetails(location);
			log.info("crodwed::"+isCrowded);
			
		}else {
			locationRepository.save(location);
			isCrowded = "User Added/Updated Successfully";
		}
	      return ResponseEntity.ok(isCrowded);
	}
	
	
	public String  getClusterDetails( Location location){
		log.info("inside getclusterdetails starts::");
		 Date dNow = new Date( ); // Instantiate a Date object
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 int count =0;
		 String isCrowded =null;
		 int mm = dNow.getMinutes();
		 dNow.setMinutes(mm-1);
		 log.info("time stamp::"+sdf.format(dNow));
		 List<Location> locationList = new ArrayList<>();
		 locationRepository.findByCreateDt(sdf.format(dNow)).forEach(locationList::add);
		 log.info("size::"+locationList.size());
		 if(!locationList.isEmpty()) {
			 for(Location local:locationList) {
				 int x =40000 / 360;
				 long value = (int) (Math.cos(Math.PI *( (Float.parseFloat(location.getLatitude()))/180.0))*x);
				 log.info("value:"+value);
				 long longtitude =  (long) Math.abs((Float.parseFloat(location.getLongtitude())) - (Float.parseFloat(local.getLongtitude())))*x;
				 long latitude = (long) Math.abs((Float.parseFloat(location.getLatitude())) - (Float.parseFloat(local.getLatitude())))*x;
				 log.info("longtitude:"+longtitude+"::"+latitude);
				 boolean result = Math.sqrt((longtitude*longtitude)+(latitude*latitude)) <=0.005;
				 if(result) {
					 count ++;
				 }
			 }
		 }
		 log.info("count value;:"+count);
		 if(count>5) {
			 isCrowded ="RED";
		 }else if(count>2 && count<5){
			 isCrowded ="AMBER";
		 }else if(count<3) {
			 isCrowded = "GREEN";
		 }
		 return isCrowded;
	}
}
