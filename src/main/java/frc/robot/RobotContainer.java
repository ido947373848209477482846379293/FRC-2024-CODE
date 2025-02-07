// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.subsystems.Drive.DriveSubsystem;
import frc.robot.subsystems.Auto;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.commands.PathPlannerAuto;
import com.pathplanner.lib.path.PathPlannerPath;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.DriveCommand;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // ? https://www.chiefdelphi.com/t/why-do-many-teams-put-a-m-in-front-of-many-variable-names/377126
  // ? this is why i put m_(variable name)
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem m_driveSubsystem;
  private final OI oi;
  private final Auto auto;
  private final SendableChooser<Command> autoChooser;

  // Replace with CommandPS4Controller or CommandJoystick if needed
  // private final CommandXboxController m_driverController =
  //     new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    this.m_driveSubsystem = new DriveSubsystem();
    this.oi = new OI();
    configureBindings();
    m_driveSubsystem.setAllModulesToZero();
    this.auto = new Auto(m_driveSubsystem);
     this.autoChooser = AutoBuilder.buildAutoChooser();

    //change to shuffleBoard later if you want
    // Another option that allows you to specify the default auto by its name
    // autoChooser = AutoBuilder.buildAutoChooser("My Default Auto");
     SmartDashboard.putData("Auto Chooser", autoChooser);
  }

  public DriveSubsystem getDriveSubsystem(){
    return m_driveSubsystem;
  }
  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() 
  {  
      // m_driveSubsystem.setDefaultCommand(new DriveCommand(m_driveSubsystem, oi.joystickLeft::getX, oi.joystickLeft::getY, oi.joystickRight::getX));
      m_driveSubsystem.setDefaultCommand(new DriveCommand(m_driveSubsystem, oi.xboxController::getLeftX, oi.xboxController::getLeftY, oi.xboxController::getRightX));
  }

  public Command getAutonomousCommand()
  {
    //this is for auto-based autonomous, we relay more on paths   
    return autoChooser.getSelected();
      // // Load the path you want to follow using its name in the GUI
      //   PathPlannerPath path = PathPlannerPath.fromPathFile("path 1");

      //   // Create a path following command using AutoBuilder. This will also trigger event markers.
      //   return AutoBuilder.followPath(path);
  }

  // i think the first getAutonomousCommand lets the driver choose the auto (correct me if i'm wrong) so that's why it stays, but the other one is also here if it's more convinient 
  //  public Command getAutonomousCommand() 
  //   {
  //       return new PathPlannerAuto("path 0 auto");
  //   }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

}