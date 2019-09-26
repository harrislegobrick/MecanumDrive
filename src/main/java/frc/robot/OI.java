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
import frc.robot.commands.MeecanumDrive;
import frc.robot.commands.autoncommands.LimelightTrackToTarget;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  private static Joystick stick = new Joystick(RobotMap.JOY_PORT);
  private static Button fieldOriented, robotOriented, trackToTarget;

  public static void init(){
    robotOriented = new JoystickButton(stick, 3);
    fieldOriented = new JoystickButton(stick, 4);
    trackToTarget = new JoystickButton(stick, 5);
    
    
    fieldOriented.whenPressed(new MeecanumDrive(true));
    robotOriented.whenPressed(new MeecanumDrive(false));
    trackToTarget.whileHeld(new LimelightTrackToTarget());
  }

  public double getJoyY() {
    double raw = stick.getY();
    return Math.abs(raw) < RobotMap.JOY_DEAD_ZONE ? 0.0 : raw;
  }
  public double getJoyX() {
    double raw = stick.getX();
    return Math.abs(raw) < RobotMap.JOY_DEAD_ZONE ? 0.0 : raw;
  }
  public double getJoyZ() {
    double raw = stick.getZ();
    return Math.abs(raw) < RobotMap.JOY_DEAD_ZONE ? 0.0 : raw;
  }
  public double getJoyThrottle(){
    return stick.getThrottle();
  }

}
