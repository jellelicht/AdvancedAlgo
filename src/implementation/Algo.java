package implementation;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import interfaces.Job;

public class Algo {
	private List<Job> globalJobs;
	private Map<FingerPrint, Integer> OPT;
	private Map<FingerPrint, Integer> deltas;
	
	public int getDelta(FingerPrint fp){
		return deltas.get(fp);
	}
	
	public FingerPrint getFingerPrint(List<Job> jobs, int t){
		BitSet bs = new BitSet(globalJobs.size());
		Job j;
		for(int i=0; i< globalJobs.size(); i++){
			j=globalJobs.get(i);
			if(jobs.contains(j)){
				bs.set(i);
			}
		}
		
		return new FingerPrint(bs, t);
	}
	public int C(List<Job> jobs){
		
		int sum = 0;
		
		for(Job j: jobs){
			sum += j.getDuration();
		}
		
		return sum;
	}
	
	public int T(List<Job> jobs, int t){
		if(this.OPT == null){ // first top level run detection
			this.globalJobs = jobs;
			this.OPT = new HashMap<FingerPrint, Integer>();
			this.deltas = new HashMap<FingerPrint, Integer>();
		}
		FingerPrint fp = getFingerPrint(jobs, t);
		if(OPT.containsKey(fp)){
			 return OPT.get(fp);
		}
		int n = jobs.size();
		
		//Handle base cases
		if (n == 0){
			OPT.put(fp, 0);
			return 0;
		} else if (n == 1){
			Job j = jobs.get(0);
			int tardiness = Math.max(t+j.getDuration()-j.getDue(), 0);
			OPT.put(fp, tardiness);
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
		int max_delta = n-k-1; // correction for 0 based indexes; // e.g. 5-4

		int tardiness = Integer.MAX_VALUE;
		//Calculate sub problems
		int chosen_delta= -1;
		for(int delta = 0; delta <= max_delta; delta++){ //max delta will be 1:
			int sub_tardiness;
			List<Job> ji = new ArrayList<Job>(jobs.subList(0, k+delta+1));
			ji.remove(jobs.get(k));
			List<Job> jiii = new ArrayList<Job>(jobs.subList(k+delta+1, jobs.size())); // 4 + 1 + 1
			
			int ti = T(ji, t);
			int stk = Math.max(0,  
					t + C(ji)+jobs.get(k).getDuration()- // forgot to add t
					jobs.get(k).getDue());
			int tiii = T(jiii, t+C(ji)+jobs.get(k).getDuration()); // forgot to add t
			sub_tardiness = ti + stk + tiii;

			if(sub_tardiness < tardiness){
				tardiness = sub_tardiness;
				chosen_delta = delta;
			}
		}
		deltas.put(fp, chosen_delta);
		OPT.put(fp, tardiness);
		return tardiness;
	}
}