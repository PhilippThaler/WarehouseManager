package com.philippthaler.app.commands;

import com.philippthaler.app.utils.Warehouse;

public interface ControlCommand {
  void execute(Warehouse warehouse);
}
