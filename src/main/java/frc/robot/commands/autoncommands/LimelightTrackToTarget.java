/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autoncommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LimelightTrackToTarget extends Command {
  private double angle, zRotation;
  private double magnitude = 0.3; // Robot.limelight.getArea() * 0.2; gotta figure out what the area would be
  private final double kP = 0.04;

  public LimelightTrackToTarget() {
    requires(Robot.limelight);
    requires(Robot.drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.limelight.setTracking();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    angle = Robot.limelight.getX();
    zRotation = angle * kP;
    magnitude -= Math.pow((0.07 * Math.pow(Math.abs(angle), 0.7)), 2);

    if (Robot.limelight.getAvalible())
      Robot.drivetrain.auton(magnitude, angle, zRotation);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivetrain.stop();
    Robot.limelight.setDriving();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
