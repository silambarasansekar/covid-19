package com.covid19.springbootrestapi.location;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, String>{

	@Query(value ="select * from LOCATION_DETAILS where createdt >?1", nativeQuery = true)
//	public List<Location> fetchLocationDetails(@Param("timeStamp") Date timeStamp);
	public List<Location> findByCreateDt( String timeStamp);	
}
