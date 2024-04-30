package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.UUID;

import static org.junit.Assert.*;

@RunWith( KikahaRunner.class )
public class UpdateTaskServiceTest {

	private IUpdateTaskService updateTaskService;
	private ICreateTaskService createTaskService;
	private IRetrieveTaskByIdService retrieveTaskByIdService;

	@Before
	public void init() {
		final ITaskRepository taskRepository = new TaskRepository();

		updateTaskService = new UpdateTaskService(taskRepository);
		createTaskService = new CreateTaskService(taskRepository);
		retrieveTaskByIdService = new RetrieveTaskByIdService(taskRepository);
	}

	@Test
	public void shouldBeAbleToUpdateTask() {
		TaskDTO taskDTO = new TaskDTO("Test", "Test description");
		Task task = createTaskService.execute(taskDTO);

		task.setTitle("Updated Test");
		task.setDescription("Updated Test description");
		Task updatedTask = updateTaskService.execute(task);

		assertEquals("Updated Test", updatedTask.getTitle());
		assertEquals("Updated Test description", updatedTask.getDescription());
	}

	@Test(expected = RuntimeException.class)
	public void shouldNotBeAbleToUpdateATaskThatDoesNotExist() {
		Task task = new Task();
		task.setId(UUID.randomUUID());

		updateTaskService.execute(task);
	}

	@Test
	public void shouldNotBeAbleToUpdateTaskStatusManually() {
		TaskDTO taskDTO = new TaskDTO("Test", "Test description");
		Task task = createTaskService.execute(taskDTO);

		TaskStatus originalStatus = task.getStatus();
		task.setStatus(TaskStatus.COMPLETE);
		Task updatedTask = updateTaskService.execute(task);

		assertEquals(originalStatus, updatedTask.getStatus());
	}
}