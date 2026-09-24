
package frc.robot;

import static edu.wpi.first.units.Units.*;

import com.ctre.phoenix6.swerve.SwerveRequest;
import com.pathplanner.lib.auto.NamedCommands;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import frc.robot.Subsystems.CommandSwerveDrivetrain;
import frc.robot.Subsystems.SuperStructure;
import frc.robot.generated.TunerConstants;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private double MaxSpeed = 1.0 * TunerConstants.kSpeedAt12Volts.in(MetersPerSecond);
  private double MaxAngularRate = RotationsPerSecond.of(0.75).in(RadiansPerSecond);
  public final CommandPS4Controller driver = new CommandPS4Controller(0);
  public final CommandPS4Controller driver2 = new CommandPS4Controller(1);
  public final CommandSwerveDrivetrain drivetrain = CommandSwerveDrivetrain.getSwerve();
  private final Field2d field = new Field2d();

  public Robot() {
    NamedCommands.registerCommand("pulling", new InstantCommand(()->SuperStructure.getInstance().changeState(SuperStructure.state.pulling)));
    drivetrain.setDefaultCommand(
        drivetrain.applyRequest(() -> new SwerveRequest.FieldCentric()
            .withVelocityX(-driver.getLeftY() * MaxSpeed)
            .withVelocityY(-driver.getLeftX() * MaxSpeed)
            .withRotationalRate(MathUtil.applyDeadband(-driver.getRightX(), 0.5) * MaxAngularRate)));

    driver2.R2().whileTrue(new InstantCommand(() -> SuperStructure.getInstance().changeState(SuperStructure.state.intaking))).onFalse(new InstantCommand(()->SuperStructure.getInstance().changeState(SuperStructure.state.idle)));
       
    driver2.triangle().onTrue(new InstantCommand(() -> SuperStructure.getInstance().changeState(SuperStructure.state.L1)));

    driver2.square().onTrue(new InstantCommand(() -> SuperStructure.getInstance().changeState(SuperStructure.state.L2)));

    driver2.cross().onTrue(new InstantCommand(() -> SuperStructure.getInstance().changeState(SuperStructure.state.L3)));

    driver2.circle().onTrue(new InstantCommand(() -> SuperStructure.getInstance().changeState(SuperStructure.state.L4)));

    driver2.povUp()
        .whileTrue(new InstantCommand(() -> SuperStructure.getInstance().changeState(SuperStructure.state.pulling))).onFalse(new InstantCommand(()->SuperStructure.getInstance().changeState(SuperStructure.state.idle)));

    driver2.povDown()
        .onTrue(new InstantCommand(() -> SuperStructure.getInstance().changeState(SuperStructure.state.idle))).onFalse(new InstantCommand(()->SuperStructure.getInstance().changeState(SuperStructure.state.idle)));
    driver2.povRight().whileTrue(new InstantCommand(() -> SuperStructure.getInstance().changeState(SuperStructure.state.L0))).onFalse(new InstantCommand(()->SuperStructure.getInstance().changeState(SuperStructure.state.idle)));
    driver2.L1().onTrue(new InstantCommand(()-> SuperStructure.getInstance().changeState(SuperStructure.state.removing))).onFalse(new InstantCommand(()->SuperStructure.getInstance().changeState(SuperStructure.state.idle)));
    driver2.L2().onTrue(new InstantCommand(()->SuperStructure.getInstance().changeState(SuperStructure.state.release))).onFalse(new InstantCommand(()->SuperStructure.getInstance().changeState(SuperStructure.state.idle)));
    SmartDashboard.putData("Field", field);
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    SuperStructure.getInstance().periodic();
    field.setRobotPose(drivetrain.getState().Pose);
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

  @Override
  public void simulationInit() {
  }

  @Override
  public void simulationPeriodic() {
  }
}
