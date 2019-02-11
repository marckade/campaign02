package edu.isu.cs.cs3308;

import edu.isu.cs.cs3308.structures.Queue;
import edu.isu.cs.cs3308.structures.impl.LinkedQueue;
import java.util.Random;

/**
 * Class representing a wait time simulation program.
 *
 * @author Isaac Griffith
 * @author
 */
public class Simulation {

    private int arrivalRate;
    private int maxNumQueues;
    private Random r;
    private int numIterations = 50;
    private int numberOfQues = 0;
    private int curTime = 0;
    private int peopleFinished = 0;
    private int queWaitTime = 0;
    private int iterWait = 0;
    private LinkedQueue<Integer>[] lines;


    /**
     * Constructs a new simulation with the given arrival rate and maximum number of queues. The Random
     * number generator is seeded with the current time. This defaults to using 50 iterations.
     *
     * @param arrivalRate the integer rate representing the maximum number of new people to arrive each minute
     * @param maxNumQueues the maximum number of lines that are open
     */
    public Simulation(int arrivalRate, int maxNumQueues) {
        this.arrivalRate = arrivalRate;

        this.maxNumQueues = maxNumQueues;
        r = new Random();
    }

    /**
     * Constructs a new siulation with the given arrival rate and maximum number of queues. The Random
     * number generator is seeded with the provided seed value, and the number of iterations is set to
     * the provided value.
     *
     * @param arrivalRate the integer rate representing the maximum number of new people to arrive each minute
     * @param maxNumQueues the maximum number of lines that are open
     * @param numIterations the number of iterations used to improve data
     * @param seed the initial seed value for the random number generator
     */
    public Simulation(int arrivalRate, int maxNumQueues, int numIterations, int seed) {
        this(arrivalRate, maxNumQueues);
        r = new Random(seed);
        this.numIterations = numIterations;
    }

    /**
     * Executes the Simulation
     */
    public void runSimulation() {

        //Queues are lines in this case. We will need a certain amount of them depending on the amount of lines

        for(int i = 1; i <= maxNumQueues; i++)
        {
            // Count the number of ques based on how many times the iteration goes through.
            numberOfQues = i;

            for(int j = 0; j < numIterations; j++)
            {
                // Call create que method.
                createQueue();

                for(int k = 1; k <= 720; k++)
                {
                    // Sets the iteration to the current time so we can store it for the people.
                    curTime = k;
                    addToLines();
                    removePeople();
                }

                iterWait += queWaitTime / peopleFinished;
            }

            System.out.println("Average wait time using "
                    +(numberOfQues)+" queue(s): "+(iterWait / numIterations));

            peopleFinished = 0;
            queWaitTime = 0;
            iterWait = 0;

        }

    }

    public void removePeople()
    {
        for(int i = 0; i < 2; i++)
        {
            for(int j = 0; j < numberOfQues; j++)
            {
                if(lines[j].size() > 0)
                {
                    queWaitTime += curTime - lines[j].poll();
                    peopleFinished += 1;
                }
            }
        }
    }

    public void addToLines()
    {
        // Creat int to store the random seed created by Isaac's method.
        int people = getRandomNumPeople(arrivalRate);

        //Loop through the random'd number above and add a person to the shortest que (line)
        for(int i = 0; i < people; i++)
        {
            //Sets the smallest que to the first var as a failcheck if the size of the que is only 1.
            int smallestQue = lines[0].size();
            int smallestQueIndex = 0; //Set to 0 as failsafe.

          // Loops through the lines.
            for(int j = 0; j <numberOfQues; j++)
            {
                //Create a temp variable to store the size of the lines we are looping through.
                int currentIterationSize = 0;

                //Set the temp variable to the current line via the loop.
                currentIterationSize = lines[j].size();

                //If that current size is smaller than the rest, replace it as the smallest along with index.
                if(currentIterationSize < smallestQue)
                {
                    smallestQue = currentIterationSize;
                    smallestQueIndex = j;
                }
            }


            lines[smallestQueIndex].offer(curTime - 1);

        }
    }

    public void createQueue()
    {
        // Initiaiate the array of ques.
        lines = new LinkedQueue[numberOfQues];

        //Loop and create a linked Que for each line.
        for(int i = 0; i < numberOfQues; i++)
        {
            lines[i] = new LinkedQueue<>();
        }
    }

    /**
     * returns a number of people based on the provided average
     *
     * @param avg The average number of people to generate
     * @return An integer representing the number of people generated this minute
     */
    //Don't change this method.
    private static int getRandomNumPeople(double avg) {
        Random r = new Random();
        double L = Math.exp(-avg);
        int k = 0;
        double p = 1.0;
        do {
            p = p * r.nextDouble();
            k++;
        } while (p > L);
        return k - 1;
    }
}
