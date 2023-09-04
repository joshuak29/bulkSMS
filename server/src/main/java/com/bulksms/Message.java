package com.bulksms;

public class Message {
    private String textMessage;
    private String senderId;

    public Message(String textMessage, String senderId) {
        this.textMessage = textMessage;
        this.senderId = senderId;
    }

    public String getText() {
        return this.textMessage;
    }

    public String getSenderId() {
        return this.senderId;
    }

    public double getPrice() {
        float messageLength = textMessage.length();
        float smsChars = 160;
        float smsUnitPrice = 5;

        double units = Math.ceil(messageLength / smsChars);
        double tot = units * smsUnitPrice;

        return tot;
    }

    public static void main(String[] args) {
        String text = """
                A single SMS message technically supports up to 160 characters, or up to 70 if the message contains one or more Unicode characters (such as emoji or Chinese characters). However, modern phones and mobile networks support message concatenation, which enables longer messages to be sent.
                    """;
        Message sms = new Message(text, "MTN MOMO");

        System.out.println(sms.getText());
        System.out.println(sms.getPrice());
    }
}
