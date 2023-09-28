package com.pla.Utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.UUID;

public class LocalUploadImageUtil {
    public static String uploadImageFile(MultipartFile file) {
        String newFileName = getNewFileNameByUuid(file.getOriginalFilename());
        String finalPath = getFinalPath(newFileName);
        String url = "https://" + ConfigFileMessageUtils.LOCAL_IP + ":" + ConfigFileMessageUtils.SERVER_PORT + "/" + ConfigFileMessageUtils.IMAGE_DIR_NAME + "/" + newFileName;
        boolean flag = upload(file, finalPath);
        if (!flag) {
            throw new RuntimeException("图片上传失败！");
        }
        return url;
    }

    public static String uploadImageFile(MultipartFile file, String fileName) {
        String finalPath = getFinalPath(fileName);
        String url = "https://" + ConfigFileMessageUtils.LOCAL_IP + ":" + ConfigFileMessageUtils.SERVER_PORT + "/" + ConfigFileMessageUtils.IMAGE_DIR_NAME + "/" + fileName;
        boolean flag = upload(file, finalPath);
        if (!flag) {
            throw new RuntimeException("图片上传失败！");
        }

        return url;
    }

    private static String getFinalPath(String fileName) {
        return ConfigFileMessageUtils.IMAGE_ALL_DIR + fileName;
    }

    private static boolean upload(MultipartFile file, String finalPath) {
        File dest = new File(finalPath);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        try {
            file.transferTo(dest);
            return true;
        } catch (IOException e) {
            e.printStackTrace();;
            return false;
        }
    }

    public static String addFixForFileName(String fix, String fileName) {
        return fix + getFileSuffix(fileName);
    }

    private static String getNewFileNameByUuid(String fileName) {
        return getUUID() + getFileSuffix(fileName);
    }

    private static String getUUID() {
        return UUID.randomUUID().toString();
    }

    private static String getFileSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    @Configuration
    @Component
    public static class ConfigFileMessageUtils implements InitializingBean {
        @Value("${server.port}")
        private String serverPort;

        public static String LOCAL_IP;

        public static String SERVER_PORT;

        public static String IMAGE_DIR_NAME;

        public static String IMAGE_ALL_DIR;

        @Override
        public void afterPropertiesSet() throws Exception {
            SERVER_PORT = this.serverPort;
            LOCAL_IP = InetAddress.getLocalHost().getHostAddress();
            IMAGE_ALL_DIR = System.getProperty("user.dir") + "/src/resources/static/" + IMAGE_DIR_NAME;
        }
    }
}


