package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class mainClassTest extends OpMode {

    Turret turret;
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
        turret = new Turret(hardwareMap);
        /*intakeTest = new IntakeTest(hardwareMap);
        transferTest = new TransferTest(hardwareMap);
        cameraTest = new CameraTest(hardwareMap);
        odometryTest = new OdometryTest(hardwareMap);*/
    }

    @Override
    public void loop() {
        //drivetrainTest.manualControllerDrive(gamepad1);
        shooterTest.flywheelManualControl(gamepad1);
        //turret.turretManualControl(gamepad1);
        /*shooterTest.hoodManualControl(gamepad1);
        shooterTest.flywheelManualControl(gamepad1);
        intakeTest.intakeManualControl(gamepad1);
        transferTest.transferManualControl(gamepad1);
        cameraTest.getCameraInfo();
        odometryTest.getOdoPosHeading();*/
        if (gamepad1.triangleWasPressed()) {
            turret.moveToPosition(0);
        }
        if (gamepad1.circleWasPressed()) {
            turret.moveToPosition(270);
        }
        if (gamepad1.crossWasPressed()) {
            turret.moveToPosition(180);
        }
        if (gamepad1.squareWasPressed()) {
            turret.moveToPosition(90);
        }
        turret.tuneP(gamepad1);
        telemetry.addData("Flywheel velocity:", shooterTest.flywheelVelocity);
        telemetry.addData("Flywheel target velocity:", shooterTest.TargetVelocity);
        //telemetry.addData("Turret Position: ", turretTest.turretPosition);
        turret.updateTelemetry(telemetry);

        telemetry.update();
    }
}
