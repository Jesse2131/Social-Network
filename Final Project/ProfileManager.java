import java.awt.image.*;
import java.util.*;
public class ProfileManager{

    private List<Profile> profileList[] = new LinkedList[10];

    int location,size; 
	public ProfileManager(){

		for(int i=0; i<profileList.length; i++){
			profileList[i]=new LinkedList<>();
		}
		size = 0;
		location = 0; 
	}

	public void addProfile(Profile p){
		profileList[location].add(p);
		size++;
		location++;
	}

	public void addFriend(Profile a, Profile b){
		//Check to see if they are already friends
		if(a.getFriendList().contains(b)){
			//System.out.println("Already Friends");
			return;
		}
		else{
			//Connects profile a to profile b and vise versa. 
			a.addFriend(b);
			b.addFriend(a);
		}

	}

	public void removeFriend(Profile a, Profile b){
		//Makes sure they are friends before removing. 
		if(a.getFriendList().contains(b)){
			a.removeFriend(b);
			b.removeFriend(a);
		}
	}

	public Profile getAllProfiles(){
		for(int i=0; i<profileList.length; i++){
			if(!profileList[i].isEmpty()){
				for(int j=0; j<profileList[i].size(); j++){
					return profileList[i].get(j);
				}
			}
		}	
		return null;
	}

	public Profile getProfile(int i){
		for(int j=0; j<profileList[i].size(); j++){
			return profileList[i].get(j);
		}
		return null;
	}

	public int getSize(){
		return size; 
	}

	public String displayAll(){ //Displays Both the profile as well as its friends. 
		String text = "";

		for(int i=0; i<profileList.length; i++){
			text += "Profile==>"+profileList[i].toString();
			for(int j=0; j<profileList[i].size(); j++){
				text+= "Friends: " + profileList[i].get(j).getFriendList();
			}
			text += "\n";
		}
		System.out.println(text);
		return text;

	}

}