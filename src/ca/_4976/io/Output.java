package ca._4976.io;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;

public class Output {

    public enum Digital {

        LED(5);

        private DigitalOutput di;

        Digital(int id) {
            di = new DigitalOutput(id);
        }

        public void set(boolean value) { di.set(value); }
    }

    public enum Motor {
        DRIVE_LEFT_1(0, 1.0, new CANTalon(11)),
        DRIVE_LEFT_2(1, 1.0, new CANTalon(12)),
        DRIVE_RIGHT_1(2, 1.0, new CANTalon(13)),
        DRIVE_RIGHT_2(3, 1.0, new CANTalon(14)),
        ELEVATOR(11, 0.75, new Talon(0)),
        GRIPPER_LEFT(12, 1, new Talon(1)),
        GRIPPER_RIGHT(13, 1,new Talon(2));
        public int id;
        public Object motor;

        public double modifier;

        Motor(int id, double modifier, Object motor) {
            this.id = id;
            this.motor = motor;
            this.modifier = modifier;
        }

        public void set(double speed) {

            if (motor instanceof Talon)
                ((Talon) motor).set(speed * modifier);
            else if (motor instanceof CANTalon)
                ((CANTalon) motor).set(speed * modifier);
        }

        public double getCurrent() {
            if (motor instanceof CANTalon)
                return ((CANTalon) motor).getOutputCurrent();
            return 0;
        }

        public double getSpeed() {

            if (motor instanceof Talon)
                return ((Talon) motor).getSpeed();
            else if (motor instanceof CANTalon)
                return ((CANTalon) motor).getSpeed();
            return 0;
        }

        public void enableBrake(boolean isEnabled) {

            if (motor instanceof CANTalon)
                ((CANTalon) motor).enableBrakeMode(isEnabled);
        }


    }

    public enum PneumaticSolenoid {
        Gear_Shift(0, 4),
        CLAW_OPEN(2,3),
        CLAW_UP(4,5);

        public int port1, port2;
        public DoubleSolenoid solenoid;

        PneumaticSolenoid(int port1, int port2) {
            this.port1 = port1;
            this.port2 = port2;
            solenoid = new DoubleSolenoid(20, this.port1, this.port2);
        }

        public void set(boolean extend) {
            if (extend)
                set(DoubleSolenoid.Value.kForward);
            else
                set(DoubleSolenoid.Value.kReverse);
        }

        public void set (DoubleSolenoid.Value value) {
            solenoid.set(value);
        }

    }

}
// Waffle
