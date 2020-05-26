package ua.edu.ukma.ykrukovska.practice2;

import ua.edu.ukma.ykrukovska.practice2.channel.Channel;
import ua.edu.ukma.ykrukovska.practice2.processor.AbstractDataProcessingService;
import ua.edu.ukma.ykrukovska.practice2.processor.ProcessingManager;

import java.util.LinkedList;
import java.util.List;

public class WatchCat implements Runnable {

    private List<Channel> channels = new LinkedList<>();
    private List<AbstractDataProcessingService> processors = new LinkedList<>();

    public void registerChannel(Channel channel) {
        channels.add(channel);
    }

    public void registerProcessor(AbstractDataProcessingService processor) {
        processors.add(processor);
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


        for (AbstractDataProcessingService processor : processors) {
            processor.stopProcessing();
        }
        ProcessingManager.stopProcessing();
    }
}
