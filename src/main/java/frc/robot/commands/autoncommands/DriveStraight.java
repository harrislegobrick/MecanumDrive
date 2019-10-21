/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autoncommands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

public class DriveStraight extends TimedCommand {
  public enum DDirection {
    FORWARD, BACKWARD, LEFT, RIGHT
  }

  private DDirection direction;
  private double driveSpeed, driveDirection, turn;
  private double kP = 0.05;
  private double kI = 0.01;
  private double kD = 0.04;

  public DriveStraight(double time, double driveSpeed, DDirection direction) {
    super(time);
    requires(Robot.drivetrain);
    this.driveSpeed = Math.abs(driveSpeed);
    this.direction = direction;
  }

  @Override 
  protected void initialize() {
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
    Robot.drivetrain.auton(driveSpeed, driveDirection, turn);
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