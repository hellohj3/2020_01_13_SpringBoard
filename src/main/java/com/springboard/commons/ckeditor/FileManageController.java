package com.springboard.commons.ckeditor;

import com.springboard.commons.util.UploadsFileUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class FileManageController {

    /**
     * Do save image data
     * @param model - Model. Send data and view
     * @param upload - String. command string
     * @param file - MultipartFile. file data
     * @return String. Result message
     * @exception Exception
     */
    @RequestMapping("/fileupload.do")
    public String fileupload(Model model,
                             @RequestParam(value="upload", required = false) MultipartFile file,
                             HttpServletRequest request) throws Exception {

        UploadsFileUtils uploadsFileUtils = new UploadsFileUtils();

        try {
            String filePath = uploadsFileUtils.uploadFile(file, request);
            return "success";
        } catch (IOException e) {
            return "fail";
        }
    }
}