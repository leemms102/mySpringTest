package com.example.test;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.List;
@Repository
public class ContactDAO {
     @Autowired
     private SqlSessionTemplate sqlSession;
    public int insert(Map<String, Object> map)  {
        return this.sqlSession.insert("com.example.test.ContactDAO.insert", map);
    }

    public int insert(MemberVO vo) {
        return this.sqlSession.insert("com.example.test.ContactDAO.vo_insert", vo);
    }

    public List<Map<String, Object>> member_list(Map<String, Object> map){
        return this.sqlSession.selectList("com.example.test.ContactDAO.list", map);
    }

    public Map<String, Object> member_detail(Map<String, Object> map) {
        return this.sqlSession.selectOne("com.example.test.ContactDAO.detail", map);
    }

    public MemberVO member_detail(int id) {
        return this.sqlSession.selectOne("com.example.test.ContactDAO.vo_detail", id);
    }

    public int update(Map<String, Object> map) {
        return this.sqlSession.update("com.example.test.ContactDAO.update", map);
    }

    public int update(MemberVO vo) {
        return this.sqlSession.update("com.example.test.ContactDAO.vo_update", vo);
    }

    public int delete(Map<String, Object> map) {
        return this.sqlSession.delete("com.example.test.ContactDAO.delete", map);
    }

    public int delete(MemberVO vo) {
        return this.sqlSession.delete("com.example.test.ContactDAO.vo_delete", vo);
    }
}
