import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import external.algorithms;
import implementation.Noop;
import interfaces.Job;
import interfaces.MinTar;

public class Util {
	static class JobImpl implements Job {
		private int duration;
		private int due;
		

		public JobImpl(int duration, int due) {
			this.duration = duration;
			this.due = due;
		}

		@Override
		public int getDuration() {
			// TODO Auto-generated method stub
			return duration;
		}

		@Override
		public int getDue() {
			// TODO Auto-generated method stub
			return due;
		}
		
	}
	public static List<MinTar> strategies(){
		List<MinTar> retval = new ArrayList<MinTar>();
		// Add more algorithms here:
		MinTar noop = new Noop();
		retval.add(noop);
		MinTar greedy = algorithms.greedy();
		retval.add(greedy);
		MinTar bestfirst = algorithms.bestfirst();
		retval.add(bestfirst);	
		//
		
		return retval;
	}
	public static List<Job> read_problem(String text_file) {
		Scanner s = null;
		List<Job> jobs = null;// = new ArrayList<Job>();
		int NumJobs;
		try {
			s = new Scanner(new BufferedReader(new FileReader(text_file)));
			if (s.hasNextInt()) {
				NumJobs = s.nextInt();
				jobs = new ArrayList<Job>(NumJobs);
				int job = 0;

				while (s.hasNextInt() && job < NumJobs) {
					int l = s.nextInt();
					int d = s.nextInt(); // unsafe?
					jobs.add(new Util.JobImpl(l, d));
					++job;
				}
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if(jobs != null){ 
			return jobs;
		}
		return new ArrayList<Job>(); // just supply empty list if nothing is available
	}
}
