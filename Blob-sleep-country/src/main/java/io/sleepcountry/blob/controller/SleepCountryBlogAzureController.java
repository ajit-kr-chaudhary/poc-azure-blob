package io.sleepcountry.blob.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.sleepcountry.blob.service.SleepCountryBlogAzureService;

@RestController
public class SleepCountryBlogAzureController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SleepCountryBlogAzureController.class);

	@Autowired
	private SleepCountryBlogAzureService sleepCountryBlogAzureService;
	
	@GetMapping(path = "/getStorageConnection", produces = "application/json")
	public List<String> getStorageConnection() {
		LOGGER.info(">> getStorageConnection");
		return sleepCountryBlogAzureService.getStorageConnection();
	}
	
	@GetMapping(path = "/downloadFileFromBlobs", produces = "application/json")
	public String downloadFileFromBlobs(@RequestParam String fileName) {
		return sleepCountryBlogAzureService.downloadFileFromBlobs(fileName);
	}

}
