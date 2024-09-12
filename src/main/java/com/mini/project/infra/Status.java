package com.mini.project.infra;

public enum Status {
	Success("Success"),
	FAILED("FAILED");
	
	private final String type;
	
	Status(String type) {
		this.type = type;
	}
}
