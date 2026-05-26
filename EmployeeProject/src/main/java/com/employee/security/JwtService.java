package com.employee.security;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private final String jwtSecret;
	private final long jwtExpirationMs;

	public JwtService(@Value("${app.jwt.secret}") String jwtSecret,
			@Value("${app.jwt.expiration-ms}") long jwtExpirationMs) {
		this.jwtSecret = jwtSecret;
		this.jwtExpirationMs = jwtExpirationMs;
	}

	public String generateToken(String username) {
		Instant now = Instant.now();

		return Jwts.builder()
				.subject(username)
				.issuedAt(Date.from(now))
				.expiration(Date.from(now.plusMillis(jwtExpirationMs)))
				.signWith(getSigningKey())
				.compact();
	}

	public boolean isTokenValid(String token, UserDetails userDetails) {
		String username = extractUsername(token);

		return username != null && username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

	public String extractUsername(String token) {
		return extractAllClaims(token).getSubject();
	}

	private boolean isTokenExpired(String token) {
		Date expiration = extractAllClaims(token).getExpiration();

		return expiration == null || expiration.before(Date.from(Instant.now()));
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser()
				.verifyWith(getSigningKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}

	private SecretKey getSigningKey() {
		return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
	}
}
