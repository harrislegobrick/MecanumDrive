/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // motors 
  public static final byte FRONT_LEFT_MOTOR = 0;
  public static final byte FRONT_RIGHT_MOTOR = 1;
  public static final byte BACK_LEFT_MOTOR = 2;
  public static final byte BACK_RIGHT_MOTOR = 3;

  // input
  public static final byte JOY_PORT = 0;
  public static final double JOY_DEADZONE = 0.05;
}
