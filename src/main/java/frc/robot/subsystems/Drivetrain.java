/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.RobotMap;
import frc.robot.commands.MecanumDriveWithStick;
import frc.robot.commands.MecanumDriveWithStick.Orientation;

/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {
  private static Drivetrain instance;
  private static WPI_VictorSPX frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor;
  private static MecanumDrive rodot;
  private static ADXRS450_Gyro gyro;

  private Drivetrain() {
    init();
  }

  public void init() {
    frontLeftMotor = new WPI_VictorSPX(RobotMap.FRONT_LEFT_MOTOR);
    frontRightMotor = new WPI_VictorSPX(RobotMap.FRONT_RIGHT_MOTOR);
    backLeftMotor = new WPI_VictorSPX(RobotMap.BACK_LEFT_MOTOR);
    backRightMotor = new WPI_VictorSPX(RobotMap.BACK_RIGHT_MOTOR);

    rodot = new MecanumDrive(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor);

    gyro = new ADXRS450_Gyro();

    rodot.setDeadband(RobotMap.JOY_DEADZONE);
    gyro.calibrate();
  }

  public static Drivetrain getInstance() {
    if (instance == null)
      instance = new Drivetrain();
    return instance;
  }

  public void stickRobot(double ySpeed, double xSpeed, double zRotation) {
    rodot.driveCartesian(ySpeed, xSpeed, zRotation);
  }

  public void stickField(double ySpeed, double xSpeed, double zRotation) {
    rodot.driveCartesian(ySpeed, xSpeed, zRotation, -gyro.getAngle());
  }

  public void auton(double magnitude, double angle, double zRotation) {
    rodot.drivePolar(magnitude, angle, zRotation);
  }

  public void stop() {
    rodot.stopMotor();
  }

  public void setBrake() {
    frontLeftMotor.setNeutralMode(NeutralMode.Brake);
    frontRightMotor.setNeutralMode(NeutralMode.Brake);
    backLeftMotor.setNeutralMode(NeutralMode.Brake);
    backRightMotor.setNeutralMode(NeutralMode.Brake);
  }

  public void setCoast() {
    frontLeftMotor.setNeutralMode(NeutralMode.Coast);
    frontRightMotor.setNeutralMode(NeutralMode.Coast);
    backLeftMotor.setNeutralMode(NeutralMode.Coast);
    backRightMotor.setNeutralMode(NeutralMode.Coast);
  }

  public double getGyroo() {
    return gyro.getAngle();
  }

  public void resetGyro() {
    gyro.reset();
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new MecanumDriveWithStick(Orientation.ROBOT));
  }
}