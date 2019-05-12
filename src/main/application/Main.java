package application;

import java.util.*;

import javax.swing.*;
import layout.*;

/**
 * Main
 */
public class Main {
    public static final double version = 0.1;
    public static final String filePath = "resource/";
    public static final int freshInterval = 1000; // in ms
    public static final int defaultOverdraftLimit = 2000;

    public static LinkedList<JPanel> funcSetLogin = new LinkedList<JPanel>();
    public static LinkedList<JPanel> funcSet = new LinkedList<JPanel>();
    public static boolean restart = false;
    public static int loginStatus = 0;

    public static void setup() {

        funcSetLogin = new LinkedList<JPanel>();
        funcSet = new LinkedList<JPanel>();

        if (loginStatus == 0) {
            funcSetLogin.add(new FuncPanelLogin());
            funcSetLogin.add(new FuncPanelRegister());
            new MainLayout(funcSetLogin);
        } else {
            funcSet.add(new FuncPanelMoney());
            new MainLayout(funcSet);
        }
    }

    public static void main(String[] args) {
        setup();
    }
}