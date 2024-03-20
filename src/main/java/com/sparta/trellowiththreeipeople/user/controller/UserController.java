package com.sparta.trellowiththreeipeople.user.controller;

import com.sparta.trellowiththreeipeople.security.UserDetailsImpl;
import com.sparta.trellowiththreeipeople.user.dto.request.CreateUserRequestDto;
import com.sparta.trellowiththreeipeople.user.dto.request.UpdateUserRequestDto;
import com.sparta.trellowiththreeipeople.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public void createUser(
            @RequestBody @Valid CreateUserRequestDto createUserRequestDto
    ) throws BadRequestException {
        userService.createUser(createUserRequestDto);
    }

    @PutMapping
    public void updateUser(
            @RequestBody @Valid UpdateUserRequestDto updateUserRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) throws BadRequestException {
        userService.updateUser(updateUserRequestDto, userDetails.getUser());
    }
}
