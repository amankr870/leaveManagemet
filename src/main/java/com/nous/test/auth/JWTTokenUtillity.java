package com.nous.test.auth;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JWTTokenUtillity implements Serializable {
	
	private static final long serialVersionUID = -2550185165626007488L;

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	@Value("${jwt.secret}")
	private String secret;

	//get username from jwt token

	public String getUsernameFromToken(String token) {
		System.out.println("Secret Key = " + secret );

	return getClaimFromToken(token, Claims::getSubject);

	}

	//get expiration date from jwt token

	public Date getExpirationDateFromToken(String token) {

	return getClaimFromToken(token, Claims::getExpiration);

	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {

	final Claims claims = getAllClaimsFromToken(token);

	return claimsResolver.apply(claims);

	}

	    //for getting any information from token we will need the secret key

	private Claims getAllClaimsFromToken(String token) {

	return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

	}

	//check if the token has expired

	private Boolean isTokenExpired(String token) {

	final Date expiration = getExpirationDateFromToken(token);

	return expiration.before(new Date());

	}

	//generate token for user

	public String generateToken(UserDetails userDetails) {
		String roles = userDetails.getAuthorities().toArray()[0] +"";
	Map<String, Object> claims = new HashMap<>();
	claims.put("roles", roles);

	return doGenerateToken(claims, userDetails.getUsername());

	}



	private String doGenerateToken(Map<String, Object> claims, String subject) {

	return Jwts.builder()
			.setClaims(claims)
			.setSubject(subject)
			.setIssuedAt(new Date(System.currentTimeMillis()))

	.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 5000))

	.signWith(SignatureAlgorithm.HS512, secret).compact();

	}

	//token validation

	public Boolean validateToken(String token, UserDetails userDetails) {

		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			e.printStackTrace();
		} catch (MalformedJwtException e) {
			e.printStackTrace();
		} catch (ExpiredJwtException e) {
			e.printStackTrace();
		} catch (UnsupportedJwtException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		return false;
		
		
//		final String username = getUsernameFromToken(token);
//
//		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

	}

}
