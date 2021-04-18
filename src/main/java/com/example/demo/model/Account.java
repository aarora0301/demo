package com.example.demo.model;


public class Account {
    private String id;
    private String password;


    public boolean resetPassword(String password){
      this.password= password;
      return true;
    }
}