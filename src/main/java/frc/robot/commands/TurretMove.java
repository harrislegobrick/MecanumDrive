/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.LimelightTurret;

public class TurretMove extends Command {
  public enum Direction {
    CCW(1), CW(-1);

    public final double value;

    private Direction(double value) {
      this.value = value;
    }
  }

  private Direction direction;

  public TurretMove(Direction direction) {
    requires(LimelightTurret.getInstance());
    this.direction = direction;
  }

  @Override
  protected void execute() {
    LimelightTurret.moveBy(direction.value);
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