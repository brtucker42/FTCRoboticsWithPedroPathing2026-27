package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


@com.qualcomm.robotcore.eventloop.opmode.Autonomous

public class Autonomous extends OpMode {
    DcMotor frontLeftMotor;
    DcMotor frontRightMotor;
    DcMotor backLeftMotor;
    DcMotor backRightMotor;
    @Override
    public void init() {


            frontLeftMotor  = hardwareMap.dcMotor.get("frontLeftMotor");
            backLeftMotor   = hardwareMap.dcMotor.get("backLeftMotor");
            frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
            backRightMotor  = hardwareMap.dcMotor.get("backRightMotor");

            frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

            //Make Sure Motors are off
            frontLeftMotor.setPower(0);
            backLeftMotor.setPower(0);
            frontRightMotor.setPower(0);
            backRightMotor.setPower(0);
            }

    @Override
    public void loop() {
        //Move a bit Forward
        frontLeftMotor.setPower(0.5);
        backLeftMotor.setPower(0.5);
        frontRightMotor.setPower(0.5);
        backRightMotor.setPower(0.5);

        sleep(5);

    }

}
