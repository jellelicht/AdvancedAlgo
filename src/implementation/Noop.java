package implementation;

import java.util.List;

import interfaces.Job;
import interfaces.MinTar;
import interfaces.Schedule;

public class Noop implements MinTar{
	static class NoopSchedule implements Schedule {
		private List<Job> jobs;
		

		public NoopSchedule(List<Job> jobs) {
			this.jobs = jobs;
		}

		@Override
		public int getTardiness() {
			int tardiness = 0;
			int total = 0;
			for(Job j : jobs){
				total+= j.getDuration();
				if(j.getDue() < total){
					tardiness += total - j.getDue();
				}
			}
			return tardiness;
		}

		@Override
		public int getTotalTime() {
			int total = 0;
			for(Job j : jobs){
				total+= j.getDuration();
			}
			return total;
		}

		@Override
		public boolean contains(Job job) {
			// TODO Auto-generated method stub
			return this.jobs.contains(job);
		}

		
	}
	private List<Job> jobs;
	private Schedule schedule;

	@Override
	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	@Override
	public void processJobs() {
		if(this.jobs != null){
			this.schedule = new NoopSchedule(this.jobs);
		}
	}

	@Override
	public int getTardiness() {
		if(this.schedule != null){
			return this.schedule.getTardiness();
		}
		return -1; //invalid tardiness
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Noop";
	}
	
}
