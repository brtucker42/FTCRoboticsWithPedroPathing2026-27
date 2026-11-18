package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class TransferTest {

    DcMotor transferMotor;
    Servo transferServo;
    boolean upPressed = false;
    boolean downPressed = false;
    boolean motorRunning = false;

    public double transferPosition = 0.0;
    private static final double transferIncrement = 10.0;


    public TransferTest(HardwareMap hardwareMap) {
        transferMotor = hardwareMap.get(DcMotor.class,"transferMotor");
        transferMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        transferMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        transferMotor.setPower(0);

    }


    public void bryceTransferControl(Gamepad gamepad1) {
        if (gamepad1.dpad_up) {
            motorRunning = true;
        }
    }

    public void transferManualControl(Gamepad gamepad1) {
        if (gamepad1.dpad_up) {
            if (!upPressed) {
                if (!motorRunning) {
                    motorRunning = true;
                    transferMotor.setPower(0.1);
                }
                else {
                    motorRunning = false;
                    transferMotor.setPower(0);
                }

            }
            upPressed = true;
        }
        else {
            upPressed = false;
        }
    }



}
