package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeView extends BaseWindow{

    private JMenuBar homeMenu;
    private JMenu customerMenu;
    private JMenu flightScheduleMenu;
    private JMenu ticketMenu;
    private JMenuItem scheduleListing;

    public HomeView(){
        initializeHomeViewComponent();
        this.setTitle("Home View");
        this.baseWindow.setVisible(true);
    }

    public void initializeHomeViewComponent(){
        this.homeMenu = new JMenuBar();
        this.customerMenu = new JMenu("Customer");
        this.flightScheduleMenu = new JMenu("Flight Schedule");
        this.scheduleListing = new JMenuItem("Schedule Listing");

        this.ticketMenu = new JMenu("Ticket");

        this.homeMenu.add(this.customerMenu);
        this.homeMenu.add(this.flightScheduleMenu);

        this.flightScheduleMenu.add(this.scheduleListing);
        this.homeMenu.add(ticketMenu);
        this.addActionScheduleListingMenu();
        this.baseWindow.setJMenuBar(this.homeMenu);
    }


    public void addActionScheduleListingMenu(){
        this.scheduleListing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScheduleListingPage scheduleListingPage = new ScheduleListingPage();
            }
        });
    }
}
