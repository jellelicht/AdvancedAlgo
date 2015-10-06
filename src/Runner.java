import java.util.List;

import implementation.Noop;
import implementation.Util;
import interfaces.Job;
import interfaces.MinTar;

public class Runner {

	public static void main(String args[]) {
		List<Job> jobs = Util.read_problem(args[0]);
		for(MinTar mt: Util.strategies()){
			//System.out.println(mt.getLabel() + ": ");
			mt.setJobs(jobs);
			mt.processJobs();
			int tardiness = mt.getTardiness();
			//System.out.println("\t" + tardiness);
			System.out.println(tardiness);
		}
		
	}
}
