package com.arlley.pack.utils;

import com.arlley.pack.entity.File;
import org.springframework.web.multipart.MultipartFile;

public final class FileHelper {

    private FileHelper(){}

    public static File saveFile(MultipartFile file, String fileName){
        String path = FileHelper.class.getResource("/").getPath();
        return null;
    }


    public static void main(String[] args) {
        String path = FileHelper.class.getResource("/").getPath();
        System.out.println(path);
    }
}
