/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autoncommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.commands.autoncommands.DriveStraight.Drive;
import frc.robot.commands.autoncommands.RotateBot.Rotate;

public class ChaChaSlide extends CommandGroup {
  /**
   * Isn't this the only reason to have a holonomic drivetrain?
   */
  public ChaChaSlide() {
    addSequential(new DriveStraight(1, 0.4, Drive.RIGHT));
    addSequential(new WaitCommand(2));
    addSequential(new DriveStraight(1, 0.4, Drive.LEFT));
    addSequential(new WaitCommand(2));
    addSequential(new DriveStraight(0.5, 0.4, Drive.BACKWARD));
    addSequential(new WaitCommand(3));
    addSequential(new RotateBot(90, Rotate.COUNTER_CLOCKWISE));
    addSequential(new WaitCommand(1));
    addSequential(new RotateBot(180, Rotate.CLOCKWISE));
    addSequential(new WaitCommand(1));
    addSequential(new RotateBot(90, Rotate.COUNTER_CLOCKWISE));
  }
}
