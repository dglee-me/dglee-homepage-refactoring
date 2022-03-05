package com.dglee.apps.service;

import com.dglee.apps.dto.UserDTO;
import com.dglee.apps.entity.User;
import com.dglee.apps.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Service
public class UserService {

    final PasswordEncoder passwordEncoder;

    final UserRepository userRepository;

    /**
     * emailId가 존재하는지 확인
     *
     * @param emailId 이메일 주소 (localPart@domainPart)
     * @return
     */
    public boolean isExistUserByEmailId(String emailId) {
        return userRepository.existsUserByEmailId(emailId);
    }

    /**
     * 사용자 추가
     *
     * @param dto
     * @return
     */
    public User save(UserDTO.UserAdd dto) {

        // regist Date Initialize
        if(dto.getRegistDate() == 0) {
            dto.setRegistDate(System.currentTimeMillis());
        }

        // password encrypt
        dto.setPw(passwordEncoder.encode(dto.getPw()));

        return userRepository.save(dto.convert(User.class));
    }
}
