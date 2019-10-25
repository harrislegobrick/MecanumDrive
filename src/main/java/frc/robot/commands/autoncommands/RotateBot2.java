/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autoncommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RotateBot2 extends Command {
  public enum Rotate {
    CLOCKWISE, COUNTER_CLOCKWISE
  }

  private Rotate direction;
  private double degrees, initalHeading;
  private double speed = 0.3;
  private double exactness = 0.95;

  public RotateBot2(double degrees, Rotate direction) {
    requires(Robot.drivetrain);
    this.degrees = degrees;
    this.direction = direction;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    initalHeading = Robot.drivetrain.getGyroo();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.drivetrain.rotate(direction == Rotate.CLOCKWISE ? speed : -speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (Robot.drivetrain.getGyroo()
        - initalHeading) >= ((initalHeading + (direction == Rotate.CLOCKWISE ? degrees : -degrees)) * exactness);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivetrain.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
