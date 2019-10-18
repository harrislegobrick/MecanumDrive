/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autoncommands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

public class DriveStraight extends TimedCommand {
  public enum DDirection{
    FORWARD, BACKWARD, LEFT, RIGHT
  }
  
  private DDirection direction;
  private double driveSpeed, driveDirection;
  private double kP = 0.05;
  private double kI = 0.01;
  private double kD = 0.04;
  private double turn;
  private PIDCommand thePID = new PIDCommand(kP, kI, kD) {
  
    @Override
    protected boolean isFinished() {
      return false;
    }
  
    @Override
    protected void usePIDOutput(double output) {
      turn = output;
    }
  
    @Override
    protected double returnPIDInput() {
      return Robot.drivetrain.getGyroo();
    }
  };

  public DriveStraight(double time, double driveSpeed, DDirection direction) {
    super(time);
    requires(Robot.drivetrain);
    this.driveSpeed = Math.abs(driveSpeed);
    this.direction = direction; 
  }

  @Override
  protected void initialize() {
    thePID.start();
    switch(direction){
      case FORWARD : 
      driveDirection = 0;
      break;
      case BACKWARD : 
      driveDirection = 180;
      break;
      case LEFT : 
      driveDirection = -90;
      break;
      case RIGHT : 
      driveDirection = 90;
      break;
    }
  }

  @Override
  protected void execute() {
    Robot.drivetrain.auton(driveSpeed, driveDirection, turn);
  }

  @Override
  protected void end() {
    thePID.cancel();
    thePID.close();
    Robot.drivetrain.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}