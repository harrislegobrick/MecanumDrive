/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CalibrateLimelight extends Command {
  /**
   * Turns on the tracking but doens't do anything with the tracking data. Used
   * for trying to calibrate the limelight.
   */
  public CalibrateLimelight() {
    requires(Robot.limelight);
  }

  @Override
  protected void initialize() {
    Robot.limelight.setTracking();
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.limelight.setDriving();
  }

  @Override
  protected void interrupted() {
    end();
  }

}
