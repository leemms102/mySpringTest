package com.example.test;

import com.example.utils.FileUtil;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    ContactService contactService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/")
    public String view (Model model)
    {
        model.addAttribute("now", new SimpleDateFormat("yyyy년 MM월 dd일 a hh시 mm분 ss초").format(new Date()));
        return "index";
    }

    @RequestMapping("/view")
    public ModelAndView view() {
        //모델앤뷰 객체를 생성해준다.
        ModelAndView mav = new ModelAndView();
        mav.addObject("now",
                new SimpleDateFormat("yyyy년 MM월 dd일 a hh시 mm분 ss초").format(new Date()));

        //now가 index.jsp로 연결되게끔 지정
        mav.setViewName("index");

        return mav;
    }

    @RequestMapping("/jsp")
    public ModelAndView jsp() {
        ModelAndView model = new ModelAndView("main");
        model.addObject("testvalue", "안녕!");

        return model;
    }

    @RequestMapping("/new")
    public ModelAndView newPost() {
        ModelAndView model = new ModelAndView("new");
        return model;
    }

    @GetMapping("/create")
    public ModelAndView create() {

        return new ModelAndView("create");
    }

    // 등록
    @PostMapping( "/create")
    public ModelAndView create(@RequestParam Map<String, Object> map, MultipartHttpServletRequest request) {

        ModelAndView mv = new ModelAndView();

        MultipartFile file = request.getFile("uploadFile");
        if(file!= null) {
            if(file.getContentType() == "application/octet-stream" || file.getSize() == 0) {
                map.put("profile", null);
            }
            else {
                System.out.println("파일 이름 : " + file.getOriginalFilename());
                System.out.println("파일 타입 : " + file.getContentType());
                System.out.println("파일 크기 : " + file.getSize());
                String uploadPath = request.getSession().getServletContext().getRealPath("/");

                String profile = FileUtil.fileUpload(uploadPath + "/upload", file);
                map.put("profile", profile);
            }
        }

        String id = this.contactService.create(map);

        if (id == null) {
            mv.setViewName("redirect:/create");
        } else {
            mv.setViewName("redirect:/detail?m_id=" + id);
        }

        return mv;
    }


    @GetMapping("ajax-upload")
    public ModelAndView ajaxImage(){
        return new ModelAndView("ajax-upload");
    }

//    @ResponseBody
//    @PostMapping(value = "/ajax-upload-result")
//    public ModelAndView  fileUpload(@RequestParam("uploadFile") List<MultipartFile> multipartFile, HttpServletRequest request) {
//        String uploadPath = request.getSession().getServletContext().getRealPath("/");
//
//        List<String> fileList = new ArrayList<>();
//
//        // 파일 업로드
//        for(MultipartFile f : multipartFile) {
//            if(!f.isEmpty()) {
//                System.out.println("파일 이름 : " + f.getOriginalFilename());
//                System.out.println("파일 타입 : " + f.getContentType());
//                System.out.println("파일 크기 : " + f.getSize());
//
//                fileList.add(FileUtil.fileUpload(uploadPath + "/upload", f));
//            }
//        }
//
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("ajax-upload-result");
//        return mv;
//
//    }
    @ResponseBody
    @PostMapping(value = "/ajax-upload-result")
    public String  fileUpload(Model model, MultipartHttpServletRequest request) {
        String uploadPath = request.getSession().getServletContext().getRealPath("/");

        List<String> fileList = new ArrayList<>();

        // 파일 업로드

        for(MultipartFile f : request.getFiles("uploadFile")) {
            if(!f.isEmpty()) {
                System.out.println("파일 이름 : " + f.getOriginalFilename());
                System.out.println("파일 타입 : " + f.getContentType());
                System.out.println("파일 크기 : " + f.getSize());

                fileList.add(FileUtil.fileUpload(uploadPath + "/upload", f));
            }
        }

        model.addAttribute("log","사진 "+fileList.size()+"장 전송완료!");
        return "/ajax-upload :: #resultDiv";

    }



    @GetMapping("image")
    public ModelAndView image() {

        return new ModelAndView("image");
    }

    @PostMapping ("/upload-result")
    public ModelAndView uploadFile(@RequestParam Map<String, Object> map, MultipartHttpServletRequest request) {

        String uploadPath = request.getSession().getServletContext().getRealPath("/");

        //파일이 업로드 될 경로 설정
       //  String saveDir = request.getSession().getServletContext().getRealPath("/resources/upload/file");

        //위에서 설정한 경로의 폴더가 없을 경우 생성
//        File dir = new File(saveDir);
//        if(!dir.exists()) {
//            dir.mkdirs();
//        }

        List<String> fileList = new ArrayList<>();

        // 파일 업로드
        for(MultipartFile f : request.getFiles("uploadFile")) {
            if(!f.isEmpty()) {
                System.out.println("파일 이름 : " + f.getOriginalFilename());
                System.out.println("파일 타입 : " + f.getContentType());
                System.out.println("파일 크기 : " + f.getSize());

                fileList.add(FileUtil.fileUpload(uploadPath + "/upload", f));
            }
        }
        map.put("fileList", fileList);

        ModelAndView mv = new ModelAndView();
        mv.addObject("map", map);
        mv.setViewName("success");
        return mv;
    }

//    @PostMapping("/url")
//    public String getData(Model model, MultipartHttpServletRequest request){
//        List <MultipartFile> multipartFileList = new ArrayList<>();
//        try{
//            MultiValueMap<String, MultipartFile> files =request.getMultiFileMap();
//            for(Map.Entry<String, List<MultipartFile>> entry : files.entrySet()){
//                List<MultipartFile> fileList = entry.getValue();
//                for(MultipartFile file: fileList){
//                    if(!file.isEmpty()) continue;
//                    multipartFileList.add(file);
//                }
//            }
//            if(multipartFileList.size() > 0) {
//                for (MultipartFile file : multipartFileList) {
//                    file.transferTo(new File("/upload" + File.separator + file.getOriginalFilename()));
//                }
//            }
//        }catch(Exception e) {
//            e.printStackTrace();
//            logger.info("has no multipartFile!");
//        }
//        model.addAttribute("log", "사진" + multipartFileList.size() + "장 전송완료!");
//        return "html 템플릿 주소 :: #resultDiv";
//    }

    // 상세
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam Map<String, Object> map){
        Map<String, Object> detailMap = this.contactService.member_detail(map);

        ModelAndView mv = new ModelAndView();
        mv.addObject("data", detailMap);
        String id = map.get("m_id").toString();
        mv.addObject("id", id);
        mv.setViewName("detail");

        return mv;
    }

    // 목록
    @RequestMapping(value = "list")
    public ModelAndView list(@RequestParam Map<String, Object> map){
        List<Map<String, Object>> list = this.contactService.member_list(map);

        ModelAndView mv = new ModelAndView();
        mv.addObject("data", list);

        if(map.containsKey("keyword")) {
            mv.addObject("keyword", map.get("keyword"));
        }

        mv.setViewName("list");

        return mv;
    }

    // 수정
    @GetMapping ("update")
    public ModelAndView update(@RequestParam Map<String, Object> map) {
        Map<String, Object> detailMap = this.contactService.member_detail(map);

        ModelAndView mv = new ModelAndView();
        mv.addObject("data", detailMap);
        mv.setViewName("update");

        return mv;
    }

//    @PostMapping("update")
//    public ModelAndView updateMember(@RequestParam Map<String, Object> map, MultipartHttpServletRequest req) throws Exception{
//
//        ModelAndView mv = new ModelAndView();
//
//        String memberImg = FileUtil.updateImg(req);
//
//        int updateSuccess = this.contactService.update(map);
//        if(updateSuccess == 1) {
//            String id = map.get("m_id").toString();
//            mv.setViewName("redirect:/detail?m_id="+id);
//        }else {
//            mv = this.update(map);
//        }
//
//        return mv;
//    }


    @PostMapping("update")
    public ModelAndView updateMember(@RequestParam Map<String, Object> map, MultipartHttpServletRequest request){
        ModelAndView mv = new ModelAndView();

        MultipartFile file = request.getFile("uploadFile");
        if(file!= null) {
            String uploadPath = request.getSession().getServletContext().getRealPath("/");

            String profile = FileUtil.fileUpload(uploadPath + "/upload", file);
            map.put("profile", profile);

            System.out.println(profile);
        }

        Map<String, Object> detailMap = this.contactService.member_detail(map);
        mv.addObject("data", detailMap);

        int updateSuccess = this.contactService.update(map);
        if(updateSuccess == 1) {
            String id = map.get("m_id").toString();
            mv.setViewName("redirect:/detail?m_id="+id);
        }else {
            mv = this.update(map);
        }

        return mv;
    }

//    @GetMapping("vo_update")
//    public String update(Model model, String id) {
//        System.out.println("id = " + id);
//        model.addAttribute("vo", this.contactService.member_detail(Integer.valueOf(id)));
//
//        return "vo_update";
//    }

    @GetMapping("vo_update")
    public ModelAndView vo_update(String id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("vo", this.contactService.member_detail(Integer.valueOf(id)));

        mv.setViewName("vo_update");
        return mv;
    }

    @PostMapping("vo_update")
    public ModelAndView vo_updateMember(MemberVO vo){
        System.out.println("vo.id = " + vo.getId());
        ModelAndView mv = new ModelAndView();

        int updateSuccess = this.contactService.update(vo);
        if(updateSuccess == 1) {
            String id = String.valueOf(vo.getId());
            mv.setViewName("redirect:/detail?m_id="+id);
        }

        return mv;
    }

    @RequestMapping("/delete")
    public ModelAndView delete(@RequestParam Map<String, Object> map) {
        ModelAndView mv = new ModelAndView();

        int updateSuccess = this.contactService.delete(map);
        if(updateSuccess == 1) {
            mv.setViewName("redirect:/list");
        }else {
            String id = map.get("m_id").toString();
            mv.setViewName("redirect:/detail?m_id="+id);
        }

        return mv;
    }

    // 삭제
    @RequestMapping("/vo_delete")
    public ModelAndView vo_delete(MemberVO vo) {
        ModelAndView mv = new ModelAndView();

        System.out.println("Deleteid = " + vo.getId());
        int updateSuccess = this.contactService.delete(vo);
        if(updateSuccess == 1) {
            mv.setViewName("redirect:/list");
        }else {
            String id = String.valueOf(vo.getId());
            mv.setViewName("redirect:/vo_update?m_id="+id);
        }

        return mv;
    }

    /* 연락처 웹사이트 기능 끝 */


    //HttpServletRequest로 form의 데이터를 접근
    @RequestMapping("/createServletRequest")
    public String create(HttpServletRequest request, Model model) {
        //데이터를 전달할 때에는 모델에 담아서 전달하는데 전달할 데이터는 request에 담겨있다.
        model.addAttribute("name", request.getParameter("name"));
        model.addAttribute("phoneno", request.getParameter("phoneno"));
        model.addAttribute("addr", request.getParameter("addr"));
        model.addAttribute("email", request.getParameter("email"));
        model.addAttribute("method", "HttpServletRequest");

        return "info";
    }

//    //@RequestParam으로 form의 데이터를 접근
//    @RequestMapping("/createRequestParam")
//    public String create(Model model, String name,
//                       @RequestParam("phoneno") String p, String addr, String email) {
//        //지정해도 되지만 지정하지 않아도 기본적으로 @RequestParam 처리가 된다.
//        //form 태그의 name 속성과 통일을 하면 RequestParam의 매개 변수로 지정을 하지 않아도 되지만,
//        //name 속성과 매개 변수의 이름을 다르게 설정하면 RequestParam의 매개 변수로 지정을 해주어야 한다.
//
//        model.addAttribute("name", name);
//        model.addAttribute("phoneno", p);
//        model.addAttribute("addr", addr);
//        model.addAttribute("email", email);
//        model.addAttribute("method", "@RequestParam");
//
//        return "info";
//    }

    @RequestMapping("/createRequestParam")
    public String create(Model model, String name,
                         String phoneno, String addr, String email, String gender){
        //지정해도 되지만 지정하지 않아도 기본적으로 @RequestParam 처리가 된다.
        //form 태그의 name 속성과 통일을 하면 RequestParam의 매개 변수로 지정을 하지 않아도 되지만,
        //name 속성과 매개 변수의 이름을 다르게 설정하면 RequestParam의 매개 변수로 지정을 해주어야 한다.
        model.addAttribute("name", name);
        model.addAttribute("phoneno", phoneno);
        model.addAttribute("addr", addr);
        model.addAttribute("email", email);
        model.addAttribute("gender", gender);
        model.addAttribute("method", "@RequestParam");

        return "info";
    }

    @RequestMapping("/createDataObject")
    public String vo_create(MemberVO vo, Model model) {
        model.addAttribute("vo", vo);
        model.addAttribute("method", "데이터 객체");

        System.out.println(contactService.create(vo));

        return "info";
    }

    //@PathVariable로 form의 데이터를 접근
    @RequestMapping("/createPathVariable/{name}/{phoneno}/{addr}/{email}")
    public String create(@PathVariable String name,
                         @PathVariable String phoneno, @PathVariable String addr, @PathVariable String email, Model model) {
        //@PathVariable은 파라미터마다 어노테이션을 따로 지정해주어야한다.
        //Model의 위치는 파라미터 처음, 중간, 끝, 어디여도 상관없다
        model.addAttribute("name", name);
        model.addAttribute("phoneno", phoneno);
        model.addAttribute("addr", addr);
        model.addAttribute("email", email);
        model.addAttribute("method", "@PathVariable");

        return "info";
    }
}
