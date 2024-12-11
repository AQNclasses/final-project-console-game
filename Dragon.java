import java.util.List;
     public class Dragon extends Item{
        public int fireBreathDamage;
        public int flightSpeed;
        public Dragon(String name, List<String> types, String desc, String use, String action, int fireBreathDamage, int flightSpeed) {
            super(name, types, desc, use, action);
            this.fireBreathDamage = fireBreathDamage;
            this.flightSpeed = flightSpeed;

        }
    public int getFireBreathDamage() {
        return fireBreathDamage;
    }

    public int getFlightSpeed() {
        return flightSpeed;
    }
    @Override
    public String toString() {
        return this.name;
    }

    
}
