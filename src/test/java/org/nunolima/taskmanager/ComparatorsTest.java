package org.nunolima.taskmanager;

import org.nunolima.taskmanager.core.comparator.Comparators;
import org.nunolima.taskmanager.domain.Priority;
import org.nunolima.taskmanager.domain.process.Process;
import org.nunolima.taskmanager.domain.process.RunningProcess;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ComparatorsTest {

    private List<RunningProcess> processes;

    Process process1 = new Process("66a4a7e8-15b7-4f32-a2b1-fc8c23b9544d", Priority.LOW);
    Process process2 = new Process("e7174ab8-75db-487f-83a8-d4542a7ee41e", Priority.MEDIUM);
    Process process3 = new Process("8a5045e7-6993-4f1f-83e3-e0e981891b08", Priority.HIGH);
    Process process4 = new Process("259495da-da5e-4872-b2e0-8d775566db78", Priority.MEDIUM);
    Process process5 = new Process("5d8ea9a6-b488-432e-a786-e30c7bfc3dce", Priority.LOW);

    @BeforeClass
    public void setUp() {
        processes = Lists.newArrayList(
                new RunningProcess(process1),
                new RunningProcess(process2),
                new RunningProcess(process3),
                new RunningProcess(process4),
                new RunningProcess(process5));
    }

    @Test
    public void shouldOrderByAge() {
        processes. sort(Comparators.ageComparator());

        assertTrue(processes.get(0).getCreatedAt().isBefore(processes.get(1).getCreatedAt()));
        assertTrue(processes.get(1).getCreatedAt().isBefore(processes.get(2).getCreatedAt()));
        assertTrue(processes.get(2).getCreatedAt().isBefore(processes.get(3).getCreatedAt()));
        assertTrue(processes.get(3).getCreatedAt().isBefore(processes.get(4).getCreatedAt()));
    }

    @Test
    public void shouldOrderByPid() {
        processes. sort(Comparators.pidComparator());

        assertEquals(processes.get(0).getProcess(), process4);
        assertEquals(processes.get(1).getProcess(), process5);
        assertEquals(processes.get(2).getProcess(), process1);
        assertEquals(processes.get(3).getProcess(), process3);
        assertEquals(processes.get(4).getProcess(), process2);
    }

    @Test
    public void shouldOrderByPriority() {
        processes. sort(Comparators.priorityComparator());

        assertEquals(processes.get(0).getPriority(), Priority.LOW);
        assertEquals(processes.get(1).getPriority(), Priority.LOW);
        assertEquals(processes.get(2).getPriority(), Priority.MEDIUM);
        assertEquals(processes.get(3).getPriority(), Priority.MEDIUM);
        assertEquals(processes.get(4).getPriority(), Priority.HIGH);
    }
}
