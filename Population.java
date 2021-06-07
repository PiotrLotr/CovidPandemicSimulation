import java.util.ArrayList;

public class Population {

    private ArrayList<SimulationParticipant> infected;
    private ArrayList<SimulationParticipant> healthyInfectionVulnerable;
    private ArrayList<SimulationParticipant> deaths;
    private ArrayList<SimulationParticipant> healthyInfectionInvulnerable;

    // generation random population
    public Population() {
        this.infected = new ArrayList<>();
        this.healthyInfectionVulnerable = new ArrayList<>();
        this.deaths = new ArrayList<>();
        this.healthyInfectionInvulnerable = new ArrayList<>();
    }

    public ArrayList<SimulationParticipant> getInfected() {
        return infected;
    }

    public void setInfected(ArrayList<SimulationParticipant> infected) {
        this.infected = infected;
    }

    public ArrayList<SimulationParticipant> getHealthyInfectionVulnerable() {
        return healthyInfectionVulnerable;
    }

    public void setHealthyInfectionVulnerable(ArrayList<SimulationParticipant> healthyInfectionVulnerable) {
        this.healthyInfectionVulnerable = healthyInfectionVulnerable;
    }

    public ArrayList<SimulationParticipant> getDeaths() {
        return deaths;
    }

    public void setDeaths(ArrayList<SimulationParticipant> deaths) {
        this.deaths = deaths;
    }

    public ArrayList<SimulationParticipant> getHealthyInfectionInvulnerable() {
        return healthyInfectionInvulnerable;
    }

    public void setHealthyInfectionInvulnerable(ArrayList<SimulationParticipant> healthyInfectionInvulnerable) {
        this.healthyInfectionInvulnerable = healthyInfectionInvulnerable;
    }

    @Override
    public String toString() {
        return "Population{" +
                "infected=" + getInfected().size() +
                ", healthyInfectionVulnerable=" + getHealthyInfectionVulnerable().size() +
                ", deaths=" + getDeaths().size() +
                ", healthyInfectionInvulnerable=" + getHealthyInfectionInvulnerable().size() +
                '}';
    }
}
