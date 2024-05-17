package com.example.bmsbookticket.controllers;

import com.example.bmsbookticket.dtos.*;
import com.example.bmsbookticket.models.User;
import com.example.bmsbookticket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public SignupUserResponseDTO signupUser(SignupUserRequestDTO requestDTO){
        SignupUserResponseDTO responseDTO = new SignupUserResponseDTO();
        try {
            User user = userService.signupUser(requestDTO.getName(), requestDTO.getEmail(), requestDTO.getPassword());
            responseDTO.setUserId(user.getId());
            responseDTO.setEmail(requestDTO.getEmail());
            responseDTO.setName(requestDTO.getName());
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDTO;
    }

    public LoginResponseDto login(LoginRequestDto requestDto){
        LoginResponseDto responseDTO = new LoginResponseDto();
        try {
            boolean isLoggedIn = userService.login(requestDto.getEmail(), requestDto.getPassword());
            responseDTO.setLoggedIn(isLoggedIn);
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDTO;
    }

}
