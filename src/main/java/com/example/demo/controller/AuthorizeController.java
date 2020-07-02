package com.example.demo.controller;

import com.example.demo.dto.AccessTokenDTO;
import com.example.demo.dto.GithubUserDTO;
import com.example.demo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO(
                "13ae99393ef115f2ee4c",
                "df71d63fb8f63944377e77be9b8d10c2b5833730",
                code,
                "http://localhost:8887/callback",
                state);
        String accessToken = this.githubProvider.getAccessToken(accessTokenDTO);
        GithubUserDTO user = this.githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}