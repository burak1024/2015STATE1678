package frc.robot.auto;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Subsystems.SuperStructure;

public class auto {
   public static Command pull(){
    return new InstantCommand(()-> SuperStructure.getInstance().changeState(SuperStructure.state.pulling));
   }
}
