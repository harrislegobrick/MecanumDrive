/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autoncommands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

public class DriveStraight extends TimedCommand {
  public enum Drive {
    FORWARD(0), BACKWARD(180), LEFT(-90), RIGHT(90);

    private final int value;

    private Drive(int value) {
      this.value = value;
    }

    public int getValue() {
      return this.value;
    }
  }

  private Drive direction;
  private double driveSpeed, error, initalHeading;
  private double kP = 0.05;

  /**
   * Used to drive the robot for a time at a speed in a direction.
   * 
   * @param time       : How long it should run in seconds.
   * @param driveSpeed : The speed at which it should run (0 to 1).
   * @param direction  : The direction it should drive (forward, backward, left,
   *                   and right).
   */
  public DriveStraight(double time, double driveSpeed, Drive direction) {
    super(time);
    requires(Robot.drivetrain);
    this.driveSpeed = Math.abs(driveSpeed);
    this.direction = direction;
  }

  @Override
  protected void initialize() {
    initalHeading = Robot.drivetrain.getGyroo();
  }

  @Override
  protected void execute() {
    error = initalHeading - Robot.drivetrain.getGyroo();
    Robot.drivetrain.auton(driveSpeed, direction.getValue(), error * kP);
  }

  @Override
  protected void end() {
    Robot.drivetrain.stop();
  }
}