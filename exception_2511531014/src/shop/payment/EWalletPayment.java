package shop.payment;

import shop.exception.InsufficientBalanceException;
import shop.exception.PaymentFailedException;

public class EWalletPayment implements PaymentMethod {
    private final String provider;
    private long balance;

    public EWalletPayment(String provider, long balance) {
        this.provider = provider;
        this.balance = balance;
    }

    public long getBalance() { return balance; }

    @Override
    public String getName() {
        return "E-Wallet " + provider;
    }

    @Override
    public void pay(long amount) throws PaymentFailedException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (amount > balance) {
            throw new InsufficientBalanceException(amount, balance);
        }
        balance -= amount;
    }
}