package test.view;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Miguel Emmara
 */
public class MainViewController extends JFrame implements ActionListener {

    private RootPanel rootPanel;
    private MainMenuView mainMenu;
    private CreateAccountView createAccountView;
    private ImageShopAndRun rightPanel;

    public MainViewController() throws HeadlessException {
        FlatLightLaf.install();

        this.rootPanel = new RootPanel();

        this.mainMenu = new MainMenuView();
        rootPanel.addCardToStack(mainMenu, MainMenuView.NAME);

        this.createAccountView = new CreateAccountView();
        rootPanel.addCardToStack(createAccountView, CreateAccountView.NAME);

        rightPanel = new ImageShopAndRun();
        rightPanel.setBounds(50, 200, 400, 116);
        rootPanel.addCardToStack(rightPanel, ImageShopAndRun.NAME);

        getContentPane().add(this.rootPanel);
        setSize(745,525);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        mainMenu.getCreateAccount().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rootPanel.showCardInStack(CreateAccountView.NAME);
                System.out.println("Test");
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        MainViewController mainViewController = new MainViewController();
        mainViewController.setVisible(true);
    }
}
