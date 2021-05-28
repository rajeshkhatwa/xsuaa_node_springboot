package com.rk.bulletin.RestControllers;
import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetTime {
	@GetMapping("/time")
	public String returnTime()
	{
	
		return " Current time is as discussed " + LocalDateTime.now();
		
	}
	

}
