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
import frc.robot.commands.autoncommands.ConstantLimelightTracker;

/**
 * Add your docs here.
 */
public class LimelightTurret extends Subsystem {
  private double lastServoValue;
  private static Servo tracker;

  public LimelightTurret() {
    tracker = new Servo(RobotMap.OtherMotors.LIMELIGHT_SERVO);
  }

  public void setServo(double value) {
    if (value != lastServoValue) {
      tracker.setSpeed(value);
      lastServoValue = value;
    }
  }

  public void stop() {
    tracker.stopMotor();
  }

  public double getAngle() {
    return tracker.getAngle();
  }

  public double get() {
    return tracker.get();
  }

  public void center() {
    tracker.set(0.5);
  }

  public double getFullAngle() {
    // if 0 isn't when full left, change 180 to -180 and -90 to 90
    return (180 * tracker.get() - 90);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ConstantLimelightTracker());
  }
}