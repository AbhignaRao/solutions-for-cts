public class Computer {
    // attributes
    private String CPU;
    private String RAM;
    private String storage;
    private String GPU;
    private boolean bluetoothEnabled;
    private boolean wifiEnabled;

    // private constructor - only Builder can create Computer
    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.storage = builder.storage;
        this.GPU = builder.GPU;
        this.bluetoothEnabled = builder.bluetoothEnabled;
        this.wifiEnabled = builder.wifiEnabled;
    }

    @Override
    public String toString() {
        return "Computer [CPU=" + CPU + ", RAM=" + RAM + 
               ", Storage=" + storage + ", GPU=" + GPU + 
               ", Bluetooth=" + bluetoothEnabled + 
               ", WiFi=" + wifiEnabled + "]";
    }

    // static nested Builder class
    public static class Builder {
        private String CPU;
        private String RAM;
        private String storage;
        private String GPU;
        private boolean bluetoothEnabled;
        private boolean wifiEnabled;

        public Builder setCPU(String CPU) {
            this.CPU = CPU;
            return this;
        }
        public Builder setRAM(String RAM) {
            this.RAM = RAM;
            return this;
        }
        public Builder setStorage(String storage) {
            this.storage = storage;
            return this;
        }
        public Builder setGPU(String GPU) {
            this.GPU = GPU;
            return this;
        }
        public Builder setBluetoothEnabled(boolean bluetoothEnabled) {
            this.bluetoothEnabled = bluetoothEnabled;
            return this;
        }
        public Builder setWifiEnabled(boolean wifiEnabled) {
            this.wifiEnabled = wifiEnabled;
            return this;
        }

        // build() returns final Computer object
        public Computer build() {
            return new Computer(this);
        }
    }
}