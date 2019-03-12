package com.forecast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="Temperature Forecast", description=" Controller")
public class TestController {
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@ApiOperation("This is test Controller. Project has AWS RDS instance connection."
			+ "USERNAME and PASSWORD is 'hrishikesh' ")
	@RequestMapping(value ="/HelloWorldController", method = RequestMethod.GET)
	public String greeting() {
		logger.info("logger working");
		
		return "hello bro";
		
		
	}
	
	
	
	@ApiOperation("Validates the user based on header parameters")
	@RequestMapping(value ="/ValidateUser", method = RequestMethod.GET)
	public boolean ValidateUSer() {
		logger.info("logger working");
		
		boolean flag=DBUtils.ValidateUser("hrishikesh","hrishikesh");
		
		return flag;
		
		
	}
	
	
	@ApiOperation("Gets a list of cities with MAX temperatre for whole day... "
			+ "All requests and responses are tracked in API_TRACKER table with "
			+ "USERNAME and PASSWORD is 'hrishikesh' ")
	@RequestMapping(value ="/HighestTemperatureForecast", method = RequestMethod.GET)
	public @ResponseBody ResponsePOJONew HighestTemperatureForecast(HttpServletRequest request,HttpServletResponse response,@RequestHeader("username") String username,@RequestHeader("password") String password) {
		ResponsePOJONew resp=new ResponsePOJONew();
		
		logger.info(username+password);
		boolean user_flag=DBUtils.ValidateUser(username, password);
		if(!user_flag) {
			
			resp.setRtcode(401);
			resp.setMessage("Not authorised user");
			return resp;
			
		}
		
		
		
		
		int transaction_id=DBUtils.getLastTransaction();
		logger.info("last_transaction"+transaction_id);
		
		transaction_id++;
		
		
		int check_insert=DBUtils.insertRequest(transaction_id, username,"NORMAL", 0, 0);
		
		if(check_insert==1) {
			
			logger.info("request inserted and tracked");
		}else {
			logger.info("request faild to track");
		}
		
		
		
		
		int max=DBUtils.getMaxTemperature();
		logger.info("MAX temperature : "+max);
		resp.setTemperature(max);
		
		List<TemperaturePOJO> list=DBUtils.getRecords(max);
		/*
		List<String> city=new ArrayList<String>();
		
		for(int i=0;i<list.size();i++) {
			
			System.out.println(list.get(i).getCity());
			String namer=list.get(i).getCity();
			city.add(namer);
			
			
		}
		*/
		resp.setCitynames(list);
		
		resp.setRtcode(200);
		resp.setMessage("cities with max temperature : "+max+" for the whole day successfully retrieved");
		
		
		
		
		
		int after_insert=DBUtils.insertResponse(transaction_id, resp.toString());
		
		if(after_insert==1) {
			
			logger.info("response inserted and tracked");
		}else {
			logger.info("response faild to track");
		}
		
		
		
		
		
		return resp;
	}
	
	
	
	
	@ApiOperation("Gets a Filtered List of cities with max temperature between specific timestamp. Between"
			+ "lower bound timestamp and upper bound timestamp.  Next day is split in terms of the number of hours."
			+ "1-24 so specify filters accordingly to fetch data within timestamp."
			+ "All requests and responses are tracked in API_TRACKER table with "
			+ "USERNAME and PASSWORD is 'hrishikesh'  and specify time filters between 1-24 " )
	@RequestMapping(value ="/HighestTemperatureFilteredForecast", method = RequestMethod.GET)
	public @ResponseBody ResponsePOJONew HighestTemperatureFilteredForecast(HttpServletRequest request,HttpServletResponse response,@RequestHeader("username") String username,@RequestHeader("password") String password,@RequestHeader("lower_bound_timestamp") int lower,@RequestHeader("upper_bound_timestamp") int upper) {
		ResponsePOJONew resp=new ResponsePOJONew();
		
		logger.info(username+password);
		boolean user_flag=DBUtils.ValidateUser(username, password);
		if(!user_flag) {
			
			resp.setRtcode(401);
			resp.setMessage("Not authorised user");
			return resp;
			
		}
		
		
		
		
		int transaction_id=DBUtils.getLastTransaction();
		logger.info("last_transaction"+transaction_id);
		
		transaction_id++;
		
		
		int check_insert=DBUtils.insertRequest(transaction_id, username,"FILTERED",lower,upper);
		
		if(check_insert==1) {
			
			logger.info("request inserted and tracked");
		}else {
			logger.info("request faild to track");
		}
		
		
		
		
		int max=DBUtils.getMaxTemperatureFiltered(lower, upper);
		logger.info("MAX temperature  Filtered is: "+max);
		resp.setTemperature(max);
		
		List<TemperaturePOJO> list=DBUtils.getRecordsFiltered(max, lower, upper);
		/*
		List<String> city=new ArrayList<String>();
		
		for(int i=0;i<list.size();i++) {
			
			System.out.println(list.get(i).getCity());
			String namer=list.get(i).getCity();
			city.add(namer);
			
			
		}
		*/
		resp.setCitynames(list);
		
		resp.setRtcode(200);
		resp.setMessage("cities Filtered with max temperature : "+max+" between hours "+lower+" to "+upper+" successfully retrieved");
		
		
		
		
		
		int after_insert=DBUtils.insertResponse(transaction_id, resp.toString());
		
		if(after_insert==1) {
			
			logger.info("response inserted and tracked");
		}else {
			logger.info("response faild to track");
		}
		
		
		
		
		
		return resp;
	}

	

}
