/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

public class MecanumDriveWithStick extends Command {
  public enum Orientation {
    ROBOT, FIELD
  }

  private Orientation orientation;

  /**
   * Used to drive the robot in a mecanum style with one stick.
   * 
   * @param orientation : Either robot or field orientated. If field orientated
   *                    the heading does not matter; forward will be whichever way
   *                    the front was facing when changed to field.
   */
  public MecanumDriveWithStick(Orientation orientation) {
    requires(Drivetrain.getInstance());
    this.orientation = orientation;
  }

  @Override
  protected void initialize() {
    if (orientation == Orientation.FIELD)
      Drivetrain.resetGyro();
  }

  @Override
  protected void execute() {
    double throttle = (1.0 - Robot.oi.getJoyThrottle()) / 2.0;
    double x = Robot.oi.getJoyX() * throttle;
    double y = -Robot.oi.getJoyY() * throttle;
    double z = Robot.oi.getJoyZ() * throttle * 0.7;
    switch (orientation) {
    case ROBOT:
      Drivetrain.stickRobot(x, y, z);
      break;
    case FIELD:
      Drivetrain.stickField(x, y, z);
      break;
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Drivetrain.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}