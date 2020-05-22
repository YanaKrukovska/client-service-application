package ua.edu.ukma.ykrukovska.practice2;

import ua.edu.ukma.ykrukovska.practice2.decriptor.DecriptorService;
import ua.edu.ukma.ykrukovska.practice2.message.Message;

public class ReceiverImpl implements Receiver {


    private DecriptorService decriptorService;

    public ReceiverImpl(DecriptorService decriptorService) {
        this.decriptorService = decriptorService;
    }
    // private Logger logger = LoggerFactory.getLogger(ReceiverImpl.class);

    @Override
    public void receiveMessage(Message message) {
        //logger.debug("Received: {}", message.toString());
        System.out.println("Receiver " + message.toString());
        decriptorService.putForDecription(message);
    }
}
