/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class CalibrateLimelight extends Command {
  /**
   * Add your docs here.
   */
  public CalibrateLimelight() {
    super();
    requires(Robot.limelight);
  }

  // Called once when the command executes
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