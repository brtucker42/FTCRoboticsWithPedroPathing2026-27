package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class FlywheelTest {

    DcMotor flywheelMotor;

    boolean aPressed = false;
    boolean flywheelOn = false;
    public FlywheelTest (HardwareMap hardwareMap) {

        flywheelMotor = hardwareMap.dcMotor.get ("flywheelMotor");

        flywheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        flywheelMotor.setPower(0);
    }

    public void flywheelManualControl (Gamepad gamepad1) {

        // Set variables
        if (gamepad1.a) {
            if (!aPressed) {
                flywheelOn = !flywheelOn;
            }
            aPressed = true;
        }
        else {
            aPressed = false;
        }

        // Set motor
        if (flywheelOn){
            flywheelMotor.setPower(1);
        }
        else {
            flywheelMotor.setPower(0);
        }
    }
}
