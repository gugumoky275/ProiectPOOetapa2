import database.InitialData;
import fileio.Input;
import fileio.InputLoader;
import game.Simulation;

/**
 * Entry point to the simulation
 */
public final class Main {

    private Main() { }

    /**
     * Main function which reads the input file and starts simulation
     *
     * @param args input and output files
     * @throws Exception might error when reading/writing/opening files, parsing JSON
     */
    public static void main(final String[] args) throws Exception {
        // Reading the data
        InputLoader inputLoader = InputLoader.getInstance();
        Input input = inputLoader.readData(args[0]);

        // Simulating the game
        InitialData results;
        Simulation simulation = new Simulation(input);
        results = simulation.runSimulation();

        // Writing the result

    }
}
