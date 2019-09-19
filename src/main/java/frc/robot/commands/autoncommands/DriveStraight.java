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
  private double driveSpeed, error, initalHeading;
  private double kP = 0.1;

  public DriveStraight(double timeout, double driveSpeed) {
    super(timeout);
    requires(Robot.drivetrain);
    this.driveSpeed = driveSpeed;
  }

  @Override
  protected void initialize() {
    initalHeading = Robot.drivetrain.getGyroo();
  }

  @Override
  protected void execute() {
    error = initalHeading - Robot.drivetrain.getGyroo();
    Robot.drivetrain.rodot.drivePolar(driveSpeed, 0.0, error * kP);
  }

  @Override
  protected void end() {
    Robot.drivetrain.rodot.stopMotor();
  }

  @Override
  protected void interrupted() {
    end();
  }
}