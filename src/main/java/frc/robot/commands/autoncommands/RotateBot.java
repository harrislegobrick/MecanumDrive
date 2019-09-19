/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autoncommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RotateBot extends Command {
  public enum Direction {
    RIGHT, LEFT
  }

  private Direction direction;
  private double degrees, initalHeading;
  private double turnSpeed = 0.6;
  private double turnExactness = 1.0;

  public RotateBot(double degrees, Direction direction) {
    requires(Robot.drivetrain);
    this.degrees = degrees;
    this.direction = direction;
  }

  @Override
  protected void initialize() {
    initalHeading = Robot.drivetrain.getGyroo();
  }

  @Override
  protected void execute() {
    Robot.drivetrain.rodot.drivePolar(0.0, 0.0, (direction == Direction.RIGHT ? turnSpeed : -turnSpeed));
  }

  @Override
  protected boolean isFinished() {
    return (initalHeading
        + Robot.drivetrain.getGyroo()) >= ((initalHeading + (direction == Direction.RIGHT ? degrees : -degrees))
            * turnExactness);
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