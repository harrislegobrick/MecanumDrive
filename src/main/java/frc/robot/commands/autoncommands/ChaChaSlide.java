/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autoncommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.autoncommands.DriveStraight.DDirection;
import frc.robot.commands.autoncommands.RotateBot.RDirection;

public class ChaChaSlide extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ChaChaSlide() {
    // isn't this the only reason to have a holonomic drivetrain?

    addSequential(new DriveStraight(1, 0.8, DDirection.RIGHT));
    Timer.delay(2);
    addSequential(new DriveStraight(1, 0.8, DDirection.LEFT));
    Timer.delay(2);
    addSequential(new DriveStraight(1, 0.8, DDirection.BACKWARD));
    Timer.delay(3);
    addSequential(new RotateBot(90, RDirection.LEFT));
    Timer.delay(1);
    addSequential(new RotateBot(180, RDirection.RIGHT));
    Timer.delay(1);
    addSequential(new RotateBot(90, RDirection.LEFT));
  }
}
