package ua.edu.ukma.ykrukovska.package_sender;

public class PackageTransactionTester {

    public static void main(String[] args) {
        Client client = new Client();
        Server server = new Server();

        Package packageStart = new Package.PackageBuilder().pktId(5).len(16).msq(new Message.MessageBuilder().type(3).userId(4).message("Rita").build()).build();

        byte[] sent = server.sendPackage(packageStart);
        Package packageResult = client.receivePackage(sent);

        System.out.println(packageStart.toString());
        System.out.println(packageResult.toString());


    }


}
