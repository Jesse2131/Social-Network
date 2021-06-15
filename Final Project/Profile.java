import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
public class Profile{

	private String name,status,profile;
	private ArrayList<Profile> friendList;
	private BufferedImage profilePicture; 
	private JButton addThisProfile,viewProfileFriends;
	public Profile(String name, String status, String profile){
		addThisProfile = new JButton("Add Friend");
		viewProfileFriends = new JButton("View Friends");
		this.friendList = new ArrayList<Profile>();
		if(profile.equals("profilePic1")){
			try {
					profilePicture = ImageIO.read(new File("Images/profile1.png"));
	        } catch (IOException ie) {}
    	}
    	else if(profile.equals("profilePic2")){
			try {
					profilePicture = ImageIO.read(new File("Images/profile2.png"));
	        } catch (IOException ie) {}
    	}
    	else if(profile.equals("profilePic3")){
			try {
					profilePicture = ImageIO.read(new File("Images/profile3.png"));
	        } catch (IOException ie) {}
    	}
    	else if(profile.equals("profilePic4")){
			try {
					profilePicture = ImageIO.read(new File("Images/profile4.png"));
	        } catch (IOException ie) {}
    	}
    	else if(profile.equals("profilePic5")){
			try {
					profilePicture = ImageIO.read(new File("Images/profile5.png"));
	        } catch (IOException ie) {}
    	}
    	else if(profile.equals("profilePic6")){
			try {
					profilePicture = ImageIO.read(new File("Images/profile6.png"));
	        } catch (IOException ie) {}
    	}
		this.name = name;
		this.status = status;
	}

	public String getName(){
		return name;
	}

	public void setName(String n){
		this.name = n;
	}

	public String getStatus(){
		return status;
	}

	public void setStatus(String s){
		this.status = s;
	}

	public BufferedImage getProfilePicture(){
		return profilePicture;
	}
	

	public void setProfilePicture(BufferedImage profilePicture){
		this.profilePicture = profilePicture;
	}

	public void addFriend(Profile p){
		friendList.add(p);
	}

	public void removeFriend(Profile p){
		friendList.remove(p);
	}

	public JButton getAddButton(){
		return addThisProfile;
	}

	public JButton getViewButton(){
		return viewProfileFriends;
	}

	public ArrayList<Profile> getFriendList(){
		return friendList;
	}

	public String toString(){
		return name + ", " + status; 
	}

	public void drawMe(int x, int y, Graphics g){
		g.drawImage(profilePicture,x,y,null);
		g.drawString("Name: " + this.getName(), x-76, y+170);
		g.drawString("Status: " + this.getStatus(), x-76, y+ 220);
	}

}