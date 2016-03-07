/**
 * 
 */
package edu.faytechcc.web151.stateinformation;

import java.io.Serializable;

/**
 * @author bryanc7064
 *
 */
public class State implements Serializable {

	private String name, capital, bird, url;

	public State(String line) {
		throw new RuntimeException("Not implemented");
	}
	
	
	public State(String name, String url, String capital, String bird) {
		this.name = name;
		this.capital = capital;
		this.bird = bird;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getBird() {
		return bird;
	}

	public void setBird(String bird) {
		this.bird = bird;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
