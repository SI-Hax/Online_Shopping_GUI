package com.online.shopping_gui.view;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

/**
 * Created by Miguel Emmara - 18022146
 */
public class CreatAccountChoiceView extends JPanel {
    private JButton createCustomerAccount, createAdminAccount, backBtn;

    public CreatAccountChoiceView() {
        FlatLightLaf.install();
        this.setLayout(null);

        createCustomerAccount = new JButton("Create Customer Account");
        createAdminAccount = new JButton("Create Admin Account");
        backBtn = new JButton("Back");

        add(createCustomerAccount);
        add(createAdminAccount);
        add(backBtn);

        createCustomerAccount.setBounds(100, 165, 200, 50);
        createAdminAccount.setBounds(100, 250, 200, 50);
        backBtn.setBounds(280, 500, 100, 25);
    }

    public JButton getCreateCustomerAccount() {
        return createCustomerAccount;
    }

    public JButton getCreateAdminAccount() {
        return createAdminAccount;
    }

    public JButton getBackBtn() {
        return backBtn;
    }
}
