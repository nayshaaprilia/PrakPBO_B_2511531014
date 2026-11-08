package shop.notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationService {

    private final List<NotificationChannel> channels = new ArrayList<>();

    public void register(NotificationChannel channel) {
        if (channel == null) {
            throw new IllegalArgumentException("Channel must not be null");
        }
        channels.add(channel);
    }

    public int broadcast(CustomerContact contact, String message) {
        int delivered = 0;
        for (NotificationChannel channel : channels) {
            if (channel.send(contact, message)) {
                delivered++;
            }
        }
        return delivered;
    }
}