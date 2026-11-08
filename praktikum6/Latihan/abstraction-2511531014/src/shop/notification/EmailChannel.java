package shop.notification;

public class EmailChannel extends  AbstractChannel {

    public EmailChannel(){
        super("Email");
    }


    @Override
    protected String resolveRecipient(CustomerContact contact) {
        String email = contact.getEmail();
        return email.contains("@") ? email : null;
    }
    @Override 
    protected void deliver(String recipient, String message) {
       System.out.println("[EMAIL to " + recipient + "] " + message);
    }
}
