package driveTrain;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;

public class AutoDrive {
	public static int leftChannelA = 0;
	public static int leftChannelB = 1;
	public static int leftIndex = 2;
	public static int rightChannelA = 3;
	public static int rightChannelB = 4;
	public static int rightIndex = 5;
	public static int winchChannelA = 6;
	public static int winchChannelB = 7;
	public static int winchIndex = 8;

	public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();

	public double diff;
	public double gyroDeadZone = 2; //in degrees
	public double margin = 1; //in cm
	public double targetTurn;	
	public double targetDistance = 0; 
	
	public static boolean isGyroZeroed = true;
	public static boolean isCalledFirstTime = true;
	public static final double i2cm = 2.54;
	
	public static double encoderPulsesPerRev = 512; // should be in degrees
	public static int countsPerRevolution = 512;
	public static double wheelDiameter = 6*i2cm; // in cm
	public static boolean leftEncoderReversed = true;
	public static boolean rightEncoderReversed = false;
	public static boolean winchEncoderReversed = false;
	public static double encoderDistancePerPulse = (wheelDiameter * Math.PI) / (encoderPulsesPerRev);
	public static Encoder leftEncoder = new Encoder(leftChannelA, leftChannelB, leftEncoderReversed,EncodingType.k2X);
	public static Encoder rightEncoder = new Encoder(rightChannelA, rightChannelB, rightEncoderReversed,EncodingType.k2X);
	public static Encoder winchEncoder = new Encoder(winchChannelA, winchChannelB, winchEncoderReversed,EncodingType.k2X);
	
	public AutoDrive() {
	}

	public void sensorData() {
		leftEncoder.setDistancePerPulse(encoderDistancePerPulse);
		rightEncoder.setDistancePerPulse(encoderDistancePerPulse);
	}

	public static double getLeftWheelDistance() {

		return leftEncoder.getDistance();

	}

	public static double getRightWheelDistance() {

		return rightEncoder.getDistance();

	}
	
	public static double getWinchDistance() {
		return winchEncoder.getDistance();
	}

	public double getMainAvgDistance() {

		double ave;
		ave = (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
		return ave;

	}

	public void resetEncoder() {

		leftEncoder.reset();
		rightEncoder.reset();

	}

	// zero gyro
	public void zeroGyro() {
		gyro.reset();
		isGyroZeroed = true;
	}

	public boolean isDriveStraight() {
		diff = getLeftWheelDistance() - getRightWheelDistance();
		if (diff > margin )
			return true;
		return false;
	}

	public void correctDrive(double sp) {
		targetTurn = gyro.getAngle();
		if(Math.abs(gyro.getAngle()) >= gyroDeadZone) {
			Chassis.myRobot.arcadeDrive(sp, targetTurn/5);
		}
	}
	
	public void straightDrive(double sp) {
		Chassis.myRobot.arcadeDrive(sp, 0);
	}

	/*public void goStraight(double sp, double distance) {
		if(targetDistance != distance)
			targetDistance = distance;
		if (getMainAvgDistance() < distance) {
			// zero the gyro if not zeroed
			if ((isDriveStraight()) && (!isGyroZeroed)) {
				zeroGyro();
				straightDrive(sp);
				// drive straight
			} else if (isDriveStraight()) {
				straightDrive(sp);
			} else {
				correctDrive(sp);
			}
		}
	}*/
	
	public void goStraight(double sp) {
		if((isDriveStraight())) {
			straightDrive(sp);
		}else {
			correctDrive(sp);
		}
	}
	
	/**
	 * @param steering the relevant angular turning velocity between -1 to 1
	 */
	public void turn(double steering, double targetAngle) {
		if(isCalledFirstTime) {
			zeroGyro();
			isCalledFirstTime = false;
		}
		if(gyro.getAngle() < targetAngle)
			Chassis.myRobot.arcadeDrive(0, steering);
	}
}
