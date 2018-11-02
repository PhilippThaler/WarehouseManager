package com.philippthaler.app.ui;

import com.philippthaler.app.ui.commands.View;

import java.util.Scanner;

public class UserInterface implements View {

  public UserInterface() {

  }

  @Override
  public void start() {
    System.out.println("------------------------");
    System.out.println("Lagerverwaltungssoftware");
    System.out.println("------------------------");

    System.out.println("\n\nWas wollen Sie tun?");

  }

  @Override
  public void input() {
    System.out.println("input");
  }

  @Override
  public void showHelp() {
    System.out.println("Help");
  }

  public void showAll() {
    System.out.println("Show All");
  }


}
