package backend.challenge.modules.task.services;

import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RetrieveAllTasksServiceTest {

	@InjectMocks
	private RetrieveAllTasksService retrieveAllTasksService;

	@Mock
	private ITaskRepository taskRepository;

	@Before
	public void setUp() {
		Task task1 = new Task();
		task1.setTitle("Task 1");
		task1.setDescription("Description 1");

		Task task2 = new Task();
		task2.setTitle("Task 2");
		task2.setDescription("Description 2");

		List<Task> tasks = Arrays.asList(task1, task2);

		when(taskRepository.show()).thenReturn(tasks);
	}

	@Test
	public void shouldBeAbleToListTheTasks() {
		List<Task> tasks = retrieveAllTasksService.execute();

		assertEquals(2, tasks.size());
		assertEquals("Task 1", tasks.get(0).getTitle());
		assertEquals("Task 2", tasks.get(1).getTitle());
	}
}