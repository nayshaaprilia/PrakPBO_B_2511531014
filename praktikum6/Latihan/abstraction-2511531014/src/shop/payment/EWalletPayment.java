package shop.payment;

public class EWalletPayment implements RefundablePayment {

    private String name;
    private long balance;

    public EWalletPayment(String name, long balance) {
        this.name = name;
        this.balance = balance;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean pay(long amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }

        balance -= amount;
        return true;
    }

    @Override
    public boolean refund(long amount) {
        if (amount <= 0) {
            return false;
        }

        balance += amount;
        return true;
    }

    public long getBalance() {
        return balance;
    }
}