/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MeecanumDrive extends Command {
  private boolean fieldOriented;

  public MeecanumDrive(boolean fieldOriented) {
    requires(Robot.drivetrain);
    this.fieldOriented = fieldOriented;
  }

  @Override
  protected void initialize() {
    if(fieldOriented) Robot.drivetrain.resetGyro();
  }

  @Override
  protected void execute() {
    double throttle = (1.0 - Robot.oi.getJoyThrottle()) / -2.0;
    
    if(fieldOriented){
      Robot.drivetrain.driveStickField(Robot.oi.getJoyY() * throttle, Robot.oi.getJoyX() * throttle, Robot.oi.getJoyZ() * throttle, Robot.drivetrain.getGyroo());
    }else{
      Robot.drivetrain.driveStick(Robot.oi.getJoyY() * throttle, Robot.oi.getJoyX() * throttle, Robot.oi.getJoyZ() * throttle);
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}