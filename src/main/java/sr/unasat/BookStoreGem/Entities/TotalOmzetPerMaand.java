package sr.unasat.BookStoreGem.Entities;

public class TotalOmzetPerMaand {

    private  int purchaseId;
    private int amount;
    private int totalAmount;

    public TotalOmzetPerMaand(int purchaseId, int amount, int totalAmount) {
        this.purchaseId = purchaseId;
        this.amount = amount;
        this.totalAmount = totalAmount;

    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "TotalOmzetPerMaand{" +
                "purchaseId=" + purchaseId +
                ", amount=" + amount +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
