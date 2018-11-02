/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team9109.robot;

import driveTrain.*;
import intake.*;
import elevator.*;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
//import climber.*;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	/*
	 * private static final String kDefaultAuto = "Default"; private static final
	 * String autoRoutine1 = "LeftRoute"; private static final String autoRoutine2 =
	 * "MiddleRoute"; private static final String autoRoutine3 = "RightRoute";
	 */

	public boolean hasCalledAuto = false;
	public boolean isFirstTime = true;

	// public String m_autoSelected;

	// public static Timer myTimer0 = new Timer();

	public Servo myServo = new Servo(9);
	public Chassis myChassis = new Chassis();
	public Intake myIntake = new Intake();
	public Winch myWinch = new Winch();
	// private SendableChooser<String> m_chooser = new SendableChooser<>();

	/*
	 * public PWMVictorSPX m_right = new PWMVictorSPX(6); public PWMVictorSPX m_left
	 * = new PWMVictorSPX(7); public double sp=0.7;
	 */

	// public Compressor myCompressor = new Compressor();

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */

	@Override
	public void robotInit() {
		/*
		 * m_chooser.addDefault("Default", kDefaultAuto); m_chooser.addObject("Left",
		 * autoRoutine1); m_chooser.addObject("Middle", autoRoutine2);
		 * m_chooser.addObject("Right", autoRoutine3);
		 * 
		 * AutoDrive.gyro.calibrate(); SmartDashboard.putData("Auto choices",
		 * m_chooser); //LiveWindow lw = new LiveWindow();
		 * //LiveWindow.addSensor("SomeSubsystem", "gyro", AutoDrive.gyro);
		 * //LiveWindow.addSensor("SomeSubsystem", "encoder", AutoDrive.leftEncoder);
		 * //myCompressor.setClosedLoopControl(true);
		 */
		myServo.set(0.5);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString line to get the
	 * auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the SendableChooser
	 * make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		// m_autoSelected = m_chooser.getSelected();
		Chassis.gameData = DriverStation.getInstance().getGameSpecificMessage();
		/*
		 * AutoDrive.gyro.calibrate(); switch (m_autoSelected) { case autoRoutine1: //
		 * Put custom auto code here myChassis.setAutoRoutine(1); break; case
		 * autoRoutine2: myChassis.setAutoRoutine(2); break; case autoRoutine3:
		 * myChassis.setAutoRoutine(3); break; case kDefaultAuto:
		 * myChassis.setAutoRoutine(0); break; }
		 */
		Chassis.myTimer1.start();
		Intake.grab();

	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		// myChassis.runAuto();
		//myServo.set(0);
		/*if (Chassis.gameData.substring(0, 1).equals("R")) {
			if(Chassis.myTimer1.get() < 1)
				myServo.set(0);
			else if (Chassis.myTimer1.get() < 3) {

			}
			else if (Chassis.myTimer1.get() < 4.3) {
				myWinch.rotate(0.5);
			}
			else if(Chassis.myTimer1.get()<5) {
				myWinch.rotate(0.2);
			}
			else if (Chassis.myTimer1.get() < 8)
				Chassis.myRobot.arcadeDrive(-0.7, 0.1);
			else {
				Intake.unGrab();
			}
		}*/
		//else {
			if(Chassis.myTimer1.get()<2.5)
				Chassis.myRobot.arcadeDrive(-0.7, 0.1);
		//}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		myChassis.regularDrive();
		myWinch.testControlledRoller();
		if (Winch.myViceXbox.getAButton()) {
			Intake.grab();
		}
		if (Winch.myViceXbox.getBButton()) {
			Intake.unGrab();
		}
		if(Winch.myViceXbox.getStartButton()) {
			myServo.set(0);
		}
		/*if (Winch.myViceXbox.getXButton()) {
			Intake.suck();
		} else if (Winch.myViceXbox.getYButton()) {
			Intake.vomit();
		}else {
			Intake.stop();
		}*/
		/*
		 * if(Winch.myViceXbox.getXButton()) Intake.suck(); else
		 * if(Winch.myViceXbox.getYButton()) Intake.vomit(); else Intake.stop();
		 */
		// myWinch.testControlledRoller();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		if (Winch.myViceXbox.getYButton())
			myServo.set(0.5);
		/*
		 * if(Winch.myViceXbox.getXButton()) myServo.set(0);
		 */
		// myChassis.moderateDrive();
		// System.out.println(AutoDrive.rightEncoder.get());
		myWinch.testControlledRoller();

		// if(Winch.myViceXbox.getXButton())
		// Intake.neutral();
		/*
		 * if(isFirstTime) { AutoDrive.gyro.reset(); isFirstTime = false; }
		 * myChassis.moderateDrive();
		 */

		// m_left.set(sp);
		// m_right.set(-sp);
		if (Winch.myViceXbox.getAButton()) {
			Intake.grab();
		}
		/*
		 * if(Winch.myViceXbox.getBButton()) { Intake.unGrab(); }
		 */
	}
}
