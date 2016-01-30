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
    AHRS navX = new AHRS(SerialPort.Port.kMXP);


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

    //
// Aavin@4976-Server MINGW64 ~/IdeaProjects/New bot drive train
$ git init
Initialized empty Git repository in C:/Users/Aavin/IdeaProjects/New bot drive train/.git/

Aavin@4976-Server MINGW64 ~/IdeaProjects/New bot drive train (master)
$ git remote add https://github.com/Waffle-Lord/New-bot-drive-train.git
usage: git remote add [<options>] <name> <url>

    -f, --fetch           fetch the remote branches
    --tags                import all tags and associated objects when fetching
                          or do not fetch any tag at all (--no-tags)
    -t, --track <branch>  branch(es) to track
    -m, --master <branch>
                          master branch
    --mirror[=<push|fetch>]
                          set up remote as a mirror to push to or fetch from


Aavin@4976-Server MINGW64 ~/IdeaProjects/New bot drive train (master)
$ add *
bash: add: command not found

Aavin@4976-Server MINGW64 ~/IdeaProjects/New bot drive train (master)
$ git add*
git: 'add*' is not a git command. See 'git --help'.

Did you mean this?
        add

Aavin@4976-Server MINGW64 ~/IdeaProjects/New bot drive train (master)
$ git add *
The following paths are ignored by one of your .gitignore files:
New bot drive train.iml
Use -f if you really want to add them.
warning: LF will be replaced by CRLF in src/ca/_4976/io/Controller.java.
The file will have its original line endings in your working directory.
warning: LF will be replaced by CRLF in src/ca/_4976/io/Input.java.
The file will have its original line endings in your working directory.
warning: LF will be replaced by CRLF in src/ca/_4976/io/NetworkVariables.java.
The file will have its original line endings in your working directory.
warning: LF will be replaced by CRLF in src/ca/_4976/io/Output.java.
The file will have its original line endings in your working directory.

Aavin@4976-Server MINGW64 ~/IdeaProjects/New bot drive train (master)
$ git commit -m "What ever you want"
[master (root-commit) c73a997] What ever you want
warning: LF will be replaced by CRLF in src/ca/_4976/io/Controller.java.
The file will have its original line endings in your working directory.
warning: LF will be replaced by CRLF in src/ca/_4976/io/Input.java.
The file will have its original line endings in your working directory.
warning: LF will be replaced by CRLF in src/ca/_4976/io/NetworkVariables.java.
The file will have its original line endings in your working directory.
warning: LF will be replaced by CRLF in src/ca/_4976/io/Output.java.
The file will have its original line endings in your working directory.
 8 files changed, 773 insertions(+)
 create mode 100644 build.properties
 create mode 100644 build.xml
 create mode 100644 src/ca/_4976/Main.java
 create mode 100644 src/ca/_4976/io/Controller.java
 create mode 100644 src/ca/_4976/io/Input.java
 create mode 100644 src/ca/_4976/io/NetworkVariables.java
 create mode 100644 src/ca/_4976/io/Output.java
 create mode 100644 src/ca/_4976/sub/DriveTrain.java

Aavin@4976-Server MINGW64 ~/IdeaProjects/New bot drive train (master)
$ git push -f origin
warning: push.default is unset; its implicit value has changed in
Git 2.0 from 'matching' to 'simple'. To squelch this message
and maintain the traditional behavior, use:

  git config --global push.default matching

To squelch this message and adopt the new behavior now, use:

  git config --global push.default simple

When push.default is set to 'matching', git will push local branches
to the remote branches that already exist with the same name.

Since Git 2.0, Git defaults to the more conservative 'simple'
behavior, which only pushes the current branch to the corresponding
remote branch that 'git pull' uses to update the current branch.

See 'git help config' and search for 'push.default' for further information.
(the 'simple' mode was introduced in Git 1.7.11. Use the similar mode
'current' instead of 'simple' if you sometimes use older versions of Git)

fatal: The current branch master has no upstream branch.
To push the current branch and set the remote as upstream, use

    git push --set-upstream origin master


Aavin@4976-Server MINGW64 ~/IdeaProjects/New bot drive train (master)
$ git push -f origin master
fatal: 'origin' does not appear to be a git repository
fatal: Could not read from remote repository.

Please make sure you have the correct access rights
and the repository exists.

Aavin@4976-Server MINGW64 ~/IdeaProjects/New bot drive train (master)
$ git remote add origin https://github.com/Waffle-Lord/New-bot-drive-train.git

Aavin@4976-Server MINGW64 ~/IdeaProjects/New bot drive train (master)
$ git push -f origin master
remote: Invalid username or password.
fatal: Authentication failed for 'https://github.com/Waffle-Lord/New-bot-drive-train.git/'

Aavin@4976-Server MINGW64 ~/IdeaProjects/New bot drive train (master)
$ git push -f origin master
remote: Invalid username or password.
fatal: Authentication failed for 'https://github.com/Waffle-Lord/New-bot-drive-train.git/'

Aavin@4976-Server MINGW64 ~/IdeaProjects/New bot drive train (master)
$ git push -f origin master
Counting objects: 15, done.
Delta compression using up to 4 threads.
Compressing objects: 100% (12/12), done.
Writing objects: 100% (15/15), 5.31 KiB | 0 bytes/s, done.
Total 15 (delta 0), reused 0 (delta 0)
To https://github.com/Waffle-Lord/New-bot-drive-train.git
 * [new branch]      master -> master

Aavin@4976-Server MINGW64 ~/IdeaProjects/New bot drive train (master)
$ git push -f origin master
Everything up-to-date

Aavin@4976-Server MINGW64 ~/IdeaProjects/New bot drive train (master)
$

