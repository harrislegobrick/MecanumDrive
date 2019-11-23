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
  private static Servo tracker;

  public LimelightTurret() {
    tracker = new Servo(RobotMap.OtherMotors.LIMELIGHT_SERVO);
  }

  public void moveBy(double value) {
    tracker.setAngle(value + getAngle());
  }

  public void setServoAngle(double value) {
    tracker.setAngle(value);
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

  /**
   * @return The rotation from -90 to 90 degrees where 0 degrees is facing forward
   */
  public double getLLHeading() {
    return (-180 * tracker.get()) + 90;
  }

  public void center() {
    // 180 is full left, 0 is full right
    tracker.setAngle(90);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ConstantLimelightTracker());
  }
}