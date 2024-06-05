package com.example.demo5.config;

import lombok.Getter;
import lombok.Setter;
import com.example.demo5.annotation.ConfigurationProperties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.util.Properties;

@Getter
@Setter
@ConfigurationProperties(path = "./src/main/resources/demo5.properties")
public class DatasourceProperties {
    public static void main(String[] args) {
        Class<?> clazz = DatasourceProperties.class;
        ConfigurationProperties config = clazz.getAnnotation(ConfigurationProperties.class);
        System.err.println(config.path());
    }


    private String username;
    private  String password;
    // tính chất đóng gói
}
