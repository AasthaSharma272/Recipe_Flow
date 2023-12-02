package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.saved.SavedState;
import interface_adapter.saved.SavedViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SavedView extends JPanel implements ActionListener,PropertyChangeListener {
    public final String viewName = "Saved Recipes";

    private final ProfileViewModel profileViewModel;
    private final ViewManagerModel viewManagerModel;

    private final SavedViewModel savedViewModel;

    final JButton back;

    public SavedView(SavedViewModel savedViewModel, ViewManagerModel viewManagerModel, ProfileViewModel profileViewModel) {
        this.profileViewModel = profileViewModel;
        this.savedViewModel = savedViewModel;
        this.viewManagerModel = viewManagerModel;

        JLabel title = new JLabel(savedViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        back = new JButton(savedViewModel.BACK_BUTTON_LABEL);
        buttons.add(back);

        back.addActionListener(this);

      this.add(title);
      this.add(buttons);

      back.addActionListener(
              // takes back to profile page
              new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      if (e.getSource().equals(back)){
                          ProfileState currentState = profileViewModel.getState();
                          profileViewModel.setState(currentState);
                          profileViewModel.firePropertyChanged();
                          viewManagerModel.setActiveView(profileViewModel.getViewName());
                          viewManagerModel.firePropertyChanged();
                      }
                  }
              }
      );

      //TODO: show saved recipes here (in same presentation as results)
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: implement

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //TODO: implement
    }
}