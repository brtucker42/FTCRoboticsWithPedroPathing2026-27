package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DrivetrainTest {

    DcMotor frontLeftMotor;
    DcMotor frontRightMotor;
    DcMotor backLeftMotor;
    DcMotor backRightMotor;

    public DrivetrainTest(HardwareMap hardwareMap) {
        frontLeftMotor  = hardwareMap.dcMotor.get("frontLeftMotor");
        backLeftMotor   = hardwareMap.dcMotor.get("backLeftMotor");
        frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        backRightMotor  = hardwareMap.dcMotor.get("backRightMotor");

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeftMotor.setPower(0);
        backLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backRightMotor.setPower(0);
    }

    public void drive(Gamepad gamepad1) {

        double jy = gamepad1.left_stick_y * gamepad1.left_stick_y * gamepad1.left_stick_y; // Remember, Y stick value is reversed
        double jx = -gamepad1.left_stick_x * -gamepad1.left_stick_x * -gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
        double rx = gamepad1.right_stick_x * gamepad1.right_stick_x * gamepad1.right_stick_x * 0.75; // Decrease rotation sensitivity

        double denominator = Math.max(Math.abs(jy) + Math.abs(jx) + Math.abs(rx), 1);
        double frontLeftPower  = (jy + jx + rx) / denominator;
        double backLeftPower   = (jy - jx + rx) / denominator;
        double frontRightPower = (jy - jx - rx) / denominator;
        double backRightPower  = (jy + jx - rx) / denominator;

        frontLeftMotor.setPower(frontLeftPower);
        backLeftMotor.setPower(backLeftPower);
        frontRightMotor.setPower(frontRightPower);
        backRightMotor.setPower(backRightPower);
    }
}
