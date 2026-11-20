package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class MainTeleOpTest extends OpMode {

    DrivetrainTestOdo drivetrainTest;
    //Intake intake;
    //Transfer transfer;
    //Shooter shooter;
    TurretTest turretTest;
    // TurretAutoTestFelix turretAutoTest;
    TurretAutoTestSchurig turretAutoTest;
    //Camera camera;


    @Override
    public void init() {
        drivetrainTest = new DrivetrainTestOdo(hardwareMap);
        // intake = new Intake(hardwareMap);
        // transfer = new Transfer(hardwareMap);
        // shooter = new Shooter(hardwareMap);
        // turretTest = new TurretTest(hardwareMap);
        // turretAutoTest = new TurretAutoTestFelix(hardwareMap);
        turretAutoTest = new TurretAutoTestSchurig(hardwareMap);
        // camera = new Camera(hardwareMap);
    }

    @Override
    public void loop() {
        drivetrainTest.manualDrive(gamepad1);
        // intake.intake(gamepad1);
        // transfer.manualTransfer(gamepad1);
        // shooter.manualFlywheel(gamepad1);
        // shooter.manualHood(gamepad1);
        // turretTest.turretManualControl(gamepad1);
        turretAutoTest.turretAutoAim(drivetrainTest, telemetry);
        // camera.getCameraInfo();

        drivetrainTest.updateTelemetry(telemetry);
        // turretTest.updateTelemetry(telemetry);
        turretAutoTest.updateTelemetry(telemetry);
        // shooter.updateTelemetry(telemetry);
        telemetry.update();
    }
}
