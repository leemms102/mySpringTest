package com.example.test;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class PostController {
    @RequestMapping(value = "/rest", method = RequestMethod.GET)
    public String getTestValue() {
        return "테스트";
    }

    @PostMapping(value = "/post")
    public String postMember(@RequestBody Map<String, Object> postData){
            StringBuilder sb = new StringBuilder();

            postData.entrySet().forEach(map ->{
                sb.append(map.getKey() + ":" + map.getValue() + "\n");

            });
            return sb.toString();
    }

    @PutMapping (value = "/put")
    public String putMember(@RequestBody Map<String, Object> postData){
        StringBuilder sb = new StringBuilder();

        postData.entrySet().forEach(map ->{
            sb.append(map.getKey() + ":" + map.getValue() + "\n");

        });
        return sb.toString();
    }

    @PutMapping(value = "/putdto")
    public MemberVO putMemberDTO(@RequestBody MemberVO memberDto){
            return memberDto;
    }

    @DeleteMapping(value = "/delrequest")
    public String getRequestParam(@RequestParam String email){
        return "email" + email;
    }
}
