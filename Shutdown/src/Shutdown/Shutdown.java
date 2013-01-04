package Shutdown;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class Shutdown {

	public static final String SHUTDOWN = "/s";
	public static final String RESTART = "/r";
	
	private static final int MAX_SECONDS = 315360000;

	private Shutdown() {
		//stop instantiation
	}

	/**
	 * Returns the maximum minute value that can be selected.
	 * 
	 * @return max minute value
	 */
	public static int maxMinutes() {
		return MAX_SECONDS / 60 - 1;
	}
	
	/**
	 * Attempts to shutdown the computer
	 * @param minutes
	 * @return
	 */
	public static boolean initiateShutdown(int minutes, String mode) {
		if (minutes < 0) throw new IllegalArgumentException("Minutes cannot be negative");
		if (minutes > maxMinutes()) throw new IllegalArgumentException("Minutes too large");

		List<String> list = new ArrayList<String>();

		list.add("shutdown");
		list.add(mode);
		list.add("/t");
		list.add(Integer.toString(minutes * 60));

		return (executeCommand(list) == ShutdownResult.success);
	}

	/**
	 * Attempts to stop the current shutdown process.<p>
	 * 
	 * Returns true if there was a shutdown and it has now been cancelled.
	 * Returns false if the computer wasn't currently shutting down.
	 * 
	 * @return
	 */
	public static boolean cancelShutdown() {
		List<String> list = new ArrayList<String>();
		list.add("shutdown");
		list.add("/a");

		if (executeCommand(list) == ShutdownResult.success) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Executes the given commands in a command prompt.
	 * 
	 * @param commands
	 * @return true if success, false if an error
	 */
	private static ShutdownResult executeCommand(List<String> commands) {
		ProcessBuilder cmdProcess = new ProcessBuilder(commands);

		try {
			Process p = cmdProcess.start();

			return getResult(p.getErrorStream().read());
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * Returns the request status based off the first byte of data of the error string (or lack thereof). <p>
	 * 
	 * Intended to be called from executeCommand() with the first byte of the error stream from the process.
	 * 
	 * @param data first byte of error string
	 * @return the request status
	 */
	private static ShutdownResult getResult(int data) {
		switch (data) {
		case 85: return ShutdownResult.nothingToCancel;
		case 64: return ShutdownResult.alreadyShuttingDown;
		case -1: return ShutdownResult.success;
		default: return ShutdownResult.unknown;
		}
	}

	/**
	 * Different status codes for request to shutdown, or cancel shutdown.
	 *
	 * @author Troy Shaw
	 */
	private enum ShutdownResult {success, alreadyShuttingDown, nothingToCancel, unknown}
}