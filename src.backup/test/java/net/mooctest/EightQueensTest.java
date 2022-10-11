package net.mooctest;

import static org.junit.Assert.*;

import org.junit.Test;

public class EightQueensTest {

	@Test(timeout = 4000)
	public void test() {
		EightQueens board = new EightQueens();
		Queen[] startBoard = board.generateBoard();
	}

}
