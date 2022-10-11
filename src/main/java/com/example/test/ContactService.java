package com.example.test;
// 서비스 interface
import java.util.List;
import java.util.Map;

public interface ContactService {
    String create(Map<String, Object> map);

    String create(MemberVO vo);

    List<Map<String, Object>> member_list(Map<String, Object> map);

    Map<String, Object> member_detail(Map<String, Object> map);

    MemberVO member_detail(int id);

    int update(Map<String, Object> map);

    int update(MemberVO vo);

    int delete(Map<String, Object> map);

    int delete(MemberVO vo);
}
