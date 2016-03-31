import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import aima.search.framework.HeuristicFunction;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;
import servers.FirstCaseHeuristic;
//import servers.SecondCaseHeuristic;
import servers.ServersGoalState;
import servers.ServersState;
import servers.ServersSuccessors;

public class ServersDemo {

	public static void main(String[] args) {
		newServersDemo();
	}

	private static void newServersDemo() {
        serversHillCriteria(new FirstCaseHeuristic(), "First Criteria");
	}
	
	private static void serversHillCriteria(HeuristicFunction cirteria, String name) {
		System.out.println("ServersDemo HillClimbing -" + name + " -->");
		try {
		    
		    int users = 200;
            int nrequests = 5;
            int rseed = 1234;
            int nserv = 50;
            int nrep = 5;
            int sseed = 1234;
		    
			Problem problem =  new Problem(
			    new ServersState(
			            users,
                        nrequests,
                        rseed,
                        nserv,
                        nrep,
                        sseed
			        ),
			    new ServersSuccessors(),
			    new ServersGoalState(),
			    cirteria
			    );
			Search search =  new HillClimbingSearch();
			SearchAgent agent = new SearchAgent(problem,search);

			System.out.println();
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void printInstrumentation(Properties properties) {
		Iterator keys = properties.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String property = properties.getProperty(key);
			System.out.println(key + " : " + property);
		}

	}

	private static void printActions(List actions) {
		for (int i = 0; i < actions.size(); i++) {
			String action = (String) actions.get(i);
			System.out.println(action);
		}
	}

}