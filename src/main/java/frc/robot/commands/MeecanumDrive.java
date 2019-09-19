/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MeecanumDrive extends Command {
  public MeecanumDrive() {
    requires(Robot.drivetrain);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    double throttle = (1.0 - Robot.oi.getJoyThrottle()) / -2.0;
    Robot.drivetrain.rodot.driveCartesian(Robot.oi.getJoyY() * throttle, Robot.oi.getJoyX() * throttle, Robot.oi.getJoyZ() * throttle);

    // uncoment this for field oriented driving
    //Robot.drivetrain.rodot.driveCartesian(Robot.oi.getJoyY() * throttle, Robot.oi.getJoyX() * throttle, Robot.oi.getJoyZ() * throttle, Robot.drivetrain.getGyroo());
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}