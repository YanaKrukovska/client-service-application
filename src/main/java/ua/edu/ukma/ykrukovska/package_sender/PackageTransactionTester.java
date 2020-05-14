package ua.edu.ukma.ykrukovska.package_sender;

public class PackageTransactionTester {

    public static void main(String[] args) {
        Client client = new Client();
        Server server = new Server();

        Message message = new Message.MessageBuilder().type(3).userId(4).message("Rita").build();
        Package packageStart = new Package.PackageBuilder().pktId().len(message.size()).msq(message).build();
        Package packageStart1 = new Package.PackageBuilder().pktId().len(message.size()).msq(message).build();

        byte[] sent = server.sendPackage(packageStart);
        Package packageResult = client.receivePackage(sent);

        System.out.println(packageStart.toString());
       // System.out.println(packageStart1.toString());
        System.out.println(packageResult.toString());


    }


}
