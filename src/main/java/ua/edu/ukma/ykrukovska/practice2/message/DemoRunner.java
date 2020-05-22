package ua.edu.ukma.ykrukovska.practice2.message;

import ua.edu.ukma.ykrukovska.practice2.Receiver;
import ua.edu.ukma.ykrukovska.practice2.ReceiverImpl;
import ua.edu.ukma.ykrukovska.practice2.decriptor.DecriptorService;

public class DemoRunner {

    public static void main(String[] args) {

        Receiver receiver = new ReceiverImpl(new DecriptorService());
        MessageGenerator messageGenerator = new MessageGenerator(receiver);

        messageGenerator.sendMessages(100);


    }
}
