package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class TestOp1 extends LinearOpMode {
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
        DcMotor leftMotor = hardwareMap.dcMotor.get("leftMotor");
        DcMotor rightMotor = hardwareMap.dcMotor.get("rightMotor") ;
        leftMotor.setDirection(DcMotor.Direction.REVERSE) ;

        waitForStart() ;

        while ( opModeIsActive() ){
            double y = -gamepad1.left_stick_y ;
            double rx = gamepad1.right_stick_x ;
            double max = Math.max(Math.abs(y) + Math.abs(rx) , 1 ) ;
            double leftPower = ( y + rx ) / max ;
            double rightPower = ( y - rx ) / max ;

            leftMotor.setPower(leftPower) ;
            rightMotor.setPower(rightPower) ;
            
    }
}
