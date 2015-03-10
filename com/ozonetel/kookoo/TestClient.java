package com.ozonetel.kookoo;

public class TestClient {

    public static void main(String[] args) {
        Response resp = new Response();
        resp.sendSms("Hi KooKoo", "9490607378");
        resp.sayAs(Response.SayAs.DIGITS,"1234");
        System.out.println(""+resp.getXML());
    }
}
