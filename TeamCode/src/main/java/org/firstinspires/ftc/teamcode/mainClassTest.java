package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class mainClassTest extends OpMode {

    DrivetrainTest drivetrainTest;
    ShooterTest shooterTest;
    IntakeTest intakeTest;
    TransferTest transferTest;
    CameraTest cameraTest;
    OdometryTest odometryTest;


    @Override
    public void init() {
        drivetrainTest = new DrivetrainTest(hardwareMap);
        shooterTest = new ShooterTest(hardwareMap);
        intakeTest = new IntakeTest(hardwareMap);
        transferTest = new TransferTest(hardwareMap);
        cameraTest = new CameraTest(hardwareMap);
        odometryTest = new OdometryTest(hardwareMap);
    }

    @Override
    public void loop() {
        drivetrainTest.drive(gamepad1);
        shooterTest.turretManualControl(gamepad1);
        shooterTest.hoodManuelControl(gamepad1);
        shooterTest.flywheelManualControl(gamepad1);
        intakeTest.intakeManualControl(gamepad1);
        transferTest.transferManualControl(gamepad1);
        cameraTest.getCameraInfo();
        odometryTest.getOdoPosHeading();
    }
}
