package com.kosmo.funfunhaejwo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;


class FunfunhaejwoApplicationTests {

	@Test
	void contextLoads() {
		Random i = new Random();
		int i1 = i.nextInt(100000);
		System.out.println(i1);
	}

}
