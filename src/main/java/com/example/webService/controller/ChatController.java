package com.example.webService.controller;

import com.example.webService.Data;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/send")
public class ChatController {
    @GetMapping("")
    public String send(@RequestParam(value = "chatId",required = true) String idChat,@RequestParam(value = "text", required = true) String text) {
        final Content getResult;
        //нижнее подчеркивание вместо пробела
        final String get=idChat+"&text="+text.replace("_","%20");
        try {

            getResult = Request.Get("https://api.telegram.org/bot1771180306:AAGZoZDoA5LvzsLcuAPzAI5aWT7VchiTcj4/sendMessage?chat_id="+get)
                    .execute().returnContent();
           return getResult.asString();

        } catch (IOException e) {
            e.printStackTrace();
        }
return "error "+ get;

    }
    //Post запрос(chatId=,text=.)
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public  String post(@RequestBody Data data){
        final Content getResult;
        data.setText(data.getText().replace("_","%20"));
        final String get= data.getChatId()+"&text="+data.getText();
        try {

            getResult = Request.Get("https://api.telegram.org/bot1771180306:AAGZoZDoA5LvzsLcuAPzAI5aWT7VchiTcj4/sendMessage?chat_id="+get)
                    .execute().returnContent();
            return getResult.asString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";

    }
    @GetMapping("/info")
    public ResponseEntity getChat(){
        try{
return ResponseEntity.ok("Working");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }
}
