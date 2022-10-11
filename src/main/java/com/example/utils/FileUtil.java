package com.example.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class FileUtil {
    public static String fileUpload(String path, MultipartFile uploadFile){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = sdf.format(date);
        String datePath = str.replace("-", File.separator);

        /* 폴더 생성 */
        File uploadPath = new File(path, datePath);

        if(!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        /* uuid 적용 파일 이름 */
        String uuid = UUID.randomUUID().toString();

        /* 파일 이름 */
        String fileName = '\\' + uuid+ '_' + uploadFile.getOriginalFilename();
        /* 파일 위치, 파일 이름을 합친 File 객체 */
        File destFile = new File(uploadPath + fileName);

        try {
            uploadFile.transferTo(destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "upload\\" + datePath + fileName;

    }

}
