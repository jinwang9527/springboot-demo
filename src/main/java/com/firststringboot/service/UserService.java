package com.firststringboot.service;

import com.firststringboot.repository.domain.User;

public interface UserService extends BaseService {

    User getUser();


    String addUser()throws Exception;

    String testToken();
}
