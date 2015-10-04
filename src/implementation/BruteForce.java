package implementation;
import java.util.List;

import interfaces.Job;
import interfaces.MinTarOPT;

public class BruteForce implements MinTarOPT{
	List<Job> jobs;
	List<List<Job>> permutations;
	int tardiness = Integer.MAX_VALUE;
	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "BruteForce";
	}

	@Override
	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processJobs() {
		if(this.permutations == null){
			this.permutations = Util.permutations(this.jobs);
		}
		int min = Integer.MAX_VALUE;
		for(List<Job> j : this.permutations) {
			int t = Util.computeTardiness(j, 0);
			if( t < min){
				min = t;
			}
		}
		this.tardiness = min;
		
	}

	@Override
	public int getTardiness() {
		// TODO Auto-generated method stub
		return tardiness;
	}

}
