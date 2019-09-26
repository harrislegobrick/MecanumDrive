/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autoncommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.commands.autoncommands.DriveStraight.DDirection;
import frc.robot.commands.autoncommands.RotateBot.RDirection;

public class ChaChaSlide extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ChaChaSlide() {
    // isn't this the only reason to have a holonomic drivetrain?

    addSequential(new DriveStraight(1, 0.8, DDirection.RIGHT));
    addSequential(new WaitCommand(2));
    addSequential(new DriveStraight(1, 0.8, DDirection.LEFT));
    addSequential(new WaitCommand(2));
    addSequential(new DriveStraight(1, 0.8, DDirection.BACKWARD));
    addSequential(new WaitCommand(3));
    addSequential(new RotateBot(90, RDirection.LEFT));
    addSequential(new WaitCommand(1));
    addSequential(new RotateBot(180, RDirection.RIGHT));
    addSequential(new WaitCommand(1));
    addSequential(new RotateBot(90, RDirection.LEFT));
  }
}
