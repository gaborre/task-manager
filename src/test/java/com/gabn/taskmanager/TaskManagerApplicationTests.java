package com.gabn.taskmanager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class TaskManagerApplicationTests {

	@Test
	void contextLoads() {
		assertAll(() -> TaskManagerApplication.main(new String[]{}));
	}

}
