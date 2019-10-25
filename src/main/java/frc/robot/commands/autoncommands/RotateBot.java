/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autoncommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RotateBot extends Command {
  public enum Rotate {
    CLOCKWISE, COUNTER_CLOCKWISE
  }

  private Rotate direction;
  private double degrees, initalHeading;
  private double turnSpeed = 0.3;
  private double turnExactness = 0.96;

  /**
   * Used to rotate the bot a certain degrees.
   * <ul>
   * <li><b>degrees</b> : The degrees the robot should turn to.</li>
   * <li><b>direction</b> : Whether it should rotate clockwise or counter
   * clockwise.</li>
   * </ul>
   */
  public RotateBot(double degrees, Rotate direction) {
    requires(Robot.drivetrain);
    this.degrees = degrees;
    this.direction = direction;
  }

  @Override
  protected void initialize() {
    initalHeading = Robot.drivetrain.getGyroo();
  }

  @Override
  protected void execute() {
    Robot.drivetrain.auton(0.0, 0.0, (direction == Rotate.CLOCKWISE ? turnSpeed : -turnSpeed));
  }

  @Override
  protected boolean isFinished() {
    return (initalHeading
        + Robot.drivetrain.getGyroo()) >= ((initalHeading + (direction == Rotate.CLOCKWISE ? degrees : -degrees))
            * turnExactness);
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