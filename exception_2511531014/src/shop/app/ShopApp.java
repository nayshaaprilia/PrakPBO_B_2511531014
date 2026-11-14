package shop.app;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import shop.StudentInfo;
import shop.exception.CatalogLoadException;
import shop.exception.InsufficientBalanceException;
import shop.exception.OutOfStockException;
import shop.exception.PaymentFailedException;
import shop.io.CatalogReader;
import shop.model.Product;
import shop.payment.EWalletPayment;
import shop.payment.PaymentMethod;
import shop.service.CheckoutService;
import shop.service.Inventory;
import shop.shipping.RegularShipping;
import shop.shipping.ShippingService;

public class ShopApp {

    /** Runs one purchase and returns a one-line result. Does not print. */
    public static String buy(CheckoutService checkout, List<Product> items,
                             ShippingService shipping, PaymentMethod payment) {
        try {
            long paid = checkout.checkout(items, shipping, payment);
            return "[OK] " + payment.getName() + " membayar Rp" + paid;
        } catch (InsufficientBalanceException e) { // subclass harus di atas PaymentFailedException
            return "[SALDO] " + e.getMessage() + " (kurang Rp" + e.getShortfall() + ")";
        } catch (PaymentFailedException e) {
            return "[BAYAR] " + e.getMessage();
        } catch (OutOfStockException e) {
            return "[STOK] " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        String fileName = args.length > 0 ? args[0] : "katalog.csv";
        CatalogReader reader = new CatalogReader();
        List<Product> catalog;
        try {
            catalog = reader.load(Path.of(fileName));
        } catch (CatalogLoadException e) {
            System.out.println("Gagal memuat katalog: " + e.getMessage());
            if (e.getCause() != null) {
                System.out.println("Penyebab: " + e.getCause().getMessage());
            }
            return;
        }
        System.out.println("Katalog dimuat: " + catalog.size() + " produk, "
                + reader.getSkippedCount() + " baris dilewati");

        System.out.println("== Toko Online UMKM ==");
        for (int i = 0; i < catalog.size(); i++) {
            System.out.println((i + 1) + ". " + catalog.get(i).getInfo());
        }

        List<Product> cart = new ArrayList<>();
        try (Scanner in = new Scanner(System.in)) {
            while (true) {
                System.out.print("Nomor produk (0 = selesai): ");
                int choice;
                try {
                    choice = Integer.parseInt(in.nextLine().trim());
                } catch (NumberFormatException _) {
                    System.out.println(" Input harus berupa angka.");
                    continue;
                }
                if (choice == 0) {
                    break;
                }
                if (choice < 1 || choice > catalog.size()) {
                    System.out.println(" Nomor produk tidak ada.");
                    continue;
                }
                Product product = catalog.get(choice - 1);

                System.out.print("Jumlah: ");
                int quantity;
                try {
                    quantity = Integer.parseInt(in.nextLine().trim());
                } catch (NumberFormatException _) {
                    System.out.println(" Input harus berupa angka.");
                    continue;
                }
                if (quantity <= 0) {
                    System.out.println(" Jumlah harus lebih dari 0.");
                    continue;
                }
                for (int i = 0; i < quantity; i++) {
                    cart.add(product);
                }
                System.out.println("  + " + quantity + " x " + product.getName());
            }
        }

        if (cart.isEmpty()) {
            System.out.println("Keranjang kosong, tidak ada yang dibeli.");
            return;
        }

        Inventory inventory = new Inventory();
        for (Product product : catalog) {
            int stock = switch (product.getSku()) {
                case "KOPI-001" -> StudentInfo.coffeeStock();
                case "RDG-002" -> StudentInfo.rendangStock();
                default -> 5;
            };
            inventory.addStock(product.getSku(), stock);
        }

        CheckoutService checkout = new CheckoutService(inventory);
        ShippingService regular = new RegularShipping();
        EWalletPayment wallet = new EWalletPayment("DompetKu", StudentInfo.walletBalance());
        System.out.println("\nKeranjang: " + cart.size() + " barang, total Reguler Rp"
                + checkout.total(cart, regular));
        System.out.println(buy(checkout, cart, regular, wallet));
        System.out.println("Sisa saldo: Rp" + wallet.getBalance());
    }
}