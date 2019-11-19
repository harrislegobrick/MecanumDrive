/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ConstantLimelightTracker;

/**
 * Add your docs here.
 */
public class LimelightTurret extends Subsystem {
  private double lastServoValue;
  private static Servo tracker;

  public LimelightTurret() {
    init();
  }

  public void init() {
    tracker = new Servo(RobotMap.OtherMotors.LIMELIGHT_SERVO);
  }

  public void setServo(double value) {
    if (value != lastServoValue) {
      tracker.set(value);
      lastServoValue = value;
    }
  }

  public double getPos() {
    return tracker.get();
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ConstantLimelightTracker());
  }

}