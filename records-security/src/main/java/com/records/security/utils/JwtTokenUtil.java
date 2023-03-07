package com.records.security.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
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

    //准备两常量
    private static final String CLAIM_KEY_USERNAME="sub";
    private static final String CLAIM_KEY_CREATED="created";
    //@Value可以从配置目录里面拿静态值
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 第一个工具类功能
     * 根据用户信息生成token
     * 用户信息根据Spring security框架中的UserDetail中拿
     */
    public String generateToken(UserDetails userDetails){
        //准备一个空荷载claims，用于存储生成的key和value键值对（下面是存储生成token的时间和用户名）
        Map<String,Object> claims=new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME,userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }

    /**
     * 根据荷载生成token
     * 主要是通过Jwts把荷载、失效时间、以及密钥加密生成token
     * @param claims
     * @return
     */
    private String generateToken(Map<String,Object> claims){
        //有了荷载claims就可以通过Jwts生成token,方式如下：
        // 使用BASE64秘钥对JWT签名
        for (int i = 0; i < 3; i++) {
            secret = Base64.getEncoder().encodeToString(secret.getBytes());
        }
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        Key secretKey = Keys.hmacShaKeyFor(keyBytes);
        return Jwts.builder()
                .setClaims(claims)//把荷载存储到里面
                .setExpiration(generateExpirationDate())//设置失效时间
                .signWith(secretKey) //签名
                .compact();
    }


    /**
     * 生成token失效时间
     * @return
     */
    private Date generateExpirationDate() {
        //失效时间是当前系统的时间+我们在配置文件里定义的时间
        return new Date(System.currentTimeMillis()+expiration);
    }

    /**
     * 根据token获取用户名
     * @param token
     * @return
     */
    public String getUserNameFormToken(String token){
        String username;//用户名是通过在token中获取到荷载claims，然后再从荷载中取用户名
        try{
            Claims claims=getClaimsFormToken(token);
            username=claims.getSubject();
        }catch (Exception e){
            username=null;
        }
        return username;
    }

    /**
     * 从token中获取荷载
     * 获取荷载是通过jwts，然后船两参数，分别是secret、和token
     * @param token
     * @return
     */
    private Claims getClaimsFormToken(String token) {
        Claims claims=null;
        try{
            claims=Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            e.printStackTrace();
        }
        return claims;
    }

    /**
     * 验证token是否有效
     * 主要通过token中的用户名和userDetail中的用户名是否一致，并且，token是否已经失效
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token,UserDetails userDetails){
        String username=getUserNameFormToken(token);
        return username.equals(userDetails.getUsername())&&!isTokenExpired(token);
    }

    /**
     * 判断token是否已经失效
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        //先获取之前设置的token的失效时间
        Date expireDate=getExpiredDateFormToken(token);
        return expireDate.before(new Date()); //判断下，当前时间是都已经在expireDate之后
    }

    /**
     * 根据token获取失效时间
     * 也是先从token中获取荷载
     * 然后从荷载中拿到到设置的失效时间
     * @param token
     * @return
     */
    private Date getExpiredDateFormToken(String token) {
        Claims claims=getClaimsFormToken(token);
        return claims.getExpiration();
    }

    /**
     * 判断toke是否可以被刷新
     * 如果过期则可以刷新
     * @param token
     * @return
     */
    public boolean canRefresh(String token){
        return !isTokenExpired(token);
    }

    /**
     * 刷新我们的token
     * @param token
     * @return
     */
    public String refreshToken(String token){
        Claims claims=getClaimsFormToken(token);
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }

}
