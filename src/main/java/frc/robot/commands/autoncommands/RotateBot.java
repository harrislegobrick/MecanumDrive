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
  private double degrees, desiredDegrees, previousError;
  private double turnExactness = 1.0;
  private double kP = 0.01;
  private double kD = 0.005;

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
    desiredDegrees = Robot.drivetrain.getGyroo() + (direction == Rotate.CLOCKWISE ? degrees : -degrees);
  }

  @Override
  protected void execute() {
    double error, derivative;
    double turnSpeed = 0;

    error = desiredDegrees - Robot.drivetrain.getGyroo();
    derivative = (previousError - error) / Robot.kDefaultPeriod;
    turnSpeed = error * kP + derivative * kD;
    Robot.drivetrain.auton(0.0, 0.0, (direction == Rotate.CLOCKWISE ? turnSpeed : -turnSpeed));
    previousError = error;
  }

  @Override
  protected boolean isFinished() {
    boolean fin = false;
    switch (direction) {
    case CLOCKWISE:
      fin = Robot.drivetrain.getGyroo() >= (desiredDegrees * turnExactness);
      break;
    case COUNTER_CLOCKWISE:
      fin = Robot.drivetrain.getGyroo() <= (desiredDegrees * turnExactness);
      break;
    }
    return fin;
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