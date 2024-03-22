package com.sparta.trellowiththreeipeople.user.controller;

import com.sparta.trellowiththreeipeople.security.UserDetailsImpl;
import com.sparta.trellowiththreeipeople.user.dto.request.CreateUserRequestDto;
import com.sparta.trellowiththreeipeople.user.dto.request.UpdateUserRequestDto;
import com.sparta.trellowiththreeipeople.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity createUser(
        @RequestBody @Valid CreateUserRequestDto createUserRequestDto
    ) throws BadRequestException {
        userService.createUser(createUserRequestDto);
        return ResponseEntity.ok().body("회원가입이 정상적으로 완료되었습니다.");
    }

    @PutMapping
    public ResponseEntity updateUser(
        @RequestBody @Valid UpdateUserRequestDto updateUserRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) throws BadRequestException {
        userService.updateUser(updateUserRequestDto, userDetails.getUser());
        return ResponseEntity.ok().body("회원가입이 정상적으로 완료되었습니다.");
    }
}
