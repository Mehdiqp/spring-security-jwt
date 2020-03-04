package com.company.security.jwt.utils;


import com.company.security.jwt.constants.SecurityConstant;
import com.company.security.jwt.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import sun.security.util.SecurityConstants;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUtils {

    private static byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SecurityConstant.JWT_KEY_SECRET);
    private static SecretKey secretKey = Keys.hmacShaKeyFor(apiKeySecretBytes);

    public static String createToken(String username, List<Role> roles, boolean rememberMe) {
        long expairation = rememberMe ? SecurityConstant.EXPAIRTION_REMEMBER : SecurityConstant.EXPIRATION;
        String token = Jwts.builder()
                .setHeaderParam("type", SecurityConstant.TOKEN_TYPE)
                .signWith(secretKey, SignatureAlgorithm.ES256)
                .setIssuer("khodemon")
                .setIssuedAt(new Date())
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() * expairation * 60))
                .compact();
        return SecurityConstant.TOKEN_PREFIX + token;
    }


    private boolean isTokenExpired(String token) {
        Date expiredDate = getTokenBody(token).getExpiration();
        return expiredDate.before(new Date());
    }

    public static String getUsernameByToken(String token) {
        return getTokenBody(token).getSubject();
    }

    /**
     * Get all user roles
     */
    public static List<SimpleGrantedAuthority> getUserRolesByToken(String token) {
        List<Role> role = (List<Role>) getTokenBody(token)
                .get(SecurityConstant.ROLE_CLIMS);
        List<SimpleGrantedAuthority> authorities =
                role.stream().map(rol -> new SimpleGrantedAuthority(rol.getRoleName())).collect(Collectors.toList());
        return authorities;
    }

    private static Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
