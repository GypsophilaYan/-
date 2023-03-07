package com.example.common;

/**
 * 作者：严裕
 * 时间：2023-2-28    23:16
 */
public class CustomException extends RuntimeException{
    public CustomException(String message){
        super(message);
    }
}
