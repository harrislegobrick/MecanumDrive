/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TurretMove extends Command {
  public enum Direction {
    CCW(1), CW(-CCW.toValue);

    public final double toValue;

    private Direction(double toValue) {
      this.toValue = toValue;
    }
  }

  private Direction direction;

  public TurretMove(Direction direction) {
    requires(Robot.limelightTurret);
    this.direction = direction;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.limelightTurret.moveBy(direction.toValue);
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