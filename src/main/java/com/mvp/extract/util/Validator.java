package com.mvp.extract.util;

import com.mvp.extract.exception.NoFileOrContentFoundException;
import org.springframework.web.multipart.MultipartFile;

public class Validator {

    private Validator(){}

    public static void validateInput(MultipartFile file) {
        if (file.isEmpty()) {
            throw new NoFileOrContentFoundException("File is empty");
        }
        if(file.getContentType() == null){
            throw new NoFileOrContentFoundException("No file content type defined.");
        }
    }

}
