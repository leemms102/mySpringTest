package com.example.account;

import java.util.Map;

public interface AccountService {
    String create(Map<String, Object> map);

    int check_id(String id);

    int update(Map<String, Object> map);

    int delete(Map<String, Object> map);


}
