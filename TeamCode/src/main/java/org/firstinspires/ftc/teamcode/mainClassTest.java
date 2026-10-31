package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class mainClassTest extends OpMode {

    TurretTest turretTest;
    DrivetrainTest drivetrainTest;
    ShooterTest shooterTest;
    IntakeTest intakeTest;
    TransferTest transferTest;
    CameraTest cameraTest;
    OdometryTest odometryTest;


    @Override
    public void init() {
        //drivetrainTest = new DrivetrainTest(hardwareMap);
        shooterTest = new ShooterTest(hardwareMap);
        turretTest = new TurretTest(hardwareMap);
        /*intakeTest = new IntakeTest(hardwareMap);
        transferTest = new TransferTest(hardwareMap);
        cameraTest = new CameraTest(hardwareMap);
        odometryTest = new OdometryTest(hardwareMap);*/
    }

    @Override
    public void loop() {
        //drivetrainTest.manualControllerDrive(gamepad1);
        shooterTest.flywheelManualControl(gamepad1);
        turretTest.turretManualControl(gamepad1);
        /*shooterTest.hoodManualControl(gamepad1);
        shooterTest.flywheelManualControl(gamepad1);
        intakeTest.intakeManualControl(gamepad1);
        transferTest.transferManualControl(gamepad1);
        cameraTest.getCameraInfo();
        odometryTest.getOdoPosHeading();*/
        telemetry.addData("Flywheel velocity:", shooterTest.flywheelVelocity);
        telemetry.addData("Flywheel target velocity:", shooterTest.TargetVelocity);
        //telemetry.addData("Turret Position: ", turretTest.turretPosition);
        turretTest.updateTelemetry(telemetry);

        telemetry.update();
    }
}
