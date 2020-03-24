package com.springboard.commons.ckeditor.web;

import com.springboard.commons.ckeditor.service.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ImageUploadController {

    @Autowired
    private ImageUploadService imageUploadService;

    @RequestMapping(value = "/imageUpload.do", method = RequestMethod.POST)
    public void imageUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="upload", required = false) MultipartFile upload) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset-utf-8");

        try {
            imageUploadService.imageUpload(request, response, upload);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
