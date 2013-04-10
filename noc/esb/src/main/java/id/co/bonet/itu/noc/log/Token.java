package id.co.bonet.itu.noc.log;

import org.springframework.stereotype.Service;

@Service
public class Token {
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
