package jsd.project.tank90.ui;

import jsd.project.tank90.model.tanks.PlayerTank;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    public Frame() {
        setupWindow();
//        initComponents();
    }

    private void setupWindow() {
        setTitle("Tank 1990");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(800, 600)); // Adjust width to fit both panels
        setResizable(false);
        setLocationRelativeTo(null);
    }


    }


