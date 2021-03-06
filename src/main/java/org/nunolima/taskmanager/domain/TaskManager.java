package org.nunolima.taskmanager.domain;

import org.nunolima.taskmanager.ports.TaskManagerPort;

import java.util.List;

public class TaskManager implements TaskManagerPort {


    @Override
    public void add(java.lang.Process process) {

    }

    @Override
    public List<java.lang.Process> list() {
        return null;
    }

    @Override
    public void kill(String pid) {

    }

    @Override
    public void killGroup(Priority priority) {

    }

    @Override
    public void killAll() {

    }
}
