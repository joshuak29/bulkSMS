package dev.josue.bulkSMS.utils;

import java.util.HashMap;

public class CampaignUtils {
    public static Boolean isCampaign(HashMap<String, String> data) {
        String textMessage = data.get("textMessage");
        String senderId = data.get("senderId");
        String schedule = data.get("schedule");
        String numbers = data.get("numbers");

        if (textMessage == null || senderId == null || schedule == null || numbers == null) {
            return false;
        }
        return true;
    }
}
