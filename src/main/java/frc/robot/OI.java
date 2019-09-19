/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  private static Joystick stick = new Joystick(RobotMap.JOYPORT);

  public double getJoyY() {
    double raw = stick.getY();
    return Math.abs(raw) < RobotMap.JOYDEADZONE ? 0.0 : raw;
  }
  public double getJoyX() {
    double raw = stick.getX();
    return Math.abs(raw) < RobotMap.JOYDEADZONE ? 0.0 : raw;
  }
  public double getJoyZ() {
    double raw = stick.getZ();
    return Math.abs(raw) < RobotMap.JOYDEADZONE ? 0.0 : raw;
  }
  public double getJoyThrottle(){
    return stick.getThrottle();
  }

}
