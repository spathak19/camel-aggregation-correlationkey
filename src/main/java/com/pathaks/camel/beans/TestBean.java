package com.pathaks.camel.beans;

import org.springframework.stereotype.Component;

@Component
public class TestBean {

	public String twoChars(String input) {
		return input.substring(0, 2);
	}

	public String append(String existing, String next) {
		return existing + next;
	}
}