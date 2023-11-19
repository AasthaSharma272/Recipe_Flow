package interface_adapter.profile_page;
import interface_adapter.ViewModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.profile_page.ProfileState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ProfileViewModel extends ViewModel{
    private static final LoggedInState loggedin_state = new LoggedInState();
    public static final String SAVED_BUTTON_LABEL = "Saved Recipes";
    public static final String UPLOAD_BUTTON_LABEL = "Personal Recipes";
    public static final String UPLOAD_NEW_BUTTON_LABEL = "Upload New Recipe";

    public static final String TITLE_LABEL = loggedin_state.getUsername();

    private ProfileState state = new ProfileState();

    public ProfileViewModel(){super(loggedin_state.getUsername());}

    public void setState(ProfileState state) {this.state = state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged(){support.firePropertyChange("state", null, this.state);}

    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }

    public ProfileState getState(){return state;}
}


