/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.CalibrateLimelight;
import frc.robot.commands.MecanumDriveWithStick;
import frc.robot.commands.MecanumDriveWithStick.Orientation;
import frc.robot.commands.autoncommands.LimelightTrackToTarget;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  private static Joystick stick = new Joystick(RobotMap.JOY_PORT);
  private static Button fieldOriented, robotOriented, trackToTarget, calibrateLimelight;


  public static void init() {
    robotOriented = new JoystickButton(stick, 3);
    fieldOriented = new JoystickButton(stick, 4);
    trackToTarget = new JoystickButton(stick, 5);
    calibrateLimelight = new JoystickButton(stick, 6);
    
   
    fieldOriented.whenPressed(new MecanumDriveWithStick(Orientation.FIELD));
    robotOriented.whenPressed(new MecanumDriveWithStick(Orientation.ROBOT));
    trackToTarget.whileHeld(new LimelightTrackToTarget());
    calibrateLimelight.toggleWhenPressed(new CalibrateLimelight());
  }

  public static double getJoyY() {
    return stick.getY();
  }
  
  public static double getJoyX() {
    return stick.getX();
  }

  public static double getJoyZ() {
    return stick.getZ();
  }

  public static double getJoyThrottle() {
    return stick.getThrottle();
  }
}