/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Limelight;

public class CalibrateLimelight extends Command {
  /**
   * Turns on the tracking but doens't do anything with the tracking data. Used
   * for trying to calibrate the limelight.
   */
  public CalibrateLimelight() {
    requires(Limelight.getInstance());
  }

  @Override
  protected void initialize() {
    Limelight.setTracking();
    Limelight.setPiPMain();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Limelight.setDriving();
    Limelight.setPiPSecondary();
  }
}