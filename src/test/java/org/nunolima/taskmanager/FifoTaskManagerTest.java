package org.nunolima.taskmanager;

import org.nunolima.taskmanager.core.comparator.Comparators;
import org.nunolima.taskmanager.domain.Priority;
import org.nunolima.taskmanager.domain.process.Process;
import org.nunolima.taskmanager.usecase.FifoTaskManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class FifoTaskManagerTest {
    private final static int CAPACITY = 3;

    private FifoTaskManager victim;

    @BeforeMethod
    public void setUp() {
        victim = new FifoTaskManager(CAPACITY);
    }

    @Test
    public void shouldAddAndReplaceOldestProcess() {
        Process process1 = new Process(Priority.MEDIUM);
        Process process2 = new Process(Priority.MEDIUM);
        Process process3 = new Process(Priority.MEDIUM);

        victim.add(process1);
        victim.add(process2);
        victim.add(process3);

        assertEquals(victim.getProcessCount(), 3);

        Process newProcess = new Process(Priority.MEDIUM);
        victim.add(newProcess);

        List<Process> processes = victim.list(Comparators.ageComparator());

        assertFalse(processes.contains(process1));
        assertTrue(processes.contains(process2));
        assertTrue(processes.contains(process3));
        assertTrue(processes.contains(newProcess));
    }
}
