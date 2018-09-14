package com.springboot.servlet.servlettest;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;

/**
 * @ClassName CompletableFutureDemo
 * @Description TODO
 * @Author 刘海飞
 * @Date 2018/9/14 11:12
 * @Version 1.0
 **/
public class CompletableFutureDemo {

    public static void main(String[] args) {
        //1.completedFuture
//        CompletableFuture<String> future=CompletableFuture.completedFuture("hello");
//        System.out.println(future.isDone());
//        System.out.println(future.getNow(null));
        //2.supplyAsync
        CompletableFuture<String> future=CompletableFuture.supplyAsync(()->{
            return "hello";
        }).thenApplyAsync(result->{
            return result+"world";
        }).whenComplete((v,e)->{
            System.out.println(v);
            System.out.println(e);
        }).exceptionally(e->{
            return e.getMessage();
        });


    }
}
