package com.knoldus.dispatcher.sms;

import com.knoldus.dispatcher.sms.service.SendMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class PinpointApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(PinpointApplication.class, args);
		SendMessage sendMessage = new SendMessage();
		sendMessage.send();
	}
}
