package com.dglee.apps.api;

import com.dglee.apps.dto.UserDTO;
import com.dglee.apps.entity.User;
import com.dglee.apps.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<User> userAdd(HttpServletRequest request, @Valid @RequestBody UserDTO.UserAdd dto) {

        if(userService.isExistUserByEmailId(dto.getEmailId())) {
            log.error("user add failed. duplicate this email. email: {}", dto.getEmailId());
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

        User user = userService.save(dto);
        if(user == null) {
            log.error("user add failed. cause save action failed. email: {}", dto.getEmailId());
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
