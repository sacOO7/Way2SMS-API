package com.company;

import com.jaunt.Element;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;
import com.jaunt.component.Form;

/**
 * Created by sachin on 5/4/16.
 */
public class Way2SMS {


    UserAgent agent;
    Form sms;
    Boolean LoginSuccessful = false;
    public static String Token;

    /**
     * Used for logging into way2sms site using username as a mobile no and password
     * @param username
     * @param pasword
     * @throws ResponseException
     * @throws NotFound
     */
    public void Login(String username, String pasword) throws ResponseException, NotFound {
        agent = new UserAgent();
        agent.visit("http://site24.way2sms.com/content/index.html");
        Form form = agent.doc.getForm(1);
        form.setTextField("username", username);
        form.setPassword("password", pasword);
        form.submit();
        if (agent.doc.innerHTML().contains("<h3>Welcome to Way2SMS</h3>")) {
            System.out.println("Login successful");
            MakeDashBoardReady();
            LoginSuccessful = true;
        } else {
            System.out.println("Login failed");
            LoginSuccessful = false;
        }
    }

    /**
     * Used for traversing into dashboard page which is nothing but a form to send sms.
     * @throws NotFound
     * @throws ResponseException
     */

    public void MakeDashBoardReady() throws NotFound, ResponseException {
        Form form1 = agent.doc.getForm(0);
        Element element = form1.getElement().findFirst("<input type=\"button\"");
        String character = String.valueOf(element.getAt("onclick").charAt(element.getAt("onclick").length() - 4));
        Element token = form1.getElement().findFirst("<input type=\"hidden\" name=\"Token\"");
        Token = token.getAt("value");
        form1.setHidden("section", character);
        form1.setAction("http://site24.way2sms.com/main.action");
        form1.submit();
        agent.visit("http://site24.way2sms.com/sendSMS?Token=" + Token);
        sms = agent.doc.getForm(0);
    }

    /**
     * Used to send sms to mobile no provided in arguments.You can use this method in loop , so that you dont have to login each time to send sms.
     * @param Mobile_No
     * @param Message
     * @throws NotFound
     * @throws ResponseException
     */

    public void SendSMS(String Mobile_No, String Message) throws NotFound, ResponseException {
        if (LoginSuccessful) {
            sms.setTextField("mobile", Mobile_No);
            sms.setTextArea("message", Message);
            sms.setAction("http://site24.way2sms.com/./smstoss.action");
            sms.submit();
            if (agent.doc.innerHTML().contains("Message has")){
                System.out.println("Message has been submitted for number "+Mobile_No);
            }else{
                System.out.println("Message hasnt been submitted for number "+Mobile_No);
            }
        } else {
            System.out.println("Please Make correct Login , then send message");
        }
    }
}

