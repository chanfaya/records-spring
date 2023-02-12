package com.records.security.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.*;

/**
 * JwtToken生成的工具类
 * JWT token的格式：header.payload.signature
 * header的格式（算法、token的类型）：
 * {"alg": "HS512","typ": "JWT"}
 * payload的格式（用户名、创建时间、生成时间）：
 * {"sub":"wang","created":1489079981393,"exp":1489684781}
 * signature的生成算法：
 * HMACSHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
 *
 * @date 2023/2/12 */
public class JwtTokenUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    /**
     * ip地址常量
     */
    private static final String IP = "ip";

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * token前缀
     */
    @Value("jwt.tokenPrefix")
    public static String tokenPrefix;

    /**
     * 根据负责生成JWT的token
     */
    private String generateToken(Map<String, Object> claims) throws JsonProcessingException {
        String tmp ="";
        for (int i = 0; i < 3; i++) {
            secret = Base64.getEncoder().encodeToString(secret.getBytes());
        }
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        Key secretKey = Keys.hmacShaKeyFor(keyBytes);
        ObjectMapper objectMapper = new ObjectMapper();
//        LOGGER.info("签名{}", objectMapper.writeValueAsString(secretKey));
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(secret)
                .compact();
    }

    /**
     * 从token中获取JWT中的负载
     */
    private Claims getClaimsFromToken(String token) {

        for (int i = 0; i < 3; i++) {
            secret = Base64.getEncoder().encodeToString(secret.getBytes());
        }
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        Key secretKey = Keys.hmacShaKeyFor(keyBytes);
        // 解析Token
        Claims claims = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
//            LOGGER.info("签名{}", objectMapper.writeValueAsString(secretKey));
            claims = (Claims) Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parse(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            claims = e.getClaims();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    /**
     * 生成token的过期时间
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 从token中获取登录用户名
     */
    public String getUserNameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 验证token是否还有效
     *
     * @param token       客户端传入的token
     * @param userDetails 从数据库中查询出来的用户信息
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 判断token是否已经失效
     */
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 根据用户信息生成token
     */
    public String generateToken(UserDetails userDetails, String ip) throws JsonProcessingException {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        claims.put(IP, ip);
        return generateToken(claims);
    }

    /**
     * 当原来的token没过期时是可以刷新的
     *
     * @param oldToken 带tokenHead的token
     */
    public String refreshHeadToken(String oldToken) throws JsonProcessingException {
        if(oldToken == null || oldToken.length() <= 0){
            return null;
        }
        String token = oldToken.substring(tokenHead.length());
        if(token == null || token.length() <= 0){
            return null;
        }
        //token校验不通过
        Claims claims = getClaimsFromToken(token);
        if(claims==null){
            return null;
        }
        //如果token已经过期，不支持刷新
        if(isTokenExpired(token)){
            return null;
        }
        //如果token在30分钟之内刚刷新过，返回原token
        if(tokenRefreshJustBefore(token,30*60)){
            return token;
        }else{
            claims.put(CLAIM_KEY_CREATED, new Date());
            return generateToken(claims);
        }
    }

    /**
     * 判断token在指定时间内是否刚刚刷新过
     * @param token 原token
     * @param time 指定时间（秒）
     */
    private boolean tokenRefreshJustBefore(String token, int time) {
        Claims claims = getClaimsFromToken(token);
        Date created = claims.get(CLAIM_KEY_CREATED, Date.class);
        Date refreshDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(created);
        calendar.add(Calendar.SECOND, time);
        //刷新时间在创建时间的指定时间内
        if(refreshDate.after(created)&&refreshDate.before(calendar.getTime())){
            return true;
        }
        return false;
    }
}
