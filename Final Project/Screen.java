//Swing
import javax.swing.*;
//Awt
import java.awt.*;
import java.awt.image.BufferedImage;
//Swing
import javax.swing.*;
import javax.swing.text.*;
//Awt Event
import java.awt.event.*;
//Util
import java.util.*;	
//Io
import java.io.*;
import javax.imageio.ImageIO;

 
public class Screen extends JPanel implements ActionListener{
	ProfileManager pm;

    private int screen; 
    private BufferedImage profilePic1,profilePic2,profilePic3,profilePic4,profilePic5,profilePic6;
    private Profile yourProfile,john,jenny,alex,alexa,thomas;
    private String profilePicture;
    //Homepage Elements 
    private JTextField nameInput0,statusInput0; 
    private JButton joinNetworkButton; 
    private JButton profile1, profile2, profile3, profile4, profile5, profile6;
    //Network page Elements 
    private JButton profileDetailsButton, leaveNetworkButton, searchProfileButton, createProfileButton, viewProfilesButton; 
    private JTextField nameInput1, statusInput1, searchField;
    //private String displayText; 
    private JTextPane otherProfileDisplay; 
    private JScrollPane scrollBar;
	private SimpleAttributeSet attributeSet;
	private StyledDocument doc;
	//Profile Detail Elements
	private JButton backButton, changeButton, changeProfileButton, yourFriendsButton;
    private JTextField editNameField, editStatusField; 
	private JTextPane friendProfileDisplay;
	private JScrollPane scrollBar1;
	private StyledDocument doc1;
    private String profileFriendsText;
    //Profile Pictures 
    public Screen(){
        profilePicture = "";
        //Instantiate all Profile Pictures 
        try {
            profilePic1 = ImageIO.read(new File("Images/profile1.png"));
            profilePic2 = ImageIO.read(new File("Images/profile2.png"));
            profilePic3 = ImageIO.read(new File("Images/profile3.png"));
            profilePic4 = ImageIO.read(new File("Images/profile4.png"));
            profilePic5 = ImageIO.read(new File("Images/profile5.png"));
            profilePic6 = ImageIO.read(new File("Images/profile6.png"));
        } catch (IOException ie) {
            ie.printStackTrace();
        }

        pm = new ProfileManager(); //Create profile manager and then add a few default profiles. 
        john = new Profile("John Doe", "Single", "profilePic2");
        jenny = new Profile("Jenny Doe", "Single", "profilePic1");
        alex = new Profile("Alex Doe", "Married", "profilePic3");
        alexa = new Profile("Alexa Doe", "Married", "profilePic6");
        thomas = new Profile("Thomas Doe","Single", "profilePic4");
        pm.addProfile(john);
        pm.addProfile(jenny);
        pm.addProfile(alex);
        pm.addProfile(alexa);
        pm.addProfile(thomas);
        pm.addFriend(john,thomas); //Create a few default friendships
        pm.addFriend(alexa,john);
        pm.addFriend(jenny,alex);

        screen = 0; //Starts at Homepage. 
    	
   			
   		//Homepage Components Screen 0 --------------------------------------------------------------
   		nameInput0 = new JTextField();
   		nameInput0.setBounds(565,97,115,30);
        this.add(nameInput0);

        statusInput0 = new JTextField();
        statusInput0.setBounds(565,137,115,30);
        this.add(statusInput0);

   		joinNetworkButton = new JButton("Join Network");
   		joinNetworkButton.setBounds(585,600, 120, 30);
   		joinNetworkButton.addActionListener(this);
        this.add(joinNetworkButton);
        joinNetworkButton.setEnabled(false);

        //Generate Profile options
        profile1 = new JButton(new ImageIcon("Images/profile1.png"));
        profile1.setBounds(450, 220,100,100);
        profile1.addActionListener(this);
        add(profile1);

        profile2 = new JButton(new ImageIcon("Images/profile2.png"));
        profile2.setBounds(580, 220, 100, 100);
        profile2.addActionListener(this);
        add(profile2);

        profile3 = new JButton(new ImageIcon("Images/profile3.png"));
        profile3.setBounds(710, 220, 100,100);
        profile3.addActionListener(this);
        add(profile3);

        profile4 = new JButton(new ImageIcon("Images/profile4.png"));
        profile4.setBounds(450, 350, 100, 100);
        profile4.addActionListener(this);
        add(profile4);

        profile5 = new JButton(new ImageIcon("Images/profile5.png"));
        profile5.setBounds(580, 350, 100,100);
        profile5.addActionListener(this);
        add(profile5);

        profile6 = new JButton(new ImageIcon("Images/profile6.png"));
        profile6.setBounds(710, 350, 100,100);
        profile6.addActionListener(this);
        add(profile6);

        //Network Screen Options Screen 1 --------------------------------------------------------------
        profileDetailsButton = new JButton("Profile Details");
        profileDetailsButton.setBounds(405, 400, 120,30);
        profileDetailsButton.addActionListener(this);

        leaveNetworkButton = new JButton("Leave Network");
        leaveNetworkButton.setBounds(405,450,120,30);
        leaveNetworkButton.addActionListener(this);

        searchProfileButton = new JButton("Search");
        searchProfileButton.setBounds(900,97,100,29);
        searchProfileButton.addActionListener(this);

        searchField = new JTextField("Enter Name");
        searchField.setBounds(700,97,200,30);

        createProfileButton = new JButton("Add Profile");
        createProfileButton.setBounds(790,570,120,30);
        createProfileButton.addActionListener(this);

        viewProfilesButton = new JButton("View Profiles");
        viewProfilesButton.setBounds(790,610,120,30);
        viewProfilesButton.addActionListener(this);

        //Other profile display area 
        attributeSet = new SimpleAttributeSet();  
        //StyleConstants.setBold(attributeSet,true);

        //TextPane to display other profiles 
        otherProfileDisplay = new JTextPane();
        //otherProfileDisplay.setCharacterAttributes(attributeSet,true);
        otherProfileDisplay.setEditable(false);
        otherProfileDisplay.setFont(new Font("Arial", Font.BOLD, 15));
        //Add elements to TextPane
        doc = otherProfileDisplay.getStyledDocument();
        for(int i=0; i<pm.getSize(); i++){
        	otherProfileDisplay.insertIcon(new ImageIcon(pm.getProfile(i).getProfilePicture())); //Add profile pictures 
        	try {
				doc.insertString(1,"\nName: " + pm.getProfile(i).getName() + " Status: " + pm.getProfile(i).getStatus() + "\n\n",attributeSet); //Add profile Info
				otherProfileDisplay.insertComponent(pm.getProfile(i).getAddButton()); //Add friend button
				pm.getProfile(i).getAddButton().addActionListener(this);
        	} catch (BadLocationException ex) {
        	    ex.printStackTrace();
        	}
			
        }
        //Turns JTextPane to scrollpane. 
        scrollBar = new JScrollPane(otherProfileDisplay);
        scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollBar.setBounds(700,150,300,400);


        //Profile detail section Screen 2 --------------------------------------------------------------\
        profileFriendsText = "Your";
        backButton = new JButton("Back");
        backButton.setBounds(590,600,100,30);
        backButton.addActionListener(this);
        //Text Fields to Edit name and Status
        editNameField = new JTextField("New Name");
        editNameField.setBounds(329,400,200,30);
        editStatusField = new JTextField("New Status");
        editStatusField.setBounds(329,440,200,30);
        //Button for Editing profile
        changeButton = new JButton("Apply");
        changeButton.setBounds(329,490,100,30);
        changeButton.addActionListener(this);
        //Button for Changing Profile Picture
        changeProfileButton = new JButton("Change Profile Picture");
        changeProfileButton.setBounds(329,540,170,30);
        changeProfileButton.addActionListener(this);
        //Button to view your friends
        yourFriendsButton = new JButton("Your Friends");
        yourFriendsButton.setBounds(790,570,120,30);
        yourFriendsButton.addActionListener(this);
        //Display Friend List 
     	friendProfileDisplay = new JTextPane();
        friendProfileDisplay.setEditable(false);
        friendProfileDisplay.setFont(new Font("Arial", Font.BOLD, 15));
    	doc1 = friendProfileDisplay.getStyledDocument();
        //Turns JTextPane to scrollpane. 
        scrollBar1 = new JScrollPane(friendProfileDisplay);
        scrollBar1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollBar1.setBounds(700,150,300,400);

        setFocusable(true);
        setLayout(null);  
    }
          
    public Dimension getPreferredSize(){
        return new Dimension(1300,700);
    }
    
    public void addFriendToPanel(Profile profile){ //Adds friends to JTextPane 
    	friendProfileDisplay.setText("");
        //What profiles friendlist to display. 
    
        //Add Friend to TextPane 
        for(int i=0; i<profile.getFriendList().size(); i++){
            friendProfileDisplay.insertIcon(new ImageIcon(profile.getFriendList().get(i).getProfilePicture()));
            try {
                if(profile == yourProfile){
                    friendProfileDisplay.insertComponent(profile.getFriendList().get(i).getViewButton()); //View Friend Button
                }
                doc1.insertString(doc1.getLength(),"\nName: " + profile.getFriendList().get(i).getName() + " Status: " + profile.getFriendList().get(i).getStatus() + "\n\n",attributeSet); //Add profile Info
                profile.getFriendList().get(i).getViewButton().addActionListener(this);
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }
        }   
    }

    public void displayOtherProfiles(Profile p){
        otherProfileDisplay.setText("");
        otherProfileDisplay.setCaretPosition(0); 

        otherProfileDisplay.insertIcon(new ImageIcon(p.getProfilePicture())); //Add profile pictures 
        try {
            otherProfileDisplay.setCaretPosition(doc.getLength());

            doc.insertString(1,"\nName: " + p.getName() + " Status: " + p.getStatus() + "\n\n",attributeSet); //Add profile Info

            otherProfileDisplay.setCaretPosition(0);

            otherProfileDisplay.insertComponent(p.getAddButton()); //Add friend button
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g); 
        Font font = new Font("Arial", Font.BOLD, 25);
        g.setFont(font);
        if(screen == 0){ //Screen 0 is the home screen. This is where the user can join the network. 
        	g.drawString("Create Profile to Join Network", 450, 50);
        	g.drawString("Enter Name:", 400, 120);
        	g.drawString("Enter Status:",400,160);
        	g.drawString("Select Profile Picture", 500, 200);
        }
        else if(screen == 1){ //Screen 1 is the network screen. This is where the user can view other profiles and a summary of his/her own. 
        	g.drawString("Your Profile", 400,120);
        	if(yourProfile != null){
        		yourProfile.drawMe(405, 150, g);
        	}
        }
        else if(screen == 2){//Screen 2 is the profile details screen
        	g.drawString("Your Profile",400,120);
            g.drawString(profileFriendsText + " Friends",775,120);
        	if(yourProfile != null){
        		yourProfile.drawMe(405,150,g);
        	}
        }
        else if(screen == 3){//Screen 3 is the add profile screen
            g.drawString("Add Another Profile", 500, 50);
            g.drawString("Enter Name:", 400, 120);
            g.drawString("Enter Status:",400,160);
            g.drawString("Select Profile Picture", 500, 200);
        }
        else if(screen == 4){//Screen 4 is the change profile picture screen
            g.drawString("Select New Profile Picture", 490,50);
        }
    }    
	
	private void moveScreen(int s){ //Transition between screens 

		if(s == 0){ //Homescreen 
			removeAll(); //If moving from different screen remove all elements from last screen. Then re-add screen 0 elements 
			screen = 0; //For paintComponent 
			this.add(nameInput0);
			this.add(statusInput0);
			this.add(joinNetworkButton);
        	joinNetworkButton.setEnabled(false);
        	add(profile1);
			add(profile2);
			add(profile3);
			add(profile4);
			add(profile5);
			add(profile6);
		}
		else if(s == 1){ //Network screen 
			removeAll(); //If moving from different screen remove all elements from last screen. Then re-add screen 1 elements 
			screen = 1; //For paintComponent 
			add(profileDetailsButton);
			add(leaveNetworkButton);	
			add(searchProfileButton);
            add(createProfileButton);
            add(viewProfilesButton);
			add(searchField);
			add(scrollBar);
			scrollBar.validate();
		}
		else if(s == 2){//Profile Details screen. This is where the user edits his profile and views friends 
			removeAll();
			screen = 2;
			add(backButton);
            add(editStatusField);
            add(editNameField);
            add(changeButton);
            add(changeProfileButton);
            add(yourFriendsButton);
			add(scrollBar1);
			scrollBar1.validate();
		}
        else if(s == 3){ //Adding another profile screen 
            removeAll();
            screen = 3;
            this.add(nameInput0);
            this.add(statusInput0);
            this.add(joinNetworkButton);
            joinNetworkButton.setEnabled(false);
            add(profile1);
            add(profile2);
            add(profile3);
            add(profile4);
            add(profile5);
            add(profile6);

        }
        else if(s == 4){ //Screen to select new profile picture 
            removeAll();
            screen = 4;
            add(profile1);
            add(profile2);
            add(profile3);
            add(profile4);
            add(profile5);
            add(profile6);
        }
	}
    public void actionPerformed(ActionEvent e){
    	String newName = nameInput0.getText();
    	String newStatus = statusInput0.getText();
    	if(e.getSource() == joinNetworkButton && !newName.equals("") && !newStatus.equals("")){ //Check to make sure that all the information is selected.
            if(screen == 0){ //Add user profile
                yourProfile = new Profile(newName, newStatus, profilePicture); //Create a new profile with name and profile. 
                nameInput0.setText(null); //Reset textfields
                statusInput0.setText(null);
                //pm.addProfile(yourProfile);
                this.moveScreen(1);
            }
            else if(screen == 3){ //Add another profile
                Profile newProfile = new Profile(newName, newStatus, profilePicture);
                pm.addProfile(newProfile);

                otherProfileDisplay.setCaretPosition(0); 

                otherProfileDisplay.insertIcon(new ImageIcon(newProfile.getProfilePicture())); //Add profile pictures 
                try {
                    otherProfileDisplay.setCaretPosition(doc.getLength());

                    doc.insertString(1,"\nName: " + newProfile.getName() + " Status: " + newProfile.getStatus() + "\n\n",attributeSet); //Add profile Info

                    otherProfileDisplay.setCaretPosition(0);

                    otherProfileDisplay.insertComponent(newProfile.getAddButton()); //Add friend button
                    newProfile.getAddButton().addActionListener(this);
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }

                nameInput0.setText(null); //Reset textfields
                statusInput0.setText(null);
                this.moveScreen(1);
            }
    	}
        //Profiles are the profilepicture that the user selects. Screen 5 is the area for users to change their profile picture. 
    	else if(e.getSource() == profile1){ 
            if(screen == 4){
                yourProfile.setProfilePicture(profilePic1);
                this.moveScreen(2);
            }
            else{
                profilePicture = "profilePic1";
                joinNetworkButton.setEnabled(true);
            }
    	}
    	else if(e.getSource() == profile2){
            if(screen == 4){
                yourProfile.setProfilePicture(profilePic2);
                this.moveScreen(2);
            }
            else{
                profilePicture = "profilePic2";
                joinNetworkButton.setEnabled(true);
            }
    	}
    	else if(e.getSource() == profile3){
            if(screen == 4){
                yourProfile.setProfilePicture(profilePic3);
                this.moveScreen(2);
            }
            else{
                profilePicture = "profilePic3";
                joinNetworkButton.setEnabled(true);
            }
    	}
    	else if(e.getSource() == profile4){
            if(screen == 4){
                yourProfile.setProfilePicture(profilePic4);
                this.moveScreen(2);
            }
            else{
                profilePicture = "profilePic4";
                joinNetworkButton.setEnabled(true);
            }
    	}
    	else if(e.getSource() == profile5){
            if(screen == 4){
                yourProfile.setProfilePicture(profilePic5);
                this.moveScreen(2);
            }
    		else{
                profilePicture = "profilePic5";
                joinNetworkButton.setEnabled(true);
            }
    	}
    	else if(e.getSource() == profile6){
            if(screen == 4){
                yourProfile.setProfilePicture(profilePic6);
                this.moveScreen(2);
            }
            else{
                profilePicture = "profilePic6";
                joinNetworkButton.setEnabled(true);
            }
    	}
        //Leave the network
        else if(e.getSource() == leaveNetworkButton){
            for(int i=0; i<pm.getSize(); i++){
                if(pm.getProfile(i).getFriendList().contains(yourProfile)){ //If other profiles has user as a friend delete user from list. 
                    pm.getProfile(i).removeFriend(yourProfile);
                    pm.getProfile(i).getAddButton().setEnabled(true); //Re-enable add friend button for next user
                }
                if(yourProfile.getFriendList().contains(pm.getProfile(i))){ //Removes all friends from users profile 
                    yourProfile.removeFriend(pm.getProfile(i));
                }
            }
            friendProfileDisplay.setText(""); //Reset friend list display
            yourProfile = null; //Removes user
            this.moveScreen(0);
        }
        //View profile details page 
    	else if(e.getSource() == profileDetailsButton){
    		this.moveScreen(2);
    	}
        //Change Profile Picture Button
        else if(e.getSource() == changeProfileButton){
            this.moveScreen(4);
        }
        //Button for changing name and status 
        else if(e.getSource() == changeButton){
            if(!editNameField.getText().equals("New Name") && !editNameField.getText().equals("")){//Check to see if user put in a name 
                yourProfile.setName(editNameField.getText());
            }
            if(!editStatusField.getText().equals("New Status") && !editStatusField.getText().equals("")){//Check to see if user put in a status
                yourProfile.setStatus(editStatusField.getText());
            }
        }
        //Back button 
    	else if(e.getSource() == backButton){
    		if(screen == 2){ //If in profile detail page, go back to network overview page. 
    			this.moveScreen(1);
                editNameField.setText("New Name"); //Reset TextFields
                editStatusField.setText("New Status");
    		}
    	}
        //Add a new profile 
        else if(e.getSource() == createProfileButton){
            this.moveScreen(3);
        }
        //View user friends 
        else if(e.getSource() == yourFriendsButton){
            profileFriendsText = "Your";
            this.addFriendToPanel(yourProfile);
        }
        //Search For a profile 
        else if(e.getSource() == searchProfileButton){
            String searchedProfile = searchField.getText();
            if(!searchedProfile.equals("") && !searchedProfile.equals("Enter Name")){ //Make sure that the search field has something inputted. 
                for(int i=0; i<pm.getSize(); i++){
                    if(pm.getProfile(i).getName().equals(searchedProfile)){ //If the searched profile exists, display it in the panel
                        this.displayOtherProfiles(pm.getProfile(i));
                    }
                }
            }
        }
        //View all profiles again. This button is used if the user searched for a profile and wants to see all the profiles again. 
        else if(e.getSource() == viewProfilesButton){
            otherProfileDisplay.setText("");
            otherProfileDisplay.moveCaretPosition(0); 
            for(int i=0; i<pm.getSize(); i++){
                otherProfileDisplay.insertIcon(new ImageIcon(pm.getProfile(i).getProfilePicture())); //Add profile pictures 
                try {
                    otherProfileDisplay.setCaretPosition(0);
                    doc.insertString(1,"\nName: " + pm.getProfile(i).getName() + " Status: " + pm.getProfile(i).getStatus() + "\n\n",attributeSet); //Add profile Info
                    otherProfileDisplay.insertComponent(pm.getProfile(i).getAddButton()); //Add friend button
                    otherProfileDisplay.setCaretPosition(0);

                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        }
    	for(int i=0; i<pm.getSize(); i++){ //Buttons For add Friend 
    		if(e.getSource() == pm.getProfile(i).getAddButton()){ //Figure out which profiles add friend button was clicked. 
    			pm.addFriend(yourProfile,pm.getProfile(i)); //Adds friends with user profile and clicked profile friends button. 
    			pm.getProfile(i).getAddButton().setEnabled(false); //Disables button once friend is made. 
    			this.addFriendToPanel(yourProfile);
    		}
    	}
        //Button for viewing friends friends
    	if(screen == 2){ 
    		for(int j=0; j<yourProfile.getFriendList().size(); j++){
    			if(e.getSource() == yourProfile.getFriendList().get(j).getViewButton()){
    				//System.out.println(yourProfile.getFriendList().get(j));
                    profileFriendsText = yourProfile.getFriendList().get(j).getName() + "s";
                    this.addFriendToPanel(yourProfile.getFriendList().get(j));
    			}
    		}
    	}
        repaint();
    }     
}



