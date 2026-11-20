package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class TransferTest {

    DcMotor transferMotor;
    Servo transferServo;
    boolean upPressed = false;
    boolean motorRunning = false;
    ElapsedTime transferTimer = new ElapsedTime();



    public TransferTest(HardwareMap hardwareMap) {
        transferMotor = hardwareMap.get(DcMotor.class,"transferMotor");
        transferServo = hardwareMap.get(Servo.class, "transferServo");
        transferMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        transferMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        transferMotor.setPower(0);
        transferServo.setPosition(0);

    }


    public void transferManualControl(Gamepad gamepad1) {
        if (gamepad1.dpad_up) {
            if (!upPressed) {
                if (!motorRunning) {
                    transferServo.setPosition(0.5);
                    transferTimer.reset();
                    motorRunning = true;
                }
                else {
                    motorRunning = false;
                    transferMotor.setPower(0);
                    transferServo.setPosition(0);
                }

            }
            upPressed = true;
        }
        else {
            upPressed = false;
        }
        runTransferMotor();
    }

    public void runTransferMotor() {
        if (motorRunning && transferTimer.milliseconds() > 1000) {
            transferMotor.setPower(0.5);
        }
    }



}
