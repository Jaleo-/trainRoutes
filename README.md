#TrainRoutes Program 

## Summary

Program is designed to analyze a one way train station.  Given a directed graph where each node represents a town and an edge is a
route connecting the two towns.  Each edge is weighted to represent the distance between the towns.  With the given input the program can compute the distance/existence of a specific route, the shortest possible rotue between two towns,  along with the number of possible trips between two towns with a maximum or exact number of possible stops.

 
## Initialization
I ran this program on Eclipse, specifically the Kepler release.  After the project is unzipped, you can import it into Eclipse.  Under the menu's file option click import.
On the new display go in the General option and pick existing projects into workspace.  A main.java file is made that runs with the sample tests.  However, it will only
produce a series of 'NO SUCH ROUTE'.  You need to enter the sample input into the arguments for run.  In projectExplorer right click Main.java and under 'Run as' select
run configurations.   In the arguments tab enter AB5 BC4 CD8 DC8 DE6 AD5 CE2 EB3 AE7 into program arguments.
  

## Testing

Another option is to run the java test case as it enters in the sample input into routesApplication and checks that it produces the correct sample output.
