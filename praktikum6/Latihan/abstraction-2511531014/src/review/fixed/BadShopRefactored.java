package review.fixed;

import java.util.ArrayList;
import java.util.List;

/*
 * T3 - CODE REVIEW (refactored)
 * Diperbaiki: masalah 1 (interface gemuk), 2 (switch string -> enum),
 * 3 (Address mutable -> immutable).
 */

// ---------- Masalah 2: enum menggantikan switch pada String ----------
enum ShippingType {
    REGULAR(10_000),
    EXPRESS(18_000),
    SAME_DAY(30_000);

    private final long costPerKg;

    ShippingType(long costPerKg) {
        this.costPerKg = costPerKg;
    }

    long costPerKg() {
        return costPerKg;
    }
}

abstract class Courier {
    public long cost(ShippingType type, int kilograms) {
        return kilograms * type.costPerKg();
    }
}

class JneCourier extends Courier {
}

// ---------- Masalah 1: interface dipecah ----------
interface Payable {
    boolean pay(long amount);
}

interface Refundable {
    boolean refund(long amount);
}

interface Printable {
    String printInvoice();
}

class GiftVoucher implements Payable, Printable {
    private double balance = 100_000;

    @Override
    public boolean pay(long amount) {
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }

    @Override
    public String printInvoice() {
        return "Voucher, remaining balance " + balance;
    }
}

// ---------- Tidak diubah (masalah 4, 5, 6, 7 dibiarkan) ----------
class Cart {
    private final List<Item> items = new ArrayList<>();

    class Item {
        private final String name;
        private final long price;
        private final int quantity;

        Item(String name, long price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        long subtotal() {
            return price * quantity;
        }

        String getName() {
            return name;
        }
    }

    void add(Item item) {
        items.add(item);
    }

    long total() {
        long total = 0;
        for (Item item : items) {
            total += item.subtotal();
        }
        return total;
    }
}

// ---------- Masalah 3: Address immutable ----------
final class Address {
    private final String street;
    private final String city;

    Address(String street, String city) {
        this.street = street;
        this.city = city;
    }

    Address withCity(String newCity) {
        return new Address(street, newCity);
    }

    @Override
    public String toString() {
        return street + ", " + city;
    }
}

class SimpleOrder {
    private final String id;
    private final Address address;

    SimpleOrder(String id, Address address) {
        this.id = id;
        this.address = address;
    }

    @Override
    public String toString() {
        return id + " -> " + address;
    }
}

interface Discount {
    default long apply(long price) {
        if (this instanceof MemberDiscount) {
            return price - price * 5 / 100;
        } else if (this instanceof FlashSaleDiscount) {
            return price - price * 30 / 100;
        }
        return price;
    }
}

class MemberDiscount implements Discount {
}

class FlashSaleDiscount implements Discount {
}

public class BadShopRefactored {
    public static void main(String[] args) {
        Cart cart = new Cart();
        cart.add(cart.new Item("Kopi Gayo", 85_000, 2));
        System.out.println("Total cart: " + cart.total());

        Courier courier = new JneCourier();
        System.out.println("Ongkir EXPRESS 2 kg: " + courier.cost(ShippingType.EXPRESS, 2));

        Address home = new Address("Jl. Sudirman 10", "Padang");
        SimpleOrder first = new SimpleOrder("ORD-1", home);
        SimpleOrder second = new SimpleOrder("ORD-2", home.withCity("Bukittinggi"));
        System.out.println(first);
        System.out.println(second);
    }
}