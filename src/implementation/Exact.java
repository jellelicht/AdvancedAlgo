package implementation;

import java.util.List;

import external.schedule;
import interfaces.Job;
import interfaces.LinkedSchedule;
import interfaces.MinTarOPT;
import interfaces.Schedule;

public class Exact implements MinTarOPT{
	private List<Job> jobs;
	
	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Exact";
	}

	@Override
	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	@Override
	public void processJobs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTardiness() {
		// TODO Auto-generated method stub
		return 0;
	}

}

class ExactSchedule implements LinkedSchedule{

	private LinkedSchedule prev;
	private Job job;
	
	public ExactSchedule(LinkedSchedule prev, Job job) {
		this.prev = prev;
		this.job = job;
	}

	@Override
	public int getTardiness() {
		int time = this.getTotalTime();
		
		int pt = prev != null?  prev.getTardiness() : 0;
		int ct = time > job.getDue() ? time - job.getDue() : 0;
		return pt + ct;
	}

	@Override
	public int getTotalTime() {
		int pt = prev != null? prev.getTotalTime() : 0;
		return pt + job.getDuration();
	}

	@Override
	public boolean contains(Job job) {		
		return job.equals(this.job) || (prev != null && prev.contains(job));
	}

	@Override
	public int compareTo(LinkedSchedule o) {
		// TODO Auto-generated method stub
		return 0;
	}	
}
