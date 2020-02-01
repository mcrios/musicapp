package org.proyectosfgk.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SimpleGrantedAuthoritiesMixin {

	@JsonCreator
	public SimpleGrantedAuthoritiesMixin(@JsonProperty("authority") String role) {}
}
