package com.ppmtoolfullstack.ppmt.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import com.ppmtoolfullstack.ppmt.Domain.User;
@Component
public class JwtTokenProvider {

	//Generate the token
	public String generateToken(Authentication authentication){
        User user = (User)authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());

        Date expiryDate = new Date(now.getTime()+SecurityConstants.EXPIRATION_TIME);

        String userId = Long.toString(user.getId());

        Map<String,Object> claims = new HashMap<>();
        claims.put("id", (Long.toString(user.getId())));
        claims.put("username", user.getUsername());
        claims.put("fullName", user.getFullname());

        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                .compact();
        }

	
	//Validate the token
	public boolean validateToken(String token) {
		
		try {
			Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token);		
			return true;
		} catch (SignatureException e) {
				System.out.println("Invailid JWT Signature");
		} catch (MalformedJwtException e) {
			System.out.println("Invailid JWT Token");
		} catch (ExpiredJwtException e) {
			System.out.println("Invailid JWT token expired");
		} catch (UnsupportedJwtException e) {
			System.out.println("Unsupported JWT Token");
		} catch (IllegalIdentifierException e) {
			System.out.println("JWT claims string is empty");
		}
		return false;
	}
	
	// Get User Id from token 
	public Long getUserIdFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token).getBody();
		String id = (String) claims.get("id");
		return Long.parseLong(id);
	}
	
	
}
