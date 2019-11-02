/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autoncommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Rotate2 extends Command {
  public enum Direction {
    CLOCKWISE, COUNTER_CLOCKWISE
  }

  private Direction direction;
  private double degrees;
  private double turnExactness = 0.9;
  private double speed = 0.25;

  /**
   * Used to rotate the bot a certain degrees.
   * <ul>
   * <li><b>degrees</b> : The degrees the robot should turn to.</li>
   * <li><b>direction</b> : Whether it should rotate clockwise or counter
   * clockwise.</li>
   * </ul>
   */
  public Rotate2(double degrees, Direction direction) {
    requires(Robot.drivetrain);
    this.degrees = Math.abs(degrees);
    this.direction = direction;
  }

  @Override
  protected void initialize() {
    if (direction == Direction.COUNTER_CLOCKWISE)
      speed = -speed;
    Robot.drivetrain.resetGyro();
  }

  @Override
  protected void execute() {
    Robot.drivetrain.auton(0.0, 0.0, speed);
  }

  @Override
  protected boolean isFinished() {
    return Math.abs(Robot.drivetrain.getGyroo()) >= degrees * turnExactness;
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