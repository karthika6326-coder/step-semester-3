interface PaymentMethod {
    boolean pay(double amount);
}

class CreditCardPayment implements PaymentMethod {
    public boolean pay(double amount) {
        System.out.printf("Paid $%.2f using Credit Card.%n", amount);
        return true;
    }
}

class PayPalPayment implements PaymentMethod {
    public boolean pay(double amount) {
        System.out.printf("Paid $%.2f using PayPal.%n", amount);
        return true;
    }
}

class BankTransferPayment implements PaymentMethod {
    public boolean pay(double amount) {
        System.out.printf("Paid $%.2f using Bank Transfer.%n", amount);
        return true;
    }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotal() {
        return product.getPrice() * quantity;
    }

    public String getProductName() {
        return product.getName();
    }

    public int getQuantity() {
        return quantity;
    }
}

class Order {
    private Customer customer;
    private java.util.ArrayList<OrderItem> items;
    private String status;

    public Order(Customer customer) {
        this.customer = customer;
        items = new java.util.ArrayList<>();
        status = "Pending";
    }

    public void addItem(Product product, int quantity) {
        items.add(new OrderItem(product, quantity));
        System.out.println(product.getName() + " added to order.");
    }

    public double calculateTotal() {
        double total = 0;

        for (OrderItem item : items) {
            total += item.getTotal();
        }

        return total;
    }

    public void makePayment(PaymentMethod paymentMethod) {
        if (items.isEmpty()) {
            System.out.println("Cannot make payment for an empty order.");
            return;
        }

        double total = calculateTotal();

        if (paymentMethod.pay(total)) {
            status = "Paid";
            System.out.println("Order status: " + status);
        }
    }

    public void displayOrder() {
        System.out.println("Customer: " + customer.getName());

        for (OrderItem item : items) {
            System.out.println(item.getProductName()
                    + " x " + item.getQuantity());
        }

        System.out.printf("Order total: $%.2f%n", calculateTotal());
        System.out.println("Order status: " + status);
    }
}

public class P5_PaymentProcessing {
    public static void main(String[] args) {
        Customer customer = new Customer("Customer 1");

        Product laptop = new Product("Laptop", 800);
        Product mouse = new Product("Mouse", 25);

        Order order = new Order(customer);

        order.addItem(laptop, 1);
        order.addItem(mouse, 2);

        order.displayOrder();

        PaymentMethod payment = new CreditCardPayment();
        order.makePayment(payment);

        order.displayOrder();
    }
}