package shop.payment;
import shop.StudentInfo;

public class CreditCardPayment implements RefundablePayment, InstallmentPayment {
    private String cardHolder;
    private long creditLimit;
    private long usedCredit;

    public CreditCardPayment(String cardHolder, long creditLimit) {
        this.cardHolder = cardHolder;
        this.creditLimit = creditLimit;
        this.usedCredit = 0;
    }
    @Override
    public String getName() {
        return "Kartu Kredit (" + cardHolder + ")";
    }
    @Override
    public boolean pay(long amount) {
        if (amount <= 0 || usedCredit + amount > creditLimit) {
            return false;
        }

        usedCredit += amount;
        return true;
    }
    @Override
    public boolean refund(long amount) {
        if (amount <= 0 || amount > usedCredit) {
            return false;
        }

        usedCredit -= amount;
        return true;
    }
    @Override
    public int maxInstallmentMonths() {
        return 12;
    }
    @Override 
    public long adminFee(long amount) {
        return amount * StudentInfo.CARD_ADMIN_PERCENT/100;
    }
    public long getUsedCredit() {
        return usedCredit;
    }
    @Override
     public String policy() {
    return RefundablePayment.super.policy() + "; " + InstallmentPayment.super.policy();
     }
}