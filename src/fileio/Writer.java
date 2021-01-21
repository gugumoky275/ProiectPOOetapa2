package fileio;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import database.InitialData;
import entities.Consumer;
import entities.Contract;
import entities.Distributor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public final class Writer {
    private static Writer instance = null;

    private Writer() { }

    /**
     * Get instance of Singleton class Writer
     * @return the instance
     */
    public static Writer getInstance() {
        if (instance == null) {
            instance = new Writer();
        }
        return instance;
    }

    /**
     * Writes the data
     * @param path path to output file
     * @param result the output wanted
     * @throws IOException IOException
     */
    public void writeData(final String path, final InitialData result) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

        Output output = new Output();
        ArrayList<ConsumerOutput> consumers = new ArrayList<>();
        ArrayList<DistributorOutput> distributors = new ArrayList<>();

        // Copy consumer fields to be written
        for (Consumer consumer : result.getConsumers()) {
            ConsumerOutput consumerOutput = (ConsumerOutput)
                    EntityFactory.createEntity(EntityFactory.EntityTypes.CUSTOMER);

            consumerOutput.setId(consumer.getId());
            consumerOutput.setIsBankrupt(consumer.isBankrupt());
            consumerOutput.setBudget(consumer.getBudget());

            consumers.add(consumerOutput);
        }

        // Copy distributor fields to be written
        for (Distributor distributor : result.getDistributors()) {
            DistributorOutput distributorOutput = (DistributorOutput)
                    EntityFactory.createEntity(EntityFactory.EntityTypes.DISTRIBUTOR);

            distributorOutput.setId(distributor.getId());
            distributorOutput.setBudget(distributor.getBudget());
            distributorOutput.setIsBankrupt(distributor.isBankrupt());
            distributorOutput.setContracts(distributor.getContracts());

            distributors.add(distributorOutput);
        }

        output.setConsumers(consumers);
        output.setDistributors(distributors);

        objectMapper.writeValue(new File(path), output);

    }
}

class Output {
    private ArrayList<ConsumerOutput> consumers;
    private ArrayList<DistributorOutput> distributors;

    Output() {
        this.consumers = new ArrayList<>();
        this.distributors = new ArrayList<>();
    }

    public ArrayList<ConsumerOutput> getConsumers() {
        return consumers;
    }

    public void setConsumers(final ArrayList<ConsumerOutput> consumers) {
        this.consumers = consumers;
    }

    public ArrayList<DistributorOutput> getDistributors() {
        return distributors;
    }

    public void setDistributors(final ArrayList<DistributorOutput> distributors) {
        this.distributors = distributors;
    }
}

class EntityOutput {
    protected int id;
    protected boolean isBankrupt;
    protected int budget;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public boolean getIsBankrupt() {
        return isBankrupt;
    }

    public void setIsBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(final int budget) {
        this.budget = budget;
    }
}

class ConsumerOutput extends EntityOutput {
    ConsumerOutput() { }
}

class DistributorOutput extends EntityOutput {
    private ArrayList<Contract> contracts;

    DistributorOutput() {
        contracts = new ArrayList<>();
    }

    public ArrayList<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(final ArrayList<Contract> contracts) {
        this.contracts = contracts;
    }
}

final class EntityFactory {
    public enum EntityTypes {
        CUSTOMER, DISTRIBUTOR
    }

    private EntityFactory() { }

    public static EntityOutput createEntity(final EntityTypes entityType) {
        switch (entityType) {
            case CUSTOMER:
                return new ConsumerOutput();
            case DISTRIBUTOR:
                return new DistributorOutput();
            default:
                System.out.println("No such entity! (yet)");
                return new EntityOutput();
        }
    }
}

