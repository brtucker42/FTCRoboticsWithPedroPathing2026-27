package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ShooterTest {
    DcMotorEx flywheelMotor;
    boolean aPressed = false;
    boolean bPressed = false;
    boolean xPressed = false;
    boolean flywheelOn = false;
    public double TargetVelocity = 1400;
    public double flywheelvelocity;

    public ShooterTest(HardwareMap hardwareMap) {
        flywheelMotor = hardwareMap.get (DcMotorEx.class,"flywheelMotor");
        flywheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheelMotor.setVelocityPIDFCoefficients(10,0,0,12);
        flywheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        flywheelMotor.setPower(0);

    }



    public void flywheelManualControl(Gamepad gamepad1) {

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
        if (gamepad1.b) {

            if (!bPressed) {
                TargetVelocity = TargetVelocity +100;
            }
            bPressed = true;
        }
        else {
            bPressed = false;
        }

        if (gamepad1.x) {

            if (!xPressed) {
                TargetVelocity = Math.max(0, TargetVelocity -100);
            }
            xPressed = true;
        }
        else {
            xPressed = false;
        }




        // Set motor
        if (flywheelOn){
            flywheelMotor.setVelocity(TargetVelocity);
            // flywheelMotor.setPower(0.5);
        }
        else {
            flywheelMotor.setPower(0);
        }
       flywheelvelocity = flywheelMotor.getVelocity();

    }
    public void flywheelAutoControl() {

    }
    public void hoodManualControl(Gamepad gamepad1) {

    }
    public void hoodAutoControl() {

    }
    public void turretManualControl(Gamepad gamepad1) {

    }
    public void turretAutoControl() {

    }
}
