package com.example.sahartest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class SaharTestApplication {

    public static void main(String[] args) {
        //  System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhh");

        SpringApplication.run(SaharTestApplication.class, args);
    }

  /*  @Controller
    public static class paymentControllers {

        @GetMapping("/start?{id}")
        public String paymentStartString(@RequestParam("id") int id) {


            return "sahar" + id;
        }

        @RequestMapping("/templates/home.html")
        public void paymentStart() {


            //return "sahar///";
        }
    }*/
}
/*
package com.example.sahartest;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.PublicKey;


@SpringBootApplication
public class SaharTestApplication {



        public static String verifyEmail(String email) {

            //SpringApplication.run(ValidateEmailApplication.class, args);
            String key = "03017b4bd26b5e7b099922068d0b5934";
            //String email = "aggab.saha2549848654884@gmail.com";
            String targetURL = "https://apilayer.net/api/check?access_key=" + key + "&email=" + email;
            HttpURLConnection connection = null;

            try {
                URL url = new URL(targetURL);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                BufferedReader in = new BufferedReader(isr);
                System.out.println(connection+" hhhhh");

                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                //print result
             //   System.out.println(response.toString());

                return response.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }

    public static void main(String[] args) {
        String res = verifyEmail("aggab.saha2549848654884@gmail.com");
    }
}*/