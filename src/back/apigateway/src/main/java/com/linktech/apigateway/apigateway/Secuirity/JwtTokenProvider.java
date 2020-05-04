package com.linktech.apigateway.apigateway.Secuirity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import com.linktech.apigateway.apigateway.Models.MongoUserDetails;
import com.linktech.apigateway.apigateway.Repositories.JwtTokenRepository;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {
    private static final String AUTH="auth";
    private static final String AUTHORIZATION="Authorization";
    private String secretKey="secret-key";
    private final long validityInMilliseconds = 3600000; // 1h

    @Autowired
    private JwtTokenRepository jwtTokenRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(final String username, final List<String> roles) {

        final Claims claims = Jwts.claims().setSubject(username);
        claims.put(AUTH,roles);

        final Date now = new Date();
        final Date validity = new Date(now.getTime() + validityInMilliseconds);

        final String token =  Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .setExpiration(validity)//
                .signWith(SignatureAlgorithm.HS256, secretKey)//
                .compact();
        jwtTokenRepository.save(new JwtToken(token));
        return token;
    }

    public String resolveToken(final HttpServletRequest req) {
        final String bearerToken = req.getHeader(AUTHORIZATION);
        /*if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }*/
        if (bearerToken != null ) {
            return bearerToken;
        }
        return null;
    }

    public boolean validateToken(final String token) throws JwtException,IllegalArgumentException{
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
    }
    public boolean isTokenPresentInDB (final String token) {
        return jwtTokenRepository.findById(token).isPresent();
    }
    //user details with out database hit
    public UserDetails getUserDetails(final String token) {
        final String userName =  getUsername(token);
        final List<String> roleList = getRoleList(token);
        final UserDetails userDetails = new MongoUserDetails(userName,roleList.toArray(new String[roleList.size()]));
        return userDetails;
    }
    public List<String> getRoleList(final String token) {
        return (List<String>) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).
                getBody().get(AUTH);
    }

    public String getUsername(final String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }
    public Authentication getAuthentication(final String token) {
        //using data base: uncomment when you want to fetch data from data base
        //UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
        //from token take user value. comment below line for changing it taking from data base
        final UserDetails userDetails = getUserDetails(token);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

}