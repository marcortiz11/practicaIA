package servers;

import aima.search.framework.HeuristicFunction;

/**
 * @author Joan Grau
 *  
 */
public class FirstCaseHeuristic implements HeuristicFunction {
	
	@Override
	public double getHeuristicValue(Object state) {
		ServersState estat = (ServersState) state;
		int retVal = 0;
		for(int i=0; i < estat.getNumPetitions(); ++i){
			retVal += estat.getTransmissionTime(i)[0];
		}
		return retVal;

	}

}