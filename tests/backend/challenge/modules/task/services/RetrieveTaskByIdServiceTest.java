package backend.challenge.modules.task.services;


import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.models.Task;

import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith( KikahaRunner.class )
public class RetrieveTaskByIdServiceTest {

	private IRetrieveTaskByIdService retrieveTaskByIdService;
	private ICreateTaskService createTaskService;

	@Before
	public void init() {
		final ITaskRepository taskRepository = new TaskRepository();
		retrieveTaskByIdService = new RetrieveTaskByIdService(taskRepository);
		createTaskService = new CreateTaskService(taskRepository);
	}

	@Test
	public void shouldBeAbleToListTheTaskById() {
		TaskDTO taskDTO = new TaskDTO("Test", "Test description");

		Task task = createTaskService.execute(taskDTO);
		Task retrievedTask = retrieveTaskByIdService.execute(task.getId());

		assertNotNull(retrievedTask);
		assertEquals(task.getId(), retrievedTask.getId());
	}

}
