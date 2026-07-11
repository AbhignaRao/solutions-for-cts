public class Main {
    public static void main(String[] args) {

        // Gaming PC config
        Computer gamingPC = new Computer.Builder()
                .setCPU("Intel i9")
                .setRAM("32GB")
                .setStorage("1TB SSD")
                .setGPU("NVIDIA RTX 4090")
                .setBluetoothEnabled(true)
                .setWifiEnabled(true)
                .build();

        // Office PC config
        Computer officePC = new Computer.Builder()
                .setCPU("Intel i5")
                .setRAM("8GB")
                .setStorage("512GB SSD")
                .setGPU("Integrated")
                .setBluetoothEnabled(false)
                .setWifiEnabled(true)
                .build();

        System.out.println("Gaming PC: " + gamingPC);
        System.out.println("Office PC: " + officePC);
    }
}