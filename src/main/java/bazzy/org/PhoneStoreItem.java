package bazzy.org;

public class PhoneStoreItem {
    String brand;
    String model;
    String os;
    int ram;
    int internalStorage;

    public PhoneStoreItem(String brand, String model, String os, int ram, int internalStorage) {
        this.brand = brand;
        this.model = model;
        this.os = os;
        this.ram = ram;
        this.internalStorage = internalStorage;
    }

    @Override
    public String toString() {
        return "PhoneStoreItem{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", OS='" + os + '\'' +
                ", RAM=" + ram + "GB" +
                ", internalStorage=" + internalStorage + "GB" +
                '}';
    }
}
