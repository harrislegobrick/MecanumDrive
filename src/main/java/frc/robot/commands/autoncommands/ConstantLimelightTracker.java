/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autoncommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.LimelightTurret;

public class ConstantLimelightTracker extends Command {
  private final double kP = 0.1; // needs tuning
  private final double kI = 0.0; // needs tuning
  private final double kD = 0.0; // needs tuning
  private double integrator, lastTimestamp, lastError;

  public ConstantLimelightTracker() {
    requires(LimelightTurret.getInstance());
    setRunWhenDisabled(true);
  }

  @Override
  protected void initialize() {
    if (!Limelight.avalible())
      LimelightTurret.center();
    lastTimestamp = Timer.getFPGATimestamp();
  }

  @Override
  protected void execute() {
    if (Limelight.avalible()) {
      double error, output, dt, derivative;

      error = Limelight.getX();
      dt = Timer.getFPGATimestamp() - lastTimestamp;
      integrator += (error * dt);
      derivative = (error - lastError) / dt;

      output = error * kP + integrator * kI + derivative * kD;
      LimelightTurret.moveBy(output);

      lastError = error;
    }
    lastTimestamp = Timer.getFPGATimestamp();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    LimelightTurret.stop();
  }
}