package com.knoldus.dispatcher.sms.service;

import com.amazonaws.services.pinpoint.AmazonPinpoint;
import com.amazonaws.services.pinpoint.model.AddressConfiguration;
import com.amazonaws.services.pinpoint.model.ChannelType;
import com.amazonaws.services.pinpoint.model.DirectMessageConfiguration;
import com.amazonaws.services.pinpoint.model.MessageRequest;
import com.amazonaws.services.pinpoint.model.SMSMessage;
import com.amazonaws.services.pinpoint.model.SendMessagesRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class SendMessage {

    @Autowired
    private AmazonPinpoint amazonPinpoint;

    private static String originationNumber = "+12065550199";

    private static String destinationNumber = "+14255550142";

    private static String message = "This message was sent through Amazon Pinpoint "
            + "using the AWS SDK for Java. Reply STOP to "
            + "opt out.";

    public static String appId = "ce796be37f32f178af652b26eexample";
    public static String messageType = "TRANSACTIONAL";

    public static String registeredKeyword = "myKeyword";

    public static String senderId = "MySenderID";

    public void send() throws IOException {

        try {
            Map<String, AddressConfiguration> addressMap =
                    new HashMap<String,AddressConfiguration>();

            addressMap.put(destinationNumber, new AddressConfiguration()
                    .withChannelType(ChannelType.SMS));

            SendMessagesRequest request = new SendMessagesRequest()
                    .withApplicationId(appId)
                    .withMessageRequest(new MessageRequest()
                            .withAddresses(addressMap)
                            .withMessageConfiguration(new DirectMessageConfiguration()
                                    .withSMSMessage(new SMSMessage()
                                            .withBody(message)
                                            .withMessageType(messageType)
                                            .withOriginationNumber(originationNumber)
                                            .withSenderId(senderId)
                                            .withKeyword(registeredKeyword)
                                    )
                            )
                    );
            System.out.println("Sending message...");
            amazonPinpoint.sendMessages(request);
            System.out.println("Message sent!");
        } catch (Exception ex) {
            System.out.println("The message wasn't sent. Error message: "
                    + ex.getMessage());
        }
    }
}

