

/*
 * Copyright 2016 sachin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.company;

import com.jaunt.NotFound;
import com.jaunt.ResponseException;

public class Main {

    public static String USERNAME="";  //Phone no as a username
    public static String PASSWORD="";

    public static void main(String arg[]) throws NotFound, ResponseException {
        Way2SMS way2SMS = new Way2SMS();
        way2SMS.Login(USERNAME,PASSWORD);

//        for (int i=0;i<4;i++) {
            way2SMS.SendSMS("", ""); //args ; Phone no , Message
//        }
    }
}
