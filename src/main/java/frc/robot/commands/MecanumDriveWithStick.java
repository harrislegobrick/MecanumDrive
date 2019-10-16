/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MecanumDriveWithStick extends Command {
  public enum Orientation {
    ROBOT, FIELD
  }

  private Orientation orientation;

  public MecanumDriveWithStick(Orientation orientation) {
    requires(Robot.drivetrain);
    this.orientation = orientation;
  }

  @Override
  protected void initialize() {
    if (orientation == Orientation.FIELD)
      Robot.drivetrain.resetGyro();
  }

  @Override
  protected void execute() {
    double throttle = (1.0 - Robot.oi.getJoyThrottle()) / -2.0;
    double x = -Robot.oi.getJoyX() * throttle;
    double y = Robot.oi.getJoyY() * throttle;
    double z = -Robot.oi.getJoyZ() * throttle * 0.5;
    switch (orientation) {
    case ROBOT:
      Robot.drivetrain.stickRobot(x, y, z);
      break;
    case FIELD:
      Robot.drivetrain.stickField(x, y, z);
      break;
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.drivetrain.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}