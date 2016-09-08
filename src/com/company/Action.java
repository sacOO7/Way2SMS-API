

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
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Action {

    public static String USERNAME="8956613425";  //Phone no as a username
    public static String PASSWORD="2ptzk8";




    public  void run() throws NotFound, ResponseException, InterruptedException {
        Way2SMS way2SMS = new Way2SMS();
        way2SMS.Login(USERNAME,PASSWORD);

        Set <User> hashset=new HashSet<>();
        Set <String> numbers=new HashSet<>();
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        MongoDatabase database=mongoClient.getDatabase("Users");
        MongoCollection<Document> collection=database.getCollection("credenzappusers");
//        System.out.println(collection.count());
        for (Document cur : collection.find()) {
            if(cur.get("Phone1")!=null){
                String value=String.valueOf(cur.get("Phone1"));
                if(value.length()==10){
//                    System.out.println(value);
                    if(!numbers.contains(value)){
                        numbers.add(value);
                        hashset.add(new User(((String) cur.get("Name1")).split(" ")[0],value));
                    }
                }
            }
            if(cur.get("Phone2")!=null){
                String value=String.valueOf(cur.get("Phone2"));
                if(value.length()==10){
//                    System.out.println(value);
                    if(!numbers.contains(value)){
                        numbers.add(value);
                        hashset.add(new User(((String) cur.get("Name2")).split(" ")[0],value));
                    }
                }
            }
            if(cur.get("Phone3")!=null){
                String value=String.valueOf(cur.get("Phone3"));
                if(value.length()==10){
//                    System.out.println(value);
                    if(!numbers.contains(value)){
                        numbers.add(value);
                        hashset.add(new User(((String) cur.get("Name3")).split(" ")[0],value));
                    }
                }
            }
//            System.out.println("Phone2 is"+cur.get("Phone2"));
        }

        System.out.println(hashset.size());



        collection=database.getCollection("credenzreceipt");
        System.out.println(collection.count());
        for (Document cur : collection.find()) {
            if(cur.get("Contact1")!=null){
                String value=String.valueOf(cur.get("Contact1"));
                if(value.length()==10){
//                    System.out.println(value);
                    if(!numbers.contains(value)){
                        numbers.add(value);
                        hashset.add(new User(((String) cur.get("Name1")).split(" ")[0],value));
                    }
                }
            }
            if(cur.get("Contact2")!=null){
                String value=String.valueOf(cur.get("Contact2"));
                if(value.length()==10){
//                    System.out.println(value);
                    if(!numbers.contains(value)){
                        numbers.add(value);
                        hashset.add(new User(((String) cur.get("Name2")).split(" ")[0],value));
                    }
                }
            }
            if(cur.get("Contact3")!=null){
                String value=String.valueOf(cur.get("Contact3"));
                if(value.length()==10){
//                    System.out.println(value);
                    if(!numbers.contains(value)){
                        numbers.add(value);
                        try {
                            hashset.add(new User(((String) cur.get("Name3")).split(" ")[0], value));
                        }catch(Exception e){
                            System.out.println("cought exception");
                        }
                    }
                }
            }
//            System.out.println("Phone2 is"+cur.get("Phone2"));
        }



        collection=database.getCollection("droidfest_users");
//        System.out.println(collection.count());
        for (Document cur : collection.find()) {
            if(cur.get("phone_no")!=null){
                String value=String.valueOf(cur.get("phone_no"));
                if(value.length()==10){
//                    System.out.println(value);
                    if(!numbers.contains(value)){
                        numbers.add(value);
                        hashset.add(new User(((String) cur.get("name")).split(" ")[0],value));
                    }
                }
            }
        }

        collection=database.getCollection("hingemeetusers");
//        System.out.println(collection.count());
        for (Document cur : collection.find()) {
            if(cur.get("Number")!=null){
                String value=String.valueOf(cur.get("Number"));
                if(value.length()==10){
//                    System.out.println(value);
                    if(!numbers.contains(value)){
                        numbers.add(value);
                        hashset.add(new User(((String) cur.get("Name")).split(" ")[0],value));
                    }
                }
            }
        }


        System.out.println(hashset.size());
        int i=1;
        for (User user:hashset) {
//            System.out.print(user.getName());
//            System.out.println(user.getPhone_no());
            i++;
            if (i > 7) {
                way2SMS.SendSMS(user.getPhone_no(), "Hi " + user.getName() + "! This is how you can build your Campus Startup: http://bit.ly/28Pa8aC FREE first lesson: http://bit.ly/28POhxJ Only for you:)");
                Thread.sleep(4000);
            }else{
                System.out.println("Interrupted for "+user.getPhone_no());
            }
            System.out.println(i);
        }
//        List <User> number=new ArrayList<>();
//        number.add(new User("lol","9819124829"));
//        number.add(new User("lol","8446499908"));
//        number.add(new User("lol","7709758284"));
//        number.add(new User("lol","8956613425"));
//        number.add(new User("lol","7276807536"));
//        for (int i=0;i<number.size();i++) {
//            way2SMS.SendSMS(number.get(i).getPhone_no(), "Hi "+number.get(i).getName()+"! This is how you can build your Campus Startup: http://bit.ly/28Pa8aC FREE first lesson: http://bit.ly/28POhxJ Only for you :)"); //args ; Phone no , Message
//        }
    }
}
