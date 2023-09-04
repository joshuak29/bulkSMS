package com.bulksms.campaigns;

import java.util.ArrayList;
import java.util.HashMap;

import com.bulksms.users.UserDb;
import com.bulksms.users.User;

public class CampaignsDb {
    private static ArrayList<Campaign> campaigns = new ArrayList<>();

    static {
        HashMap<String, Integer> schedule = new HashMap<>(0, 0);
        schedule.put("year", 2023);
        schedule.put("month", 12);
        schedule.put("date", 202123);

        User user = UserDb.getUser(0);

        campaigns.add(new Campaign("MOMO", "Get OUT!", new String[] { "07909", "0780" }, schedule, user));
        campaigns.add(new Campaign("MOMO", "Get OUT!", new String[] { "07909", "0780" }, schedule, user));
    }

    public static ArrayList<Campaign> getCampaigns() {
        return campaigns;
    }

    public static Campaign getCampaign(int id) {
        if(id >= campaigns.size()){
            return campaigns.get(0);
        }
        return campaigns.get(id);
    }

    public static Campaign addCampaigns() {
        HashMap<String, Integer> schedule = new HashMap<>(0, 0);
        schedule.put("year", 2023);
        schedule.put("month", 12);
        schedule.put("date", 202123);

        User user = UserDb.getUser(0);

        return new Campaign("BK Arena", "Asake in Rwanda", new String[] { "07909", "0780" }, schedule, user);
    }

}
