package interfaces;

public interface Schedule{
	public int getTardiness();
	public int getTotalTime();
	public boolean contains(Job job);
}
