import ApiDataTypes.ApiPlayerInfo;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class Bot {

    static String prefix = "/";
    static TextChannel updateChannel;
    static boolean channelSet = false;

    static long channelId;
    static long messageId;

    public static void main(String[] args) {

        Recorder.date = new SimpleDateFormat("MM/dd/yyyy").format(new Date());

        Stats.setupGson();
        Stats.getServerStats();

        String token = "Bot Token Here";

        DiscordApi bot = new DiscordApiBuilder().setToken(token).login().join();

        // Print the invite url of your bot
        System.out.println("You can invite the bot by using the following url: " + bot.createBotInvite());

        // Add a listener which answers with "Pong!" if someone writes "!ping"
        bot.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase("/setchannel")) {
                channelSet = false;
                updateChannel = event.getChannel();

                // SETUP
                EmbedBuilder embed = new EmbedBuilder()
                        .setTitle("Update channel set")
                        .setDescription("Please wait for data to be gathered from the API")
                        .setColor(Color.CYAN);

                updateChannel.sendMessage(embed);

                // Get channel and message ID to update
                channelId = updateChannel.getId();

                try {
                    messageId = updateChannel.getMessages(5).get().getNewestMessage().get().getId();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                channelSet = true;
            }
        });


        while (!channelSet) {
            // Block
            sleep(5);
        }
        int count = 0;
        while (channelSet) {
            Stats.getServerStats();
            EmbedBuilder statusMessage;
            String timeStamp = new SimpleDateFormat("h:mm a, MMMMMMM dd").format(Calendar.getInstance().getTime());


            /* Online */
            String playerList = "";

            ApiPlayerInfo[] playerSample = Stats.data.getPlayers().getSample();
            for (int i = 0; i < playerSample.length; i ++) {
                playerList += (playerSample[i].getName()) + "\n";
            }

            if (playerList.equals(""))
                playerList = "Nobody is online right now, lets fix that!";

            String statusDescription =  "Connected to: " + Stats.mcUrl +
                    "\nServer version: " + Stats.data.getVersion().getName();

            statusMessage = new EmbedBuilder()
                    .setTitle("The server is online")
                    .setDescription(statusDescription)
                    .addField("Online Players: " + Stats.data.getPlayers().getOnline(), playerList)
                    .setFooter("\nLast Updated at: " + timeStamp)
                    .setColor(Color.GREEN);

            /* Offline */
            if (Stats.data.getVersion() == null) {
                statusMessage = new EmbedBuilder()
                        .setTitle("The server is offline")
                        .setDescription("Could not connect to " + Stats.mcUrl +
                                        "\nLast Updated at: " + timeStamp)
                        .setColor(Color.RED);
            }

            Message.edit(bot, channelId, messageId, statusMessage);
            sleep(10 * 1000); // 30 seconds between updates

        }


    }

    static void sleep(int time) {
        try {
            // Thread to sleep for 1000 milliseconds
            Thread.sleep(time);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}