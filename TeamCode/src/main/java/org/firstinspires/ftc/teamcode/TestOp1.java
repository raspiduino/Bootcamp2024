package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class TestOp1 extends LinearOpMode {
    // Sensitivity value
    private final double SENSE = 0.05;
    private final double TRIGG = 0.3;

    /**
     * Quick function for sensitivity control
     * @param input joystick input value
     * @return input, if it's >= SENSE. Else 0.
     */
    private double sense(double input) {
        return (Math.abs(input) >= SENSE) ? input : 0;
    }

    /**
     * Quick function for detecting analog button state
     * @param input analog button value
     * @return bool, true if input >= TRIGG
     */
    private boolean trigger(float input) {
        return (input >= TRIGG);
    }

    /**
     * Override this method and place your code here.
     * <p>
     * Please do not catch {@link InterruptedException}s that are thrown in your OpMode
     * unless you are doing it to perform some brief cleanup, in which case you must exit
     * immediately afterward. Once the OpMode has been told to stop, your ability to
     * control hardware will be limited.
     *
     * @throws InterruptedException When the OpMode is stopped while calling a method
     *                              that can throw {@link InterruptedException}
     */
    @Override
    public void runOpMode() throws InterruptedException {
        // Get motor objects from hardwareMap
        DcMotor leftMotor = hardwareMap.dcMotor.get("leftMotor");
        DcMotor rightMotor = hardwareMap.dcMotor.get("rightMotor");
        DcMotor hangMotor = hardwareMap.dcMotor.get("hangMotor");

        // Set motor mode
        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        hangMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Reverse the left motor (you can choose to reverse the right one if needed)
        // Or just swap 2 motors
        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        // Enable brake
        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hangMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Wait for start
        waitForStart();

        // Main control loop
        while (opModeIsActive()) {
            // Get joystick values
            double ly = sense(-gamepad1.left_stick_y);
            double ry = sense(-gamepad1.right_stick_y);

            // Set joystick values to motor
            leftMotor.setPower(ly);
            rightMotor.setPower(ry);

            // Hang button up
            if (gamepad1.left_bumper) {
                hangMotor.setPower(1);
                telemetry.addData("Hang", "up");
            }

            // Hang motor down
            else if (gamepad1.right_bumper) {
                hangMotor.setPower(-1);
                telemetry.addData("Hang", "down");
            }

            // No button is pressed -> stop the motor
            else {
                hangMotor.setPower(0);
                telemetry.addData("Hang", "stop");
            }

            // Telemetry
            telemetry.addData("Left", ly);
            telemetry.addData("Right", ry);
            telemetry.update();
        }
    }
}
