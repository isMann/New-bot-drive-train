package ca._4976.io;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;


public class Input {

    public enum Digital {
        ELEVATOR_GROUND(3),
        IR_L(1),
        ELEVATOR_TOP(4),
        IR_R(2);

        private DigitalInput di;

        Digital(int id) { di = new DigitalInput(id); }

        public boolean get() {
            return di.get();
        }
    }

    public enum DigitalEncoder {
        DRIVE_LEFT(0, 1, 2, 2.27E-4),
        //DRIVE_RIGHT(3, 4, 5, 0.025),
        ELEVATOR(6, 7, 8,  2E-1);

        private Encoder encoder;
        private double distance;

        DigitalEncoder(int dio1, int dio2, int dio3, double dpp) {
            encoder = new Encoder(dio1, dio2, dio3);
            encoder.setDistancePerPulse(dpp);
        }

        public double getDistance() {
            distance += encoder.getDistance();
            encoder.reset();
            return distance;
        }

        public void set(double value) {
            encoder.reset();
            distance = value;
        }

        public void reset() {
            encoder.reset();
            distance = 0;
        }

    }

    public enum AnalogGyro {

        DRIVE(0);


        private boolean initalized = false;

        AnalogGyro(int analogIn) {

        }




        public boolean isInitalized() {
            return initalized;
        }

        public void reset() {
        }

    }
}

