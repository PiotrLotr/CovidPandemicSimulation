import java.util.ArrayList;
import java.util.Random;

public class Simulation {

    private final String simulationName = "covidPandemicSimulation";
    private int populationSize;
    private int infectedAtTheBeginning;
    private int infectionRate;
    private int deathRate;
    private int daysToRecover;
    private int daysToDeath;
    private int simulationDaysPeriod;

    public Simulation(Population population) {
        this.populationSize = getRandomNumberUsingNextInt(100000, 400000);
        ArrayList tempArrayList = new ArrayList();
        for (int i = 0; i < this.populationSize; i++) {
            var newParticipant = new SimulationParticipant();
            population.getHealthyInfectionVulnerable().add(newParticipant);
        }
        this.infectedAtTheBeginning = getRandomNumberUsingNextInt(5,15);
        this.infectionRate = getRandomNumberUsingNextInt(2,4);
        this.deathRate = getRandomNumberUsingNextInt(3,10);
        this.daysToRecover = getRandomNumberUsingNextInt(10,20);
        this.daysToDeath = getRandomNumberUsingNextInt(13,14);
        this.simulationDaysPeriod = getRandomNumberUsingNextInt(100,360);
    }

    private void InfectionAccInfectionRate(Population population, int simulationDay) {
        var actuallyInfected = population.getInfected().size();

        for (int i = 0; i < actuallyInfected; i++) {
            for (int j = 0; j < infectionRate; j++) {
                if (!population.getHealthyInfectionVulnerable().isEmpty()) {
                    var simulationParticipant = population.getHealthyInfectionVulnerable().get(
                            getRandomNumberUsingNextInt(0, population.getHealthyInfectionVulnerable().size()));
                    Infect(population, simulationDay, simulationParticipant);
                }
            }
        }
    }

    private void Infect(Population population, int simulationDay, SimulationParticipant simulationParticipant) {
        population.getHealthyInfectionVulnerable().remove(simulationParticipant);
        population.getInfected().add(simulationParticipant);
        simulationParticipant.setDayOfInfection(simulationDay);
    }

    private void Kill(Population population, SimulationParticipant illParticipant) {
        population.getInfected().remove(illParticipant);
        population.getDeaths().add(illParticipant);
    }

    private void DeathAccDeathRate(Population population, int simulationDay) {
        var actuallyInfected = population.getInfected().size();
        int deathCount = 0;
        Random random = new Random();
        ArrayList deathList = new ArrayList();
        for (int i = 0; i < actuallyInfected; i++) {
            var isLucky = random.nextBoolean();
            if (!isLucky) {
                var illParticipant = population.getInfected().get(i);
                if (simulationDay - illParticipant.getDayOfInfection() == daysToDeath && deathCount != deathRate) {
                    ++deathCount;
                    deathList.add(illParticipant);
                }
            }
        }
        population.getDeaths().addAll(deathList);
        population.getInfected().removeAll(deathList);
    }

    private void ShiftToRecover(Population population, int simulationDay) {
        var actuallyInfected = population.getInfected().size();
        ArrayList recoverList = new ArrayList();
        for (int i = 0; i < actuallyInfected; i++) {
            var illParticipant = population.getInfected().get(i);
            if (simulationDay - illParticipant.getDayOfInfection() == daysToRecover) {
                recoverList.add(illParticipant);
            }
        }
        population.getHealthyInfectionInvulnerable().addAll(recoverList);
        population.getInfected().removeAll(recoverList);
    }

    public void dailyPandemicChangesOnPopulation(Population population, int simulationDay) {
        // infection another participants acc. to R index (infection rate)
        if (!population.getHealthyInfectionVulnerable().isEmpty()) {
            InfectionAccInfectionRate(population, simulationDay);
        }
        // shifting infected to death acc. to M index (death rate)
        DeathAccDeathRate(population, simulationDay);
        // shifting infected to recover
        ShiftToRecover(population, simulationDay);
    }

    public void freeTheBat(Population population) {
        for (int i = 0; i < infectedAtTheBeginning; i++) {
            var simulationParticipant = population.getHealthyInfectionVulnerable().get(
                    getRandomNumberUsingNextInt(0, population.getHealthyInfectionVulnerable().size()));
            Infect(population, 0, simulationParticipant);
        }
        System.out.println("STARTING PARAMETERS OF SIMULATION:");
        System.out.println(toString());
    }

    public void printDailyStatistics(Population population, int day) {
        System.out.println("DAY" + day);
        System.out.println(population.toString());
    }

    public void startSimulation(Population population) {
        for (int day = 0; day < simulationDaysPeriod; day++) {
            if (day == 0) {
                freeTheBat(population);
                continue;
            }
            dailyPandemicChangesOnPopulation(population, day);
            printDailyStatistics(population, day);
            if(population.getInfected().isEmpty()){
                return;
            }
        }
    }

    @Override
    public String toString() {
        return "Simulation{" +
                "simulationName='" + simulationName + '\'' +
                ", populationSize=" + populationSize +
                ", infectedAtTheBeginning=" + infectedAtTheBeginning +
                ", infectionRate=" + infectionRate +
                ", deathRate=" + deathRate +
                ", daysToRecover=" + daysToRecover +
                ", daysToDeath=" + daysToDeath +
                ", simulationDaysPeriod=" + simulationDaysPeriod +
                '}';
    }

    public static int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

}
