package com.arlley.pack.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wanggang
 * @since 2020-08-02
 */
@RestController
@RequestMapping("/file")
public class FileController {


    @PostMapping("/upload")
    public int uploadFile(@RequestParam("file") MultipartFile file){

        return 0;
    }
}
