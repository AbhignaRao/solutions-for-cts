public class Main {
    public static void main(String[] args) {

        // only email
        Notifier notifier = new EmailNotifier();
        System.out.println("-- Email only --");
        notifier.send("Server is down!");

        // email + SMS
        Notifier smsNotifier = new SMSNotifierDecorator(new EmailNotifier());
        System.out.println("\n-- Email + SMS --");
        smsNotifier.send("Server is down!");

        // email + SMS + Slack
        Notifier allNotifier = new SlackNotifierDecorator(
                                new SMSNotifierDecorator(
                                new EmailNotifier()));
        System.out.println("\n-- Email + SMS + Slack --");
        allNotifier.send("Server is down!");
    }
}