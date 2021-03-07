package org.nunolima.taskmanager;

import org.nunolima.taskmanager.core.comparator.Comparators;
import org.nunolima.taskmanager.core.errorhandling.TaskManagerException;
import org.nunolima.taskmanager.domain.Priority;
import org.nunolima.taskmanager.domain.process.Process;
import org.nunolima.taskmanager.usecase.PriorityTaskManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PriorityTaskManagerTest {
    private static final int CAPACITY = 5;

    private PriorityTaskManager victim;

    @BeforeMethod
    public void setUp() {
        victim = new PriorityTaskManager(CAPACITY);
    }

    @Test
    public void shouldAddProcessAndRemoveOldestWithLowerPriority() throws TaskManagerException {
        Process process1 = new Process(Priority.MEDIUM);
        Process process2 = new Process(Priority.MEDIUM);
        Process process3 = new Process(Priority.LOW);
        Process process4 = new Process(Priority.MEDIUM);
        Process process5 = new Process(Priority.MEDIUM);

        victim.add(process1);
        victim.add(process2);
        victim.add(process3);
        victim.add(process4);
        victim.add(process5);

        assertEquals(victim.getProcessCount(), 5);

        Process mediumPriorityProcess = new Process(Priority.MEDIUM);
        victim.add(mediumPriorityProcess);

        assertFalse(victim.list(Comparators.ageComparator()).contains(process3));
        assertTrue(victim.list(Comparators.ageComparator()).contains(mediumPriorityProcess));

        Process highPriorityProcess = new Process(Priority.HIGH);
        victim.add(highPriorityProcess);

        assertFalse(victim.list(Comparators.ageComparator()).contains(process1));
        assertTrue(victim.list(Comparators.ageComparator()).contains(highPriorityProcess));

        assertThrows(() -> victim.add(new Process(Priority.LOW)));
    }
}
