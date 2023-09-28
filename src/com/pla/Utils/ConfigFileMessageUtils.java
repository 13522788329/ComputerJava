package com.pla.Utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

@Component
public class ConfigFileMessageUtils implements InitializingBean {
    @Value("${server.port}")
    private String serverPort;
    /**
     * 本机IP地址
     */
    public static String LOCAL_IP;
    /**
     * 本机服务器端口号
     */
    public static String SERVER_PORT;
    /**
     * 保存图片目录名，默认resources/static下
     */
    public static String IMAGE_DIR_NAME = "img";
    /**
     * 图片存放的全路径名
     */
    public static String IMAGE_ALL_DIR;

    /**
     * 该方法在服务器加载时被调用
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {

        SERVER_PORT = this.serverPort;

        LOCAL_IP = InetAddress.getLocalHost().getHostAddress();

        IMAGE_ALL_DIR = System.getProperty("user.dir") + "/src/main/resources/static/" + IMAGE_DIR_NAME + "/";

    }
}
