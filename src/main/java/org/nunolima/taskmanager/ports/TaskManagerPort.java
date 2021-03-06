package org.nunolima.taskmanager.ports;

import org.nunolima.taskmanager.domain.Priority;

import java.util.List;

public interface TaskManagerPort {
    void add(Process process);
    List<Process> list();
    void kill(String pid);
    void killGroup(Priority priority);
    void killAll();
}
