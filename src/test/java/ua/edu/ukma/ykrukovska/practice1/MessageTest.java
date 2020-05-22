package ua.edu.ukma.ykrukovska.practice1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


class MessageTest {


    @Test
    void compareMessagesSame() {
        Message message1 = new Message.MessageBuilder().type(1).userId(2).message("Rita").build();
        Message message2 = new Message.MessageBuilder().type(1).userId(2).message("Rita").build();
        assertThat(message1).isEqualTo(message2);
    }

    @Test
    void compareMessagesDifferentText() {
        Message message1 = new Message.MessageBuilder().type(3).userId(4).message("Rita").build();
        Message message2 = new Message.MessageBuilder().type(3).userId(4).message("Rita!").build();
        assertThat(message1).isNotEqualTo(message2);
    }

    @Test
    void compareMessagesDifferentType() {
        Message message1 = new Message.MessageBuilder().type(2).userId(4).message("Meow").build();
        Message message2 = new Message.MessageBuilder().type(8).userId(4).message("Meow").build();
        assertThat(message1).isNotEqualTo(message2);
    }

    @Test
    void compareMessagesDifferentUserId() {
        Message message1 = new Message.MessageBuilder().type(1).userId(1).message("Meow!").build();
        Message message2 = new Message.MessageBuilder().type(1).userId(8).message("Meow!").build();
        assertThat(message1).isNotEqualTo(message2);
    }

}
