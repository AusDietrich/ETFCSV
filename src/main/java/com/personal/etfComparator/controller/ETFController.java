package com.personal.etfComparator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.etfComparator.svc.SvcLayer;

@RestController
public class ETFController {

	@Autowired
	SvcLayer svcLayer;
	
	@RequestMapping("/")
	@Scheduled(cron = "0 0 17 * * MON-FRI") //set to run every weekday at 5pm
	public void etfTrigger() {
		System.out.println("go");
		svcLayer.compareatorStart();
	}
}
