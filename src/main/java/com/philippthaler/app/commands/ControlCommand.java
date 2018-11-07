package com.philippthaler.app.commands;

import com.philippthaler.app.utils.Warehouse;

/**
 * Command interface for the warehouse
 */
public interface ControlCommand {
  void execute(Warehouse warehouse);
}
