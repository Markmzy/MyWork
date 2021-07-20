package com.markmzy.mywork.wx.config.shiro;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @title: JwtUtil
 * @Author Zhiyue Ma
 * @Date: 7/19/21 09:19
 * @Version 1.0
 */
@Component
public class JwtUtil
{
    @Value("${mywork.jwt.secret}")
    private String secret;

    @Value("${mywork.jwt.expire}")
    private int expire;

    public String createToken(int userId)
    {
        Date date = DateUtil.offset(new Date(), DateField.DAY_OF_YEAR, expire); //TODO: 5
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTCreator.Builder builder = JWT.create();
        String token = builder.withClaim("userId", userId).withExpiresAt(date).sign(algorithm);
        return token;
    }

    public int getUserId(String token)
    {
        DecodedJWT jwt = JWT.decode(token);
        int userId = jwt.getClaim("userId").asInt();
        return userId;
    }

    public void verifierToken(String token)
    {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm).build();
        verifier.verify(token);
    }

}
