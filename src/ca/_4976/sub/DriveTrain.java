package ca._4976.sub;

import ca._4976.io.Controller;
import ca._4976.io.Output;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;

/**
 * Created by Aavin on 1/23/2016.
 */
public class DriveTrain extends IterativeRobot {
    int state = 0;
    int head1 = 0;
    int head2 = 0;
    int gear = 0;
    Joystick joystick = new Joystick(0);
    AHRS navX = new AHRS(SerialPort.Port.kMXP);
    RobotDrive move = new RobotDrive(0, 1, 2, 3);


    public void teleopPeriodic() {
        Controller.Primary.Trigger.RIGHT.value();
        Controller.Primary.Trigger.LEFT.value();
        Controller.Primary.Stick.LEFT.horizontal();

        //left motors (right - left) + left stick
        //right motors -(right - left) + left stick;


        System.out.println(navX.getYaw());
        if (state == 0) {
            if (Controller.Primary.Button.RIGHT_BUMPER.isDownOnce() == true) {
                state = 1;
            } else if (Controller.Primary.Button.LEFT_BUMPER.isDownOnce() == true) {
                state = 3;

            } else {
                Output.Motor.DRIVE_LEFT_1.set((Controller.Primary.Trigger.RIGHT.value() - Controller.Primary.Trigger.LEFT.value()) + (Controller.Primary.Stick.LEFT.horizontal()));
                Output.Motor.DRIVE_LEFT_2.set((Controller.Primary.Trigger.RIGHT.value() - Controller.Primary.Trigger.LEFT.value()) + (Controller.Primary.Stick.LEFT.horizontal()));
                Output.Motor.DRIVE_RIGHT_1.set((-Controller.Primary.Trigger.RIGHT.value() - Controller.Primary.Trigger.LEFT.value()) + (Controller.Primary.Stick.LEFT.horizontal()));
                Output.Motor.DRIVE_RIGHT_2.set((-Controller.Primary.Trigger.RIGHT.value() - Controller.Primary.Trigger.LEFT.value()) + (Controller.Primary.Stick.LEFT.horizontal()));
            }
        }
        if (state == 1) {
            head1 = (int) navX.getYaw();
            if (head1 < 180 && head1 > 90) {
                head2 = head1 - 270;

            } else {
                head2 = head1 + 90;
                state = 2;

            }
            if (state == 2) {
                if ((int) navX.getYaw() < head2 - 3 || (int) navX.getYaw() > head2 + 3) {
                    Output.Motor.DRIVE_LEFT_1.set(0.4);
                    Output.Motor.DRIVE_LEFT_2.set(0.4);
                    Output.Motor.DRIVE_RIGHT_1.set(-0.4);
                    Output.Motor.DRIVE_RIGHT_2.set(-0.4);
// <= less than       >= greater than
                } else {
                    Output.Motor.DRIVE_LEFT_1.set(0);
                    Output.Motor.DRIVE_LEFT_2.set(0);
                    Output.Motor.DRIVE_RIGHT_1.set(0);
                    Output.Motor.DRIVE_RIGHT_2.set(0);
                    state = 0;
                }
            }
            if (state == 3) {
                head1 = (int) navX.getYaw();
                if (head1 > -180 && head1 < -90) {
                    head2 = head1 + 270;
//&& = and      || = or
                } else {
                    head2 = head1 - 90;
                    state = 2;

                }
                if (state == 4) {
                    if ((int) navX.getYaw() < head2 - 3 || (int) navX.getYaw() > head2 + 3) {
                        Output.Motor.DRIVE_LEFT_1.set(-0.4);
                        Output.Motor.DRIVE_LEFT_2.set(-0.4);
                        Output.Motor.DRIVE_RIGHT_1.set(0.4);
                        Output.Motor.DRIVE_RIGHT_2.set(0.4);
// <= less than       >= greater than
                    } else {
                        Output.Motor.DRIVE_LEFT_1.set(0);
                        Output.Motor.DRIVE_LEFT_2.set(0);
                        Output.Motor.DRIVE_RIGHT_1.set(0);
                        Output.Motor.DRIVE_RIGHT_2.set(0);
                        state = 0;
                    }
                }
            }
        }
        if (Controller.Primary.DPad.NORTH.isDownOnce()) {
            Output.PneumaticSolenoid.Gear_Shift.set(true);
        } if (Controller.Primary.DPad.SOUTH.isDownOnce()){
            Output.PneumaticSolenoid.Gear_Shift.set(false);
    }


        }


    }
