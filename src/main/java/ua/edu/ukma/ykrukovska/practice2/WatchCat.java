package ua.edu.ukma.ykrukovska.practice2;

import ua.edu.ukma.ykrukovska.practice2.channel.Channel;
import ua.edu.ukma.ykrukovska.practice2.processor.ProcessingManager;

import java.util.LinkedList;
import java.util.List;

public class WatchCat implements Runnable {

    private List<Channel> channels = new LinkedList<>();

    public void registerChannel(Channel channel) {
        channels.add(channel);
    }

    @Override
    public void run() {
        int stopPoint = 1;

        while (stopPoint != 0) {
            for (Channel channel : channels) {
                stopPoint = channel.size();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        for (int i = 0; i < channels.size(); i++) {
            Object o = channels.get(i);
            o = null;
        }
        ProcessingManager.stopProcessing();
        System.out.println("out ");
    }
}
