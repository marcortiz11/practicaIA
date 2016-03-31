package servers;

import aima.search.framework.HeuristicFunction;
import java.lang.Math;

/**
 * @author Joan Grau
 *  
 */
public class SecondCaseHeuristic implements HeuristicFunction {
	
	@Override
	public double getHeuristicValue(Object state) {
		ServersState estat = (ServersState) state;
		double retVal, mitjana = 0,sum = 0;
		int nserv = estat.getNumServers();
		int serv[] = new int[nserv];
		for(int i=0; i < estat.getNumPetitions(); ++i){//posar temps als servers
			int[] peticio;
			peticio = estat.getTransmissionTime(i);
			serv[peticio[1]] += peticio[0];
		}
		for(int i=0; i < nserv; ++i){
			mitjana += serv[i];
		}
		mitjana /= nserv;
		for(int i=0; i < nserv; ++i){
			sum += (serv[i]-mitjana)*(serv[i]-mitjana);
		}
		retVal = Math.sqrt(1./(nserv-1)*sum);
		return retVal;

	}

}