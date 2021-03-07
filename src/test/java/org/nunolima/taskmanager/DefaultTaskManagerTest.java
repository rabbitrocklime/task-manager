package org.nunolima.taskmanager;

import org.nunolima.taskmanager.core.errorhandling.NoMoreResourcesException;
import org.nunolima.taskmanager.core.errorhandling.ProcessNotFoundException;
import org.nunolima.taskmanager.core.errorhandling.TaskManagerException;
import org.nunolima.taskmanager.domain.Priority;
import org.nunolima.taskmanager.domain.process.Process;
import org.nunolima.taskmanager.usecase.DefaultTaskManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class DefaultTaskManagerTest {

    private final static int CAPACITY = 3;

    private DefaultTaskManager victim;

    @BeforeMethod
    public void setUp() {
        victim = new DefaultTaskManager(CAPACITY);
    }

    @Test
    public void shouldAddProcessesUntilCapacity() throws TaskManagerException {
        victim.add(new Process(Priority.LOW));
        victim.add(new Process(Priority.MEDIUM));
        victim.add(new Process(Priority.HIGH));

        assertEquals(victim.getProcessCount(), 3);
        assertThrows(() -> victim.add(new Process(Priority.LOW)));
    }

    @Test
    public void shouldKillProcess() throws TaskManagerException {
        Process process = new Process(Priority.LOW);

        victim.add(process);
        assertEquals(victim.getProcessCount(), 1);

        victim.kill(process.getPid());
        assertEquals(victim.getProcessCount(), 0);
    }

    @Test
    public void shouldKillProcessGroup() throws TaskManagerException {
        victim.add(new Process(Priority.LOW));
        victim.add(new Process(Priority.MEDIUM));
        victim.add(new Process(Priority.MEDIUM));

        assertEquals(victim.getProcessCount(), 3);

        victim.killGroup(Priority.MEDIUM);
        assertEquals(victim.getProcessCount(), 1);
    }

    @Test
    public void shouldKillAll() throws TaskManagerException {
        victim.add(new Process(Priority.LOW));
        victim.add(new Process(Priority.MEDIUM));
        victim.add(new Process(Priority.HIGH));

        assertEquals(victim.getProcessCount(), 3);

        victim.killAll();
        assertEquals(victim.getProcessCount(), 0);
    }
}
