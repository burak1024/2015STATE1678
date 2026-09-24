package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Subsystems.arm.ArmSubsystem;
import frc.robot.Subsystems.elevator.ElevatorSubsystem;
import frc.robot.Subsystems.intake.IntakeSubsystem;

public class SuperStructure extends SubsystemBase {
    private static SuperStructure instance;

    public static SuperStructure getInstance() {
        if (instance == null) {
            instance = new SuperStructure();
        }
        return instance;
    }

    private final Runnable[] methods = {
            () -> idleIMethods(),
            () -> intakingIMethods(),
            () -> L1IMethods(),
            () -> L2IMethods(),
            () -> L3IMethods(),
            () -> L4IMethods(),
            () -> pulling(),
            () -> set0(),
            ()-> release(),
            ()->removing(),
    };

    private void idleIMethods() {
        changeSubsystemStates(IntakeSubsystem.state.idle, ElevatorSubsystem.state.idle, ArmSubsystem.state.idle);
    }

    private void intakingIMethods() {
        changeSubsystemStates(IntakeSubsystem.state.intaking, ElevatorSubsystem.state.idle, ArmSubsystem.state.idle);
    }

    private void L1IMethods() {
        changeSubsystemStates(IntakeSubsystem.state.idle, ElevatorSubsystem.state.L1, ArmSubsystem.state.idle);
    }

    private void L2IMethods() {
        changeSubsystemStates(IntakeSubsystem.state.idle, ElevatorSubsystem.state.L2, ArmSubsystem.state.idle);
    }

    private void L3IMethods() {
        changeSubsystemStates(IntakeSubsystem.state.idle, ElevatorSubsystem.state.L3, ArmSubsystem.state.idle);
    }

    private void L4IMethods() {
        changeSubsystemStates(IntakeSubsystem.state.idle, ElevatorSubsystem.state.L4, ArmSubsystem.state.idle);
    }

    private void pulling() {
        changeSubsystemStates(IntakeSubsystem.state.idle, ElevatorSubsystem.state.idle, ArmSubsystem.state.pulling);
    }

    private void set0() {
        changeSubsystemStates(IntakeSubsystem.state.grapping, ElevatorSubsystem.state.L0, ArmSubsystem.state.idle);
    }
    private void release(){
        changeSubsystemStates(IntakeSubsystem.state.idle, ElevatorSubsystem.state.L0, ArmSubsystem.state.idle);
    }
    private void removing(){
        changeSubsystemStates(IntakeSubsystem.state.grapping, ElevatorSubsystem.state.L1, ArmSubsystem.state.idle);
    }

    private void changeSubsystemStates(IntakeSubsystem.state intakeState, ElevatorSubsystem.state elevatorState,
            ArmSubsystem.state armState) {
        IntakeSubsystem.subsystem().changeState(intakeState);
        ElevatorSubsystem.subsystem().changeState(elevatorState);
        ArmSubsystem.subsystem().changeState(armState);
    }

    private state currentState = state.idle;

    public enum state {
        idle(0),
        intaking(1),
        L1(2),
        L2(3),
        L3(4),
        L4(5),
        pulling(6),
        L0(7),
        release(8),
        removing(9);


        public final int stateNum;

        state(int stateNum) {
            this.stateNum = stateNum;
        }
    }

    @Override
    public void periodic() {

        SmartDashboard.putString("SuperStructure/State", currentState.toString());

    }

    public SuperStructure() {
        IntakeSubsystem.subsystem();
        ArmSubsystem.subsystem();
        ElevatorSubsystem.subsystem();
    }

    public void changeState(state newState) {
        if (this.currentState == newState)
            return;
        currentState = newState;
        methods[currentState.stateNum].run();
        SmartDashboard.putNumber("ActiveState", currentState.stateNum);
    }
    public static Command pull(){
    return new InstantCommand(()-> SuperStructure.getInstance().changeState(SuperStructure.state.pulling));
   }

}