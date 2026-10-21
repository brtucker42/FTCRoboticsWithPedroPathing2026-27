package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {
    public static Follower create(HardwareMap h) {
        // return new Follower(Drivetrain, Localizer, Foresight);
        return null;
    }
    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
        c.frontLeftName.set("frontLeftMotor");
        c.frontRightName.set("frontRightMotor");
        c.backLeftName.set("backLeftMotor");
        c.backRightName.set("backRightMotor");

        c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);

        c.manualBrakeMode.set(true);
    });
    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("pinpoint");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_SWINGARM_POD);
        c.xPodOffset.set(-2.8434561181256153);
        c.yPodOffset.set(1.4012737724724718);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });
    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.25722406440045187);
                Controller secondaryTranslationalForward = Controller.proportional(0.09503741864238151);
                Controller primaryTranslationalLateral = Controller.proportional(1.4164518668682624);
                Controller secondaryTranslationalLateral = Controller.proportional(0.5233411165168783);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.01843076862079486));
                c.brake.set(Controller.proportionalFeedforward(0.015666153327675632));

                c.headingFeedback.set(Controller.proportional(2.55859310428898));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.0442830509362946, 0.006174039840597538));

                c.linearBrakeCoefficients.set(Matrix.diag(0.04425713536042069, 0.061236949967002685));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.0017619174628433487, 0.0015821088834628723));

                c.maxAchievableForwardVelocity.set(60.012399737512716);
                c.maxAchievableStrafeVelocity.set(50.51697949265686);
                c.naturalForwardDeceleration.set(44.36341057238204);
                c.naturalStrafeDeceleration.set(60.38523450706686);
            }
    );
}