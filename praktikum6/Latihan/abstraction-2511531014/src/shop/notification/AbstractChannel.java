package shop.notification;

public abstract class AbstractChannel implements NotificationChannel {
    private final String channelName;
    private int sentCount;
    private String lastDelivered = "";

    protected AbstractChannel(String channelName) {
        if (channelName == null || channelName.isBlank()) {
            throw new IllegalArgumentException("Channel name must not be blank");
        }
        this.channelName = channelName;
    }

    @Override
    public String channelName() {
        return channelName;
    }

    // urutan langkah dikunci
    @Override
    public final boolean send(CustomerContact contact, String message) {
        if (contact == null || message == null || message.isBlank()) {
            return false; // 1. Tolak pesan jika kosong
        }
        String recipient = resolveRecipient(contact); // 2. cari alamat penerima
        if (recipient == null) {
            return false; // 3.Tolak jika tidak ada alamat penerima
        }
        String prepared = prepare(message); // 4.Pesan disiapkan
        deliver(recipient, prepared); // 5.Pesan dikirim
        sentCount++; // 6.Hitung pesan yang dikirim
        lastDelivered = prepared;
        return true;
    }

    /**
     * @return alamat tujuan, atau null jika kontak tidak bisa dihubungi lewat kanal
     *         ini
     */
    protected abstract String resolveRecipient(CustomerContact contact);

    protected abstract void deliver(String recipient, String message);

    /** Hook: boleh di-override. Default: pesan apa adanya. */
    protected String prepare(String message) {
        return message;
    }

    public int getSentCount() {
        return sentCount;
    }

    public String getLastDelivered() {
        return lastDelivered;
    }

}
