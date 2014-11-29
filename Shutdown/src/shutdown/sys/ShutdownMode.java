package shutdown.sys;

/**
 * Flags for the different modes available to shutdown.
 * These are straight shutdown, and also restart.
 *
 * @author troy
 */
public enum ShutdownMode {
	Shutdown("/s"),
	Restart("/r");
	
	private String flag;
	
	private ShutdownMode(String flag) {
		this.flag = flag;
	}
	
	/**
	 * Gets the flag associated with this command.
	 * 
	 * @return
	 */
	public String getFlagString() {
		return flag;
	}
}
