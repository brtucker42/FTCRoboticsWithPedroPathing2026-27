package org.firstinspires.ftc.teamcode.kicking;

import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.ManualDrive;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

// This class is basic field centric drivetrain code using odometry, it does not keep the pose from auto, that feature is to be added.

@TeleOp
public class Drivetrain {

    private Follower follower;

    public Drivetrain(HardwareMap hardwareMap) {
        follower = Constants.create(hardwareMap);
    }

    public void manualDrive(Gamepad gamepad1) {
        DrivePowers powers = ManualDrive.fieldCentric(
                -gamepad1.left_stick_y * gamepad1.left_stick_y * gamepad1.left_stick_y,
                -gamepad1.left_stick_x * gamepad1.left_stick_x * gamepad1.left_stick_x,
                -gamepad1.right_stick_x * gamepad1.right_stick_x * gamepad1.right_stick_x,
                follower.pose().heading()
        );

        follower.manual(powers);
        follower.update();
    }

    public void updateTelemetry(Telemetry telemetry) {
        Pose robotPose = follower.pose(); // returns a Pose object

        telemetry.addData("Robot X", robotPose.x());
        telemetry.addData("Robot Y", robotPose.y());
        telemetry.addData("Robot Heading", Math.toDegrees(robotPose.heading()));
    }
}