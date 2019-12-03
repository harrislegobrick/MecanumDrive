/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autoncommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ConstantLimelightTracker extends Command {
  private final double kP = 0.1; // needs tuning
  private final double kI = 0.0; // needs tuning
  private double integrator;

  public ConstantLimelightTracker() {
    requires(Robot.limelightTurret);
    setRunWhenDisabled(true);
  }

  @Override
  protected void initialize() {
    if (!Robot.limelight.avalible())
      Robot.limelightTurret.center();
  }

  @Override
  protected void execute() {
    if (Robot.limelight.avalible()) {
      double error, output;

      error = Robot.limelight.getX();
      integrator += (error * Robot.kDefaultPeriod);
      output = error * kP + integrator * kI;

      Robot.limelightTurret.moveBy(output);
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.limelightTurret.stop();
  }
}