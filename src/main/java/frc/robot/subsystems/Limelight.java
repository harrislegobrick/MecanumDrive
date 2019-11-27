/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Limelight getters and setters
 */
public class Limelight extends Subsystem {
  public Limelight() {
    setTracking();
    setCamPos();
  }

  /**
   * @return Horizontal Offset From Crosshair To Target (-29.8 to 29.8 degrees)
   */
  public double getX() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
  }

  /**
   * @return Vertical Offset From Crosshair To Target (-24.85 to 24.85 degrees)
   */
  public double getY() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
  }

  public double getArea() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
  }

  public boolean avalible() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0) == 1;
  }

  /**
   * The 2nd cam stream is placed in the lower-right corner of the 1st cam stream
   * Access the camera at http://10.33.29.11:5800
   */
  public void setCamPos() {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("stream").setNumber(1);
  }

  public void setTracking() {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(0);
  }

  public void setDriving() {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(1);
  }

  @Override
  public void initDefaultCommand() {
  }
}