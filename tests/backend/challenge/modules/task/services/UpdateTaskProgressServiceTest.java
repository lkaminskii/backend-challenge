package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.services.RetrieveTaskByIdService;
import backend.challenge.modules.task.services.UpdateTaskProgressService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class UpdateTaskProgressServiceTest {

	private ITaskRepository taskRepository;
	private RetrieveTaskByIdService retrieveTaskByIdService;
	private UpdateTaskProgressService updateTaskProgressService;

	@Before
	public void setup() {
		taskRepository = Mockito.mock(ITaskRepository.class);
		retrieveTaskByIdService = Mockito.mock(RetrieveTaskByIdService.class);
		updateTaskProgressService = new UpdateTaskProgressService(retrieveTaskByIdService, taskRepository);
	}

	@Test
	public void shouldBeAbleToUpdateTaskProgress() {
		UUID taskId = UUID.randomUUID();
		Task task = new Task();
		task.setId(taskId);
		task.setProgress(100);

		when(retrieveTaskByIdService.execute(taskId)).thenReturn(task);
		when(taskRepository.updateProgress(Mockito.any(TaskProgressDTO.class))).thenReturn(task);

		TaskProgressDTO taskProgressDTO = new TaskProgressDTO();
		taskProgressDTO.setId(taskId);
		taskProgressDTO.setProgress(100);

		Task updatedTask;
		updatedTask = updateTaskProgressService.execute(taskProgressDTO);

		assertEquals(100, updatedTask.getProgress());
	}

	@Test
	public void shouldBeAbleToUpdateOnlyTaskStatusWhenProgressEqualsOneHundred() {
		UUID taskId = UUID.randomUUID();
		Task task = new Task();
		task.setId(taskId);
		task.setProgress(100);
		task.setStatus(TaskStatus.PROGRESS);

		when(retrieveTaskByIdService.execute(taskId)).thenReturn(task);
		when(taskRepository.updateProgress(Mockito.any(TaskProgressDTO.class))).thenReturn(task);

		TaskProgressDTO taskProgressDTO = new TaskProgressDTO();
		taskProgressDTO.setId(taskId);
		taskProgressDTO.setProgress(100);

		Task updatedTask;
		updatedTask = updateTaskProgressService.execute(taskProgressDTO);

		assertEquals(TaskStatus.COMPLETE, updatedTask.getStatus());
	}
}