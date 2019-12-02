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
  private double degrees, desiredDegrees, previousError, initalHeading;
  private final double maxSpeed = 0.6;
  private final double turnExactness = 1.0;
  private final double kP = 0.01; // will need tuning (really small number now bc of kF)
  private final double kD = 0.002; // will need tuning (really small number now bc of kF)
  private final double kF = 0.002;

  /**
   * Used to rotate the bot a certain degrees.
   * 
   * @param degrees   : The degrees the robot should turn to.
   * @param direction : Whether it should rotate clockwise or counter clockwise.
   */
  public RotateBot(double degrees, Rotate direction) {
    requires(Robot.drivetrain);
    this.degrees = Math.abs(degrees);
    this.direction = direction;
  }

  @Override
  protected void initialize() {
    desiredDegrees = Robot.drivetrain.getGyroo() + (direction == Rotate.CLOCKWISE ? degrees : -degrees);
    initalHeading = Robot.drivetrain.getGyroo();
  }

  @Override
  protected void execute() {
    double error, derivative, turnSpeed;

    error = desiredDegrees - Robot.drivetrain.getGyroo();
    derivative = (error - previousError) / Robot.kDefaultPeriod;
    turnSpeed = error * kP + derivative * kD + (direction == Rotate.CLOCKWISE ? degrees : -degrees) * kF;
    Robot.drivetrain.auton(0.0, 0.0,
        Math.abs(turnSpeed) >= maxSpeed ? maxSpeed * (turnSpeed / Math.abs(turnSpeed)) : turnSpeed);
    previousError = error;
  }

  @Override
  protected boolean isFinished() {
    return Math.abs(initalHeading - Robot.drivetrain.getGyroo()) >= (degrees * turnExactness);
  }

  @Override
  protected void end() {
    Robot.drivetrain.stop();
  }
}