package implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import interfaces.Job;
import interfaces.MinTarOPT;

public class AlgoWrapper implements MinTarOPT {
	private Algo algo = new Algo();
	private List<Job> jobs;
	private int t = Integer.MAX_VALUE;
	
	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "AlgoWrapper";
	}	
	
	public int getDelta(List<Job> jobs, int t){
		FingerPrint fp = algo.getFingerPrint(jobs, t);
		return algo.getDelta(fp);
	}

	@Override
	public void setJobs(List<Job> jobs) {
		this.jobs = new ArrayList<Job>(jobs.size());
		for(Job j: jobs){
			this.jobs.add(j);
		}
		Collections.sort(this.jobs, new CustomComparator());
		
	}

	@Override
	public void processJobs() {
		// TODO Auto-generated method stub
		this.t = algo.T(jobs, 0);
	}

	@Override
	public int getTardiness() {
		// TODO Auto-generated method stub
		return t;
	}

}
