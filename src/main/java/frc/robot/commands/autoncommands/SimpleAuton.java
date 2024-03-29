/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autoncommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.autoncommands.DriveStraight.Drive;
import frc.robot.commands.autoncommands.RotateBot.Rotate;;

public class SimpleAuton extends CommandGroup {
  /**
   * testing auton driveing and rotating
   */
  public SimpleAuton() {
    // Add Commands here:
    addSequential(new DriveStraight(2, 0.2, Drive.FORWARD));
    addSequential(new RotateBot(90, Rotate.COUNTER_CLOCKWISE));
    addSequential(new DriveStraight(2, 0.3, Drive.RIGHT));
    addSequential(new RotateBot(90, Rotate.CLOCKWISE));

    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}