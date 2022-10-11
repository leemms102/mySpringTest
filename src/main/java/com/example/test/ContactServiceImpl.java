package com.example.test;
// 서비스 class

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    ContactDAO contactDao;
    @Override
    public String create(Map<String, Object> map) {
        int aRowcnt = this.contactDao.insert(map);

        if(aRowcnt == 1) {
            return map.get("m_id").toString();
        }
        else {
            System.out.println("등록 실패");
            return null;
        }
    }

    @Override
    public String create(MemberVO vo){
        int aRowcnt = this.contactDao.insert(vo);
        if(aRowcnt == 1) {
            return vo.getAddr();
        }
        else {
            System.out.println("등록 실패");
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> member_list(Map<String, Object> map){
        return this.contactDao.member_list(map);
    }

    @Override
    public Map<String, Object> member_detail(Map<String, Object> map) {

        return this.contactDao.member_detail(map);
    }

    @Override
    public MemberVO member_detail(int id) {
        return this.contactDao.member_detail(id);
    }

    @Override
    public int update(Map<String, Object> map) {
        int aRowcnt = this.contactDao.update(map);

        return aRowcnt;
    }

    @Override
    public int update(MemberVO vo) {
        int aRowcnt = this.contactDao.update(vo);

        return aRowcnt;
    }

    @Override
    public int delete(Map<String, Object> map) {
        int aRowCount = this.contactDao.delete(map);
        return aRowCount;
    }

    @Override
    public int delete(MemberVO vo) {
        int aRowCount = this.contactDao.delete(vo);
        return aRowCount;
    }

}
