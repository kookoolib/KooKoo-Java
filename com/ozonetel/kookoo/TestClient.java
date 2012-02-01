package com.ozonetel.kookoo;

public class TestClient {

    public static void main(String[] args) {
        Response resp = new Response();

        Dial dial= new Dial();
        dial.setNumber("99999999999");
        dial.setMusicOnHold(Dial.MusicOnHold.NORMAL);
//        dial.setMusicOnHold(Dial.MusicOnHold.DEFAULT);
//        dial.setMusicOnHold(Dial.MusicOnHold.RING);
        resp.addDial(dial);

       
        System.out.println(""+resp.getXML());
    }
}
