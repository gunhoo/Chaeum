package com.tls.user.service;

import com.tls.jwt.TokenDto;
import com.tls.user.dto.UserProfileDto;
import com.tls.user.vo.UserPwdVO;
import com.tls.user.vo.UserSignUpVO;

public interface UserService {

    int signUp(UserSignUpVO userDto);

    TokenDto signIn(String userEmail, String userPwd);

    int signOut(TokenDto tokenDto);

    int checkEmail(String userEmail);

    int updateUser(String userEmail, UserPwdVO userDto);

    int findUserPwd(String userEmail, String userBirthday);

    int sendEmailAuthCode(String userEmail);

    int checkEmailAuthCode(String userEmail, int code);

    int deleteUser(String userEmail);

    UserProfileDto readProfile(String userEmail);
}
