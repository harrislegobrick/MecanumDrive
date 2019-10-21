/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autoncommands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

/**
 * Used to drive the robot for a time at a speed in a direction.
 * <ul>
 * <li><b>time</b> : How long it should run in seconds.</li>
 * <li><b>driveSpeed</b> : The speed at which it should run (0 to 1).</li>
 * <li><b>direction</b> : The direction it should drive (forward, backward,
 * left, and right).</li>
 * </ul>
 */
public class DriveStraight extends TimedCommand {
  public enum Drive {
    FORWARD, BACKWARD, LEFT, RIGHT
  }

  private Drive direction;
  private double driveSpeed, error, initalHeading, driveDirection;
  private double kP = 0.05;

  public DriveStraight(double time, double driveSpeed, Drive direction) {
    super(time);
    requires(Robot.drivetrain);
    this.driveSpeed = Math.abs(driveSpeed);
    this.direction = direction;
  }

  @Override
  protected void initialize() {
    initalHeading = Robot.drivetrain.getGyroo();
    switch (direction) {
    case FORWARD:
      driveDirection = 0;
      break;
    case BACKWARD:
      driveDirection = 180;
      break;
    case LEFT:
      driveDirection = -90;
      break;
    case RIGHT:
      driveDirection = 90;
      break;
    }
  }

  @Override
  protected void execute() {
    error = initalHeading - Robot.drivetrain.getGyroo();
    Robot.drivetrain.auton(driveSpeed, driveDirection, error * kP);
  }

  @Override
  protected void end() {
    Robot.drivetrain.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}