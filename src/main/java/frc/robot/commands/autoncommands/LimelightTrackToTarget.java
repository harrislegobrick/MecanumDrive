/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autoncommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;

public class LimelightTrackToTarget extends Command {
  private double previousError;
  private final double kP = 0.03;
  private final double kD = 0.005;

  public LimelightTrackToTarget() {
    requires(Limelight.getInstance());
    requires(Drivetrain.getInstance());
  }

  @Override
  protected void initialize() {
    Limelight.getInstance().setTracking();
  }

  @Override
  protected void execute() {
    double error, derivative, zRotation;
    double magnitude = 0.2;

    error = Limelight.getInstance().getX();
    derivative = (error - previousError) / Robot.kDefaultPeriod;
    zRotation = error * kP + derivative * kD;
    magnitude -= Math.pow((0.07 * Math.pow(Math.abs(error), 0.7)), 2);

    if (Limelight.getInstance().getAvalible())
      Drivetrain.getInstance().auton(magnitude, error, zRotation);
    previousError = error;
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Drivetrain.getInstance().stop();
    Limelight.getInstance().setDriving();
  }

  @Override
  protected void interrupted() {
    end();
  }
}