import java.util.List;
     public class Phone extends Item{
        public boolean isPhoneON = false;
        public int batteryPrecentage;
        public Phone(String name, List<String> types, String desc, String use, String action, boolean isPhoneON, int batteryPrecentage) {
            super(name, types, desc, use, action);
            this.isPhoneON = isPhoneON;
            this.batteryPrecentage = batteryPrecentage;

        }
    public boolean isPhoneON() {
        return isPhoneON;
    }

    public int battery() {
        return batteryPrecentage;
    }

    @Override
    public void use() {
        if ("call".equals(action)) {
            System.out.println("You are calling your mother but the phone dies before she picks up");
        }
    
    }
    @Override
    public String toString() {
        return this.name;
    }
}
