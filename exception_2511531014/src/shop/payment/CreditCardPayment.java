package shop.payment;

import shop.exception.InsufficientBalanceException;
import shop.exception.PaymentFailedException;

public class CreditCardPayment implements PaymentMethod {
    private final String holder;
    private final long creditLimit;
    private long usedCredit = 0;

    public CreditCardPayment(String holder, long creditLimit) {
        this.holder = holder;
        this.creditLimit = creditLimit;
    }

    public long getUsedCredit() {
        return usedCredit;
    }

    @Override
    public String getName() {
        return "Kartu Kredit " + holder;
    }

    @Override
    public void pay(long amount) throws PaymentFailedException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        long remaining = creditLimit - usedCredit;
        if (amount > remaining) {
            throw new InsufficientBalanceException(amount, remaining);
        }
        usedCredit += amount;
    }
}