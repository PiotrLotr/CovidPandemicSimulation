public class Main {

    public static void main(String[] args) {

//        int infectedAtTheBeginning, int daysToRecover, int daysToDeath, int simulationDaysPeriod
        Population population = new Population();
        Simulation simulation = new Simulation(population);
        simulation.startSimulation(population);
    }
}
