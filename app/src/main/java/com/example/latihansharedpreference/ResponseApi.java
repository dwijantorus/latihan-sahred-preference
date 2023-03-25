package com.example.latihansharedpreference;

import com.google.gson.annotations.SerializedName;

public class ResponseApi{

	@SerializedName("status")
	private boolean status;

	@SerializedName("message")
	private String message;

	@SerializedName("data")
	private User user;

	public boolean isStatus(){
		return status;
	}

	public String getMessage(){
		return message;
	}

	public User getUser(){
		return user;
	}
}