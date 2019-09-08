package com.sz.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class MenuFileHelper {

    /**
     * @Parm 接收一个图片文件
     * @return 新图片的文件名
     *
     * */

    public static String uploadImgFile(MultipartFile multipartFile, String imgPath) {
        // 获取文件名（包括文件名的后缀）
        String originalFilename = multipartFile.getOriginalFilename();
        imgPath = imgPath + File.separator;
        // 截取源文件前缀
        String fileNamePrefix = originalFilename.substring(0,
                originalFilename.lastIndexOf("."));
        // 加工处理文件名，源文件名+时间戳
        String newFileNamePrefix = fileNamePrefix + new Date().getTime();

        // 得到新的文件名
        String newFileName = newFileNamePrefix + originalFilename.substring
                (originalFilename.lastIndexOf("."));
        // 构建文件对象
        File file = new File(imgPath+newFileName);

        // 上传
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("上传你图片失败");
        }

        return newFileName;
    }
}
