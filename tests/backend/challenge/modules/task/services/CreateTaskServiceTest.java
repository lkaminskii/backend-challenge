package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class CreateTaskServiceTest {

	private ICreateTaskService createTaskService;

	@Before
	public void init() {
		final ITaskRepository taskRepository = new TaskRepository();
		createTaskService = new CreateTaskService(taskRepository);
	}

	@Test
	public void shouldBeAbleToCreateANewTask() {
		TaskDTO taskDTO = new TaskDTO("Test", "This is a test description");

		Task createdTask = createTaskService.execute(taskDTO);

		// Assert: Verify that the created task is not null
		assertNotNull("The created task should not be null", createdTask);
	}
}