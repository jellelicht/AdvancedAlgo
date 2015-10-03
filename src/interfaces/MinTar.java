package interfaces;

import java.util.List;

/**
 * A class implementing this interface signifies that it solves the Minimum Tardiness algorithm
 */
public interface MinTar {
	public String getLabel();
	public void setJobs(List<Job> jobs);
	public void processJobs();
	public int getTardiness();
}
