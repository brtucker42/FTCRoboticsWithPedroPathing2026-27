package org.firstinspires.ftc.teamcode.testing;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.ManualDrive;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

// This class is basic bot centric drivetrain code using odometry, it does not keep the pose from auto, that feature is to be added.

public class DrivetrainTestOdo {

    public Follower follower;
    private final PoseFactory poseFactory = PoseFactory.degrees();
    private final Pose startPose = poseFactory.of(24, 24, 0);

    public DrivetrainTestOdo(HardwareMap hardwareMap) {
        follower = Constants.create(hardwareMap);
        follower.setPose(startPose);
    }

    public void manualDrive(Gamepad gamepad1) {
        follower.manual(
                -gamepad1.left_stick_y,
                gamepad1.left_stick_x,
                gamepad1.right_stick_x
        );

        follower.update();
    }

    public void updateTelemetry(Telemetry telemetry) {
        Pose robotPose = follower.pose(); // returns a Pose object

        telemetry.addData("Robot X", robotPose.x());
        telemetry.addData("Robot Y", robotPose.y());
        telemetry.addData("Robot Heading", Math.toDegrees(robotPose.heading()));
    }
}