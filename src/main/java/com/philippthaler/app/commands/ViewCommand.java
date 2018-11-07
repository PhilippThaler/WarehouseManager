package com.philippthaler.app.commands;

import com.philippthaler.app.ui.View;

/**
 * Command interface for the View
 */
public interface ViewCommand {
  void execute(View ui);
}
