/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmaster.ui;

import java.util.Scanner;

/**
 *
 * @author Eddie
 */
public class UserIOImpl implements UserIO{
    
    Scanner input = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    //#6
    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        return Double.parseDouble(input.nextLine());
    }

    //#7
    @Override
    public double readDouble(String prompt, double min, double max) {
        double ui = 0;
        boolean inRange = false;
        while (inRange == false) {
            System.out.println(prompt + min + max);
            ui = Double.parseDouble(input.nextLine());
            if (ui <= min && ui >= max) {
                inRange = true;
            }
        }
        return ui;
    }

    //#4
    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        return Float.parseFloat(input.nextLine());
    }

    //#5
    @Override
    public float readFloat(String prompt, float min, float max) {
        float ui = 0;
        boolean inRange = false;
        while (inRange == false) {
            System.out.println(prompt + min + max);
            ui = Float.parseFloat(input.nextLine());
            if (ui <= min && ui >= max) {
                inRange = true;
            }
        }
        return ui;
    }

    //#1
    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        return Integer.parseInt(input.nextLine());
    }

    //#2
    @Override
    public int readInt(String prompt, int min, int max) {
        int ui = 0;
        boolean inRange = false;
        try {
            while (inRange == false) {
                System.out.println(prompt);
                ui = Integer.parseInt(input.nextLine());
                if (ui >= min && ui <= max) {
                    inRange = true;
                }
            }
        } catch (NumberFormatException e) {
            return 0;
        }

        return ui;
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        return Long.parseLong(input.nextLine());
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long ui = 0;
        boolean inRange = false;
        while (inRange == false) {
            System.out.println(prompt + min + max);
            ui = Long.parseLong(input.nextLine());
            if (ui >= min && ui <= max) {
                inRange = true;
            }
        }
        return ui;
    }

    //#3
    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return input.nextLine();
    }
    
}
