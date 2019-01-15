package com.fct.api.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fct.api.dto.CommonApiResult;
import com.fct.api.dto.TokenDTO;
import com.fct.api.request.LoginRequest;
import com.fct.api.util.JwtTokenUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户相关的请求
 *
 * @author xstarfct
 * @version 2019/1/11 4:02 PM
 */
@RestController
@RequestMapping("/ums/")
@Api(description = "用户相关的请求")
public class UmsUserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsUserController.class);

    @Value("${api.jwt.tokenHeader}")
    private String                tokenHeader;
    @Value("${api.jwt.tokenHead}")
    private String                tokenHead;
    @Resource
    private JwtTokenUtil          jwtTokenUtil;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private UserDetailsService    userDetailsService;
    @Resource
    private PasswordEncoder       passwordEncoder;

    @ApiOperation("用户登陆")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonApiResult login(@RequestBody LoginRequest request) {
        return new CommonApiResult().success(new TokenDTO(login(request.getUsername(), request.getPassword()), tokenHeader));
    }

    private String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetailsService.loadUserByUsername(username));
        } catch (AuthenticationException e) {
            LOGGER.error("登录异常:{} {}", username, password, e);
        }
        return token;
    }

}
