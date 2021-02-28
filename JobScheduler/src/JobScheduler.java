import java.util.Scanner;

public class JobScheduler {
	int i, j;
	private int noOfJobs =0;
	private int noOfTimes=2;
	private int[][] process;
	private int[] completionTime;
	private int[] turnAroundTime;
	private int[] waitingTime;
	Scanner input = new Scanner(System.in);
	
	// for input of arrival time and burst time
	public void getInput(){
		System.out.print("Enter the number of jobs: ");
		this.noOfJobs = input.nextInt();
		process = new int[noOfJobs][noOfTimes];
		//input of jobs
		for(i=0; i < noOfJobs; i++){
			System.out.println("Input for process " + (i+1));
			for( j = 0; j < noOfTimes ; j++){				
				process[i][j] = input.nextInt();
			}
		}
	}
	
	// for output of arrival time and burst time
	private void displayInput(){
		for(i=0;i<noOfJobs;i++){
			for(j=0;j<noOfTimes;j++){
				System.out.print(process[i][j]+"  ");
			}
			System.out.println("\n");
		}
			
	}
	
	private void getOutput(String s, int []arr){
		System.out.println();
		System.out.println(s+" Time of processes are:");
		for(i=0;i<noOfJobs;i++){
			System.out.println("P"+(i+1)+" = "+arr[i]);
		}
	}
	// calculate completion time of each process
	private void calcCompletionTime(){
		completionTime = new int[noOfJobs];
		
		completionTime[0] = process[0][1];
		for(i = 1 ; i < noOfJobs; i++){
			if ( process[i][0] > (process[i-1][0] +process[i-1][1])){
				completionTime[i] = process[i][0] + process[i][1];
			}
			else{
				completionTime[i] = completionTime[i-1]+process[i][1];
			}
		}
			
		this.getOutput("Completion", this.completionTime);
		
	}
	// calculate turnaround time of each process
	private void calcTurnAroundTime(){
		turnAroundTime = new int[noOfJobs];
		
		for(i = 0 ; i < noOfJobs; i++){
			turnAroundTime[i] = completionTime[i] - process[i][0];
		}
			
		this.getOutput("TurnAround", turnAroundTime);
	}
	// calculate waiting time of each process
	private void calcWaitingTime(){
		waitingTime = new int[noOfJobs];
		
		for(i = 0 ; i < noOfJobs; i++){
			waitingTime[i] = turnAroundTime[i] - process[i][1];
		}
			
		this.getOutput("Waiting", waitingTime);
	}
	
	static int sum(int[] arr) 
    { 
        int sum = 0; // initialize sum 
        int i; 
       
        // Iterate through all elements and add them to sum 
        for (i = 0; i < arr.length; i++) 
           sum +=  arr[i]; 
       
        return sum; 
    } 

	// calculate average waiting time
	private void calcAvgWaitingTime(){
		double totalWaitingTime = 0;
		double avgWaitingTime = 0;
		
		totalWaitingTime = sum(waitingTime);
		avgWaitingTime = totalWaitingTime/noOfJobs;
		System.out.println();
		System.out.println("Average Waiting Time : " +avgWaitingTime +" units");
	}

	// calculate maximum waiting time
	private void calcMaxWaitingTime(){
		int maxWaitingTime = waitingTime[0];
		int index =1;
		
		for( i=1; i < noOfJobs ;i++){
			if(maxWaitingTime < waitingTime[i]){
				maxWaitingTime = waitingTime[i];
				index = i+1;
			}
		}
		System.out.println();
		System.out.println("Maximum Waiting Time : " +maxWaitingTime + " and the process is : P" +index);
	}
	
	//start of main function
	public static void main(String[] args) {
		JobScheduler obj = new JobScheduler();
		
		// take input of arrival time and burst time
		obj.getInput();
		//display input for review
		obj.displayInput();
		// call method to calculate completion time
		obj.calcCompletionTime();
		// call method to calculate turnaround time
		obj.calcTurnAroundTime();
		// call method to calculate waiting time
		obj.calcWaitingTime();
		// call method to calculate average waiting time
		obj.calcAvgWaitingTime();		
		// call method to calculate max waiting time
		obj.calcMaxWaitingTime();		
	}

}