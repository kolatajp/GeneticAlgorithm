package rmiGenetic;

import java.rmi.Remote;
import java.rmi.RemoteException;
import newpackage.Specimen;

public interface GeneticAlgorithmInterface extends Remote {
    public void computeEvolution() throws RemoteException;
    public Specimen getBest() throws RemoteException;
    public void init( double crossBreedProbability,
                             double mutationProbability, double popPercentageToBreed,
                             int populationSize, int generations ) throws RemoteException;
    public void setFunction( String fname ) throws RemoteException;
    public void initPopulation() throws RemoteException;
    public String getBestSerial() throws RemoteException;
}
