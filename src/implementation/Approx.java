package implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import interfaces.Job;
import interfaces.MinTar;
import interfaces.MinTarOPT;

public class Approx implements MinTar {
	private AlgoWrapper minTarOpt;
	private List<Job> globalJobs;
	private int tardiness;
	private int epsilon;
	
	public Approx(int epsilon){
		this.epsilon = epsilon;
		this.minTarOpt = new AlgoWrapper();
	}
	
	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Approx";
	}

	@Override
	public void setJobs(List<Job> jobs) {
		this.globalJobs = new ArrayList<Job>(jobs.size());
		for(Job j: jobs){
			this.globalJobs.add(j);
		}
		Collections.sort(this.globalJobs, new CustomComparator());
		
	}
	//private int T
	private int T(List<Job> jobs, int t){
		int n = jobs.size();
		
		//Handle base cases
		if (n == 0){
			return 0;
		} else if (n == 1){
			Job j = jobs.get(0);
			int tardiness = Math.max(t+j.getDuration()-j.getDue(), 0);
			return tardiness;
		}
		
		int op = jobs.get(0).getDuration();
		int k = 0;
		
		//Calculate k
		for(int j = 1; j < n; j++){
			if(jobs.get(j).getDuration() > op){
				k =j;
				op = jobs.get(j).getDuration();
			}
		}
		
		//Calculate max_delta
		int delta = this.minTarOpt.getDelta(jobs, t);

		int sub_tardiness;
		List<Job> ji = new ArrayList<Job>(jobs.subList(0, k+delta+1));
		ji.remove(jobs.get(k));
		List<Job> jiii = new ArrayList<Job>(jobs.subList(k+delta+1, jobs.size())); // 4 + 1 + 1
			
		int ti = T(ji, t);
		int stk = Math.max(0,  
				t + Util.C(ji)+jobs.get(k).getDuration()- // forgot to add t
				jobs.get(k).getDue());
		int tiii = T(jiii, t+Util.C(ji)+jobs.get(k).getDuration()); // forgot to add t
		sub_tardiness = ti + stk + tiii;
		return sub_tardiness;
	}


	@Override
	public void processJobs() {
		int TMax = Util.computeTMax(globalJobs, 0);
		int K, n;
		if(TMax == 0){
			tardiness = 0;
		} else {
			n = globalJobs.size();
			K = (2*this.epsilon)/(n * (n+1)) * TMax;
			
			// scale processing times with rounding by dividing by K
			// scale due times without rounding by dividing by K -- requires floats EVERYWHERE >:(
			minTarOpt.setJobs(globalJobs); // TODO -- pass modified jobs
			minTarOpt.processJobs();
			this.tardiness = T(this.globalJobs, 0); // used saved deltas from minTarOpt to construct solution
			
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTardiness() {
		// TODO Auto-generated method stub
		return tardiness;
	}
}
