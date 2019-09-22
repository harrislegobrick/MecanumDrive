/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.RobotMap;
import frc.robot.commands.MeecanumDrive;

/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {
  private Talon frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor;
  public MecanumDrive rodot;
  private ADXRS450_Gyro gyro;

  public Drivetrain() {
    frontLeftMotor = new Talon(RobotMap.FRONTLEFTMOTOR);
    frontRightMotor = new Talon(RobotMap.FRONTRIGHTMOTOR);
    backLeftMotor = new Talon(RobotMap.BACKLEFTMOTOR);
    backRightMotor = new Talon(RobotMap.BACKRIGHTMOTOR);
    
    rodot = new MecanumDrive(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor);

    gyro = new ADXRS450_Gyro();
    gyro.calibrate();
  }

  public double getGyroo() {
    return gyro.getAngle();
  }

  public void resetGyro() {
    gyro.reset();
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new MeecanumDrive(false));
  }
}