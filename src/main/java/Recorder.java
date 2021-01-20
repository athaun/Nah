import ApiDataTypes.ApiPlayerInfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Recorder {

    static ApiPlayerInfo[] lastPlayerList;
    static ApiPlayerInfo[] playerList;
    static int currentlyOnline = 0;
    static int serverPlayerCap;
    static int lastOnline = 0;
    static int dailyMax = 0; // max number of players on at one time
    static int dailyTotal = 0; // total number of times players logged in
    static int totalDailyPlayers = 0; // total number of players who played on a given day

    static boolean resetDate = false;
    static String date;
    static String tempDate;

    static String dailyMaxCSV = "/home/asher/activityRecord/dailyMax.csv";
    static String dailyTotalCSV = "/home/asher/activityRecord/dailyTotal.csv";
    static String totalDailyPlayersCSV = "/home/asher/activityRecord/totalDailyPlayers.csv";

    public static void printStats () {
        tempDate = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
        if (lastOnline != currentlyOnline) {
            String timeStamp = new SimpleDateFormat("[MM/dd - HH:mm:ss]").format(new Date());
            System.out.println(timeStamp + " Online: " + currentlyOnline + "/" + serverPlayerCap + " | Daily max: " + dailyMax
                    + " | Daily logins: " + dailyTotal + " | Total Daily Players: " + totalDailyPlayers);
        }
    }

    static void calculateStats() {
        lastOnline = currentlyOnline;
        lastPlayerList = playerList;

        currentlyOnline = Stats.data.getPlayers().getOnline();
        if (currentlyOnline > 0) {
            playerList = Stats.data.getPlayers().getSample();
        } else {
            playerList = new ApiPlayerInfo[1];
        }
        serverPlayerCap = Stats.data.getPlayers().getMax();

        calculateDailyMax();
        calculateDailyTotal();
        calculateTotalUsers();
        printStats();
        resetDate();
    }

    static int tempDailyMax;
    static ArrayList<Integer> playerCounts = new ArrayList<Integer>();

    static void calculateDailyMax() {
        if (date.equals(tempDate)) {
            if (currentlyOnline > dailyMax) {
                playerCounts.add(currentlyOnline);
                tempDailyMax = currentlyOnline;
            }
            dailyMax = getLargestNumber(playerCounts);
            if (dailyMax < 0) {
                dailyMax = 0;
            }
        } else {
            writeCSV(date + "," + dailyMax + "\n", dailyMaxCSV);
            // reset everything for the next day
            tempDailyMax = 0;
            playerCounts = new ArrayList<Integer>();
            resetDate = true;
        }
    }

    static void calculateDailyTotal() {
        if (date.equals(tempDate)) {
            if (lastOnline < currentlyOnline) {
                dailyTotal += (currentlyOnline - lastOnline);
            }
        } else {
            writeCSV(date + "," + dailyTotal + "\n", dailyTotalCSV);
            // reset everything for the next day
            dailyTotal = 0;
            lastOnline = 0;
            resetDate = true;
        }
    }

    static ArrayList<String> playedToday = new ArrayList<String>();

    static void calculateTotalUsers() {
        if (date.equals(tempDate)) {
            if (currentlyOnline > lastOnline) {
                for (int i = 0; i < playerList.length; i++) {
                    String current = playerList[i].getName();
                    if (!playedToday.contains(current)) {
                        totalDailyPlayers++;
                        playedToday.add(current);
                    }
                }
            }
        } else {
            writeCSV(date + "," + totalDailyPlayers + "\n", totalDailyPlayersCSV);
            // reset everything for the next day
            totalDailyPlayers = 0;
            lastOnline = 0;
            resetDate = true;
        }
    }

    public static void resetDate() {
        date = tempDate;
        resetDate = false;
    }

    static BufferedWriter out;

    static void writeCSV(String data, String path) {
        try {
            try {
                out = new BufferedWriter(new FileWriter(path, true));
                try {
                    out.write(data);
                } catch (IOException e) {
                }
            } finally {
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static int getLargestNumber(ArrayList<Integer> list) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            if (obj instanceof Integer && max < (Integer) obj)
                max = (Integer) obj;
        }
        return max;
    }

    static void sleep(int time) {
        try {
            // thread to sleep for 1000 milliseconds
            Thread.sleep(time);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}