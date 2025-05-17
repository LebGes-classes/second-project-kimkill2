public class StoreCell {
    private String productName;
    private int amount;
    private int price;

    public StoreCell(String productName, int amount, int price) {
        this.productName = productName;
        this.amount = amount;
        this.price = price;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void addAmount(int newAmount) {
        int finalAmount = newAmount + amount;
        setAmount(finalAmount);
    }
}
