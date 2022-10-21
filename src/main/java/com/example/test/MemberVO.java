package com.example.test;

public class MemberVO {

    private int id;
    private String name;
    private String phoneno;
    private String addr;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno){
        this.phoneno = phoneno;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr){
        this.addr = addr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    @Override
    public String toString(){
        return "MemberDTO{ " +
                name + phoneno + addr + email + "}";
    }

}
