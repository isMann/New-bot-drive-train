package ca._4976;

import ca._4976.sub.DriveTrain;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import ca._4976.io.*;

public class Main extends IterativeRobot {

    CANTalon talon0 = new CANTalon(11);
    CANTalon talon1 = new CANTalon(12);
    public void robotInit() { }

    public void teleopInit() { }

    public void autonomousInit() { }

    public void testInit() { }

    public void teleopPeriodic() {
        double speed = 0.60;
        talon0.set(speed);
        talon1.set(speed);

    }

    public void autonomousPeriodic() { }

    public void testPeriodic() { }
}
