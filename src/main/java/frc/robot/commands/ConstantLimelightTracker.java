/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ConstantLimelightTracker extends Command {
  public ConstantLimelightTracker() {
    requires(Robot.limelightTurret);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    double goTo;
    goTo = Robot.limelight.getX() / 59.6 + 0.5 - Robot.limelightTurret.getPos();
    Robot.limelightTurret.setServo(goTo);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.limelightTurret.setServo(0.5);
  }

  @Override
  protected void interrupted() {
    end();
  }
}