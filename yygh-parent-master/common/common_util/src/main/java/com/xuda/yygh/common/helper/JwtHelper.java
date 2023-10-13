package com.xuda.yygh.common.helper;

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import java.util.Date;

public class JwtHelper {

    //过期时间
    private static long tokenExpiration = 24*60*60*1000;
    //签名秘钥
    private static String tokenSignKey = "123456";

    /**
     * 根据参数生成token
     * @param userId 用户
     * @param userName
     * @return
     */
    public static String createToken(Long userId, String userName) {
        String token = Jwts.builder()
                .setSubject("YYGH-USER")  //分类
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration)) //设置过期时间
                .claim("userId", userId)
                .claim("userName", userName) //主题信息
                .signWith(SignatureAlgorithm.HS512, tokenSignKey) //签名哈希
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }

    /**
     * 根据token字符串得到用户id
     * @param token
     * @return
     */
    public static Long getUserId(String token) {
        if(StringUtils.isEmpty(token)) return null;

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token); //解析得到需要的值
        Claims claims = claimsJws.getBody();
        Integer userId = (Integer)claims.get("userId");
        return userId.longValue();
    }


    /**
     * 根据token字符串得到用户名称
     * @param token
     * @return
     */
    public static String getUserName(String token) {
        if(StringUtils.isEmpty(token)) return "";

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (String)claims.get("userName");
    }
    public static void main(String[] args) {
        String token = JwtHelper.createToken(1L, "lucy"); //测试生成工具
        System.out.println(token);
        System.out.println(JwtHelper.getUserId(token));
        System.out.println(JwtHelper.getUserName(token));
    }
}

