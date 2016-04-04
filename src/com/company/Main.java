package com.company;

import com.jaunt.NotFound;
import com.jaunt.ResponseException;

public class Main {

    public static String USERNAME="";  //Phone no as a username
    public static String PASSWORD="";

    public static void main(String arg[]) throws NotFound, ResponseException {
        Way2SMS way2SMS = new Way2SMS();
        way2SMS.Login(USERNAME,PASSWORD);

        for (int i=0;i<4;i++) {
            way2SMS.SendSMS("", ""); //args ; Phone no , Message
        }
    }
}
