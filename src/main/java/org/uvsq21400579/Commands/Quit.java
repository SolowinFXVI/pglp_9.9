package org.uvsq21400579.Commands;

import static java.lang.System.exit;

import org.uvsq21400579.Command;

public class Quit implements Command {

  @Override
  public void execute() {
    exit(0);
  }
}
