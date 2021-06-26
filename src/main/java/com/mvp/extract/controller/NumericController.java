package com.mvp.extract.controller;


import com.mvp.extract.model.Position;
import com.mvp.extract.service.NumericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.mvp.extract.constant.Constants.BASE_PATH;
import static com.mvp.extract.constant.Constants.NUMERICS;
import static com.mvp.extract.util.Validator.validateInput;

@RestController
@RequestMapping(BASE_PATH)
public class NumericController {

    @Autowired
    NumericService numericService;

    @GetMapping(value = NUMERICS, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<Position>> extractNumbersFromFile(@RequestParam("file") MultipartFile file) {
        validateInput(file);
        return new ResponseEntity<>(numericService.extract(file), HttpStatus.OK);
    }

}
