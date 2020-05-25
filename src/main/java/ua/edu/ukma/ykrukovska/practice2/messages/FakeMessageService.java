package ua.edu.ukma.ykrukovska.practice2.messages;

import ua.edu.ukma.ykrukovska.practice2.channel.Channel;

import static java.lang.System.out;
import static java.lang.Thread.sleep;

public class FakeMessageService<Message> extends AbstractMessageGenerator<Message> {
    private final MessageCreator<Message> creator;
    private long SLEEP_TIME = 1000;

    public FakeMessageService(Channel<Message> outboundChannel, MessageCreator<Message> creator) {
        super(outboundChannel);
        this.creator = creator;
    }

    @Override
    public void generateMessages() throws InterruptedException {
        if (!infiniteGeneration) {
            for (int i = 0; i < messageCount; i++) {
                outboundChannel.submit(creator.create());
            }
            out.println("Generated messages :" + messageCount);
        } else {

                new Thread(() -> {
                    while (!stopGeneration) {
                        Message message = creator.create();
                        try {
                            outboundChannel.submit(message);
                            sleep(SLEEP_TIME);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        out.println("Generated message :" + message.toString());
                    }
                }).start();

        }
    }
}
