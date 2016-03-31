package servers;

import java.util.Set;
import IA.DistFS.Servers;
import IA.DistFS.Requests;

public class ServersState {
    
    private int[] petitionLoc;
    private static boolean instantiated = false;
    private static Requests requests;
    private static Servers servers;
    
    public ServersState(
        int users,
        int nrequests,
        int rseed,
        int nserv,
        int nrep,
        int sseed) {
        
        if (!instantiated) {
        	try {
	        	instantiated = true;
		        requests = new Requests(users,nrequests,rseed);
		        servers = new Servers(nserv,nrep,sseed);
		        petitionLoc = new int[requests.size()];
		        
		        GenerateFirstApproximation();
        	} catch (Servers.WrongParametersException e) {
        		System.out.println("Why god, why?");
        	}
        }
    }
    
    private int getLocOf(int petitionId) {
    	return petitionLoc[petitionId];
    }
    
    private void GenerateFirstApproximation() {
    	int serverId = getServersIDs(0).iterator().next();
    	for (int i=0; i<petitionLoc.length; i++) {
    		petitionLoc[i] = serverId;
    	}
    }
    
    private void setLoc(int[] locs) {
    	petitionLoc = locs.clone();
    }
    
    public ServersState copy() {
    	ServersState res = new ServersState(0,0,0,0,0,0);
    	res.setLoc(petitionLoc);
		return res;
    }
    
    public int getNumPetitions() {
    	return requests.size();
    }
    
    public int getNumServers() {
    	return servers.size();
    }
    
    public Set<Integer> getServersIDs(int fileID){
    	return servers.fileLocations(fileID);
    }
    
    public int getServerID(int i) {
        return petitionLoc[i];
    }

	public void movePetition(int petitionID, int serverID) {
		petitionLoc[petitionID] = serverID;
	}
	
	public int getUserID(int petitionID) {
		return requests.getRequest(petitionID)[0];
	}
	
	public int getFileID(int petitionID) {
		return requests.getRequest(petitionID)[1];
	}
	
	public int[] getTransmissionTime(int petitionID) {
		int userID = getUserID(petitionID);
		int serverID = petitionLoc[petitionID];
		int ret[] = {servers.tranmissionTime(userID,serverID),serverID};
		return ret;
	}
	
	public boolean fileInServer(int fileID, int serverID) {
	    return servers.fileLocations(fileID).contains(serverID);
	}


    //Mejorar despues
	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object o) {
		ServersState aState = (ServersState) o;
		boolean retVal = true;
		for (int i = 0; retVal && i < petitionLoc.length; i++)
		    retVal = retVal && this.getServerID(i) == aState.getLocOf(i);
		return retVal;
	}
    
	@Override
	public String toString() {
		String retVal="";
		for (int i=0; i<petitionLoc.length; i++) {
		    retVal = retVal + "Petition " + i + ": " + this.getServerID(i);
		}
		return retVal;
	}

}
