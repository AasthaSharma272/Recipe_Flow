package view;

import data_access.CommonRecipeDataAccessObject;
import entity.CommonRecipe;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;

public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;

    private final ViewManagerModel viewManagerModel;

    private final ProfileViewModel profileViewModel;

    private final SearchViewModel searchViewModel;

    JLabel username;

    final JButton logOut;
    final JButton search;
    final JButton account;
    JButton recipeImage;
    public LoggedInView(LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel, ProfileViewModel profileViewModel, SearchViewModel searchViewModel) throws Exception {
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
        this.profileViewModel = profileViewModel;
        this.searchViewModel = searchViewModel;

        this.loggedInViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Recipe Flow");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        //String jsonFile = "/Users/duahussain/IdeaProjects/Group99_Project207/response_output.csv";

        //String jsonFile = "/Users/farahmikati/IdeaProjects/Group99_Project207/response_output.json";

        String jsonFile = "/Users/sedakchuckal/IdeaProjects/Group99_Project207/response_output.json";

        //String jsonFile = "/Users/duahussain/IdeaProjects/Group99_Project207/response_output.csv";
        //String jsonFile = "/Users/farahmikati/IdeaProjects/Group99_Project207/response_output.json";

        String file = CommonRecipeDataAccessObject.readFileAsString(jsonFile);
        CommonRecipeDataAccessObject commonRecipeDAO = new CommonRecipeDataAccessObject(file); // replace jsonFile with the actual JSON file content or path

        // Call returnRecipeList method
        List<CommonRecipe> recipesList = commonRecipeDAO.returnRecipeList();



        //JLabel usernameInfo = new JLabel("Currently Logged in: ");
        //username = new JLabel();

        //JPanel buttons = new JPanel();
        JPanel searchButton = new JPanel();
        JPanel accountButton = new JPanel();
        JPanel recipes = new JPanel();
        JScrollPane scroll = new JScrollPane(recipes);
        //JPanel divider = new JPanel();
        logOut = new JButton(loggedInViewModel.LOGOUT_BUTTON_LABEL);
        search = new JButton(loggedInViewModel.SEARCH_BUTTON_LABEL);
        account = new JButton(loggedInViewModel.ACCOUNT_BUTTON_LABEL);
        //recipesList.get(0).getImage()
        //ImageIcon saveRecipeImage = new ImageIcon(recipesList.get(0).getImage());
        recipeImage = new JButton(recipesList.get(0).getRecipeName());



        //Panel topRightButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        //searchButton.setLayout(null);
        //accountButton.setLayout(null);
        recipes.setLayout(new GridLayout(5,4,5,5));



        //buttons.add(logOut);
        searchButton.add(search);
        searchButton.add(account);
        //divider.add(logOut);
        //recipes.add(recipeImage);

        for (CommonRecipe recipe : recipesList){
            ImageIcon saveRecipeImage = new ImageIcon(recipe.getImage());
            recipeImage = new JButton(recipe.getRecipeName(), saveRecipeImage);
            //setting position of label of recipe
            recipeImage.setVerticalTextPosition(SwingConstants.TOP);
            recipeImage.setHorizontalTextPosition(SwingConstants.CENTER);
            recipeImage.addActionListener(
                    // This creates an anonymous subclass of ActionListener and instantiates it.
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                                try {
                                    URI uri = new URI(recipe.getRecipeUrl());
                                    Desktop.getDesktop().browse(uri);
                                } catch (IOException | URISyntaxException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                System.out.println("Opening a link is not supported on this platform.");
                            }
                        }
                    }
            );
            recipes.add(recipeImage);
        }
        
        //accountButton.add(account);
        accountButton.setLocation(0,0);
        //buttons.add(account);
        //search.setBounds(500, 10, 100, 30);
        //account.setBounds(10, 10, 100, 30);
        //account.setBounds(10, -20, 100, 30);

        logOut.addActionListener(this);
        search.addActionListener(
                new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    if (evt.getSource().equals(search)) {
                        SearchState currentLoginState = searchViewModel.getState();
                        System.out.println(currentLoginState);
                        searchViewModel.setState(currentLoginState);
                        System.out.println(searchViewModel.getState());
                        searchViewModel.firePropertyChanged();
                        viewManagerModel.setActiveView(searchViewModel
                            .getViewName());
                        System.out.println(viewManagerModel.getActiveView());
                        viewManagerModel.firePropertyChanged();

                }
            }
        });
        account.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(account)) {
                            ProfileState currentLoginState = profileViewModel.getState();
                            System.out.println(currentLoginState);
                            profileViewModel.setState(currentLoginState);
                            System.out.println(profileViewModel.getState());
                            profileViewModel.firePropertyChanged();
                            viewManagerModel.setActiveView(profileViewModel
                                    .getViewName());
                            System.out.println(viewManagerModel.getActiveView());
                            viewManagerModel.firePropertyChanged();

                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        //this.add(usernameInfo);
        //this.add(username);
        //this.add(buttons);
        this.add(searchButton);
        //this.add(accountButton);
        //this.add(divider);
        //this.add(recipes);
        this.add(scroll);


        searchButton.setLocation(0,0);
        //accountButton.setLocation(200,200);
    }
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoggedInState state = (LoggedInState) evt.getNewValue();
        //username.setText(state.getUsername());
    }
}
