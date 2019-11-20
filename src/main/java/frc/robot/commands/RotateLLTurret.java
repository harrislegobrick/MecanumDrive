/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RotateLLTurret extends Command {
  public enum Direction {
    CW(0.3), CCW(-0.3);

    public final double value;

    private Direction(double value) {
      this.value = value;
    }
  }

  private Direction rotate;

  public RotateLLTurret(Direction rotate) {
    requires(Robot.limelightTurret);
    this.rotate = rotate;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.limelightTurret.setServo(rotate.value);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.limelightTurret.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}