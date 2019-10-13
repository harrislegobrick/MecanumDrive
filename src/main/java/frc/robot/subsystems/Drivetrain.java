/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.RobotMap;
import frc.robot.commands.MecanumDriveWithStick;

/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {
  private PWMVictorSPX frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor;
  private MecanumDrive rodot;
  private ADXRS450_Gyro gyro;

  public Drivetrain() {
    frontLeftMotor = new PWMVictorSPX(RobotMap.FRONT_LEFT_MOTOR);
    frontRightMotor = new PWMVictorSPX(RobotMap.FRONT_RIGHT_MOTOR);
    backLeftMotor = new PWMVictorSPX(RobotMap.BACK_LEFT_MOTOR);
    backRightMotor = new PWMVictorSPX(RobotMap.BACK_RIGHT_MOTOR);

    rodot = new MecanumDrive(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor);

    gyro = new ADXRS450_Gyro();
    gyro.calibrate();
  }

  public void stick(double ySpeed, double xSpeed, double zRotation, boolean oriented) {
    if (oriented) {
      rodot.driveCartesian(ySpeed, xSpeed, zRotation, gyro.getAngle());
    } else {
      rodot.driveCartesian(ySpeed, xSpeed, zRotation);
    }
  }

  public void auton(double magnitude, double angle, double zRotation) {
    rodot.drivePolar(magnitude, angle, zRotation);
  }

  public void stop() {
    rodot.stopMotor();
  }

  public double getGyroo() {
    return gyro.getAngle();
  }

  public void resetGyro() {
    gyro.reset();
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new MecanumDriveWithStick());
  }
}