package servers;

import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

public class ServersSuccessors implements SuccessorFunction {

	public List<Successor> getSuccessors(Object state) {

		List<Successor> successors = new ArrayList<>();
		ServersState servers_state = (ServersState) state;
		for(int i = 0; i<servers_state.getNumPetitions();++i){
			int file_id = servers_state.getFileID(i);
			Set<Integer> servers = servers_state.getServersIDs(file_id);
			Iterator<Integer> j = servers.iterator();
			while(j.hasNext()){
				int serv_id = j.next();
				ServersState child = makeChild(servers_state,i,serv_id);
				if (!servers_state.equals(child)) {
					successors.add(
						new Successor(
							"Server " +serv_id+ " serves now " + i + " petition",
							child
							)
						);
				}
			}
		}
		return successors;
	}

	private ServersState makeChild(ServersState parent, int reqID, int servID) {
		ServersState newchild = parent.copy();
		newchild.movePetition(reqID,servID);
		return newchild;
	}

}
