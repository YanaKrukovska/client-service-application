package ua.edu.ukma.ykrukovska.package_sender;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


class PackageTest {


    @Test
    void createNewPktId() {
        Message message = new Message.MessageBuilder().type(1).userId(2).message("Rita").build();
        Package package1 = new Package.PackageBuilder().pktId().len(message.size()).msq(message).build();
        Package package2 = new Package.PackageBuilder().pktId().len(message.size()).msq(message).build();
        assertThat(package1.getPktId()).isNotEqualTo(package2.getPktId());
    }


    @Test
    void comparePackageSame() {
        Message message = new Message.MessageBuilder().type(1).userId(2).message("Rita").build();
        Package package1 = new Package.PackageBuilder().pktId(1).len(message.size()).msq(message).build();
        Package package2 = new Package.PackageBuilder().pktId(1).len(message.size()).msq(message).build();
        assertThat(package1).isEqualTo(package2);
    }

    @Test
    void comparePackageDifferentMessage() {
        Message message1 = new Message.MessageBuilder().type(1).userId(2).message("Rita").build();
        Message message2 = new Message.MessageBuilder().type(1).userId(2).message("Panas").build();
        Package package1 = new Package.PackageBuilder().pktId(1).len(message1.size()).msq(message1).build();
        Package package2 = new Package.PackageBuilder().pktId(1).len(message2.size()).msq(message2).build();
        assertThat(package1).isNotEqualTo(package2);
    }

    @Test
    void comparePackageDifferentSrc() {
        Server server1 = new Server(0);
        Server server2 = new Server(1);
        Message message = new Message.MessageBuilder().type(1).userId(2).message("Rita").build();
        Package package1 = new Package.PackageBuilder().src(server1.getClientId()).pktId().len(message.size()).msq(message).build();
        Package package2 = new Package.PackageBuilder().src(server2.getClientId()).pktId().len(message.size()).msq(message).build();
        assertThat(package1.getPktId()).isNotEqualTo(package2.getPktId());
    }

    @Test
    void comparePackageDifferentId() {
        Message message = new Message.MessageBuilder().type(1).userId(2).message("Rita").build();
        Package package1 = new Package.PackageBuilder().pktId(1).len(message.size()).msq(message).build();
        Package package2 = new Package.PackageBuilder().pktId(2).len(message.size()).msq(message).build();
        assertThat(package1).isNotEqualTo(package2);
    }

}
