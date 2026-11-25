package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

public class FlywheelTest {

    DcMotorEx flywheelMotor; // put the flywheel motor name in here
    public boolean AWasPressed = false;

    public void FlywheelControl(Gamepad gamepad1){

        if(gamepad1.aWasPressed()){
            if (!AWasPressed){
                //turn on flywheel
                AWasPressed = true;
            }

        }
        if(gamepad1.aWasPressed()){
            if (AWasPressed){
                //turn off flywheel
                AWasPressed = false;
            }
        }



    }


}
