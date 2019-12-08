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
  private static Limelight instance;

  public static Limelight getInstance() {
    if (instance == null)
      instance = new Limelight();
    return instance;
  }

  private Limelight() {
    setTracking();
    setPiPMain();
  }

  /**
   * @return Horizontal Offset From Crosshair To Target (-29.8 to 29.8 degrees)
   */
  public static double getX() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
  }

  /**
   * @return Vertical Offset From Crosshair To Target (-24.85 to 24.85 degrees)
   */
  public static double getY() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
  }

  public static double getArea() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
  }

  public static double getLatency() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tl").getDouble(-99999);
  }

  public static boolean avalible() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0) == 1;
  }

  /**
   * The 2nd cam stream is placed in the lower-right corner of the 1st cam stream
   * Access the camera at http://10.33.29.11:5800
   */
  public static void setPiPMain() {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("stream").setNumber(1);
  }

  public static void setPiPSecondary() {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("stream").setNumber(2);
  }

  public static void setTracking() {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(0);
  }

  public static void setDriving() {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(1);
  }

  @Override
  public void initDefaultCommand() {
  }
}