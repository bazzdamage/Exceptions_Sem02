package bazzy.org;

public class PhoneStoreItem {
    String brand;
    String model;
    String os;
    int ram;
    int internalStorage;


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getInternalStorage() {
        return internalStorage;
    }

    public void setInternalStorage(int internalStorage) {
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
