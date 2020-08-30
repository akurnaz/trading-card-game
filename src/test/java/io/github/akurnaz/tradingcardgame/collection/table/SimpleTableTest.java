package io.github.akurnaz.tradingcardgame.collection.table;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.akurnaz.tradingcardgame.collection.Card;

class SimpleTableTest {
	private Table table;

	@BeforeEach
	void BeforeEach() {
		table = new SimpleTable();
	}

	@Test
	void insert_givenEmptyTable_thenReturnTrue() {
		// then
		assertTrue(table.insert(new Card(3)));
	}

	@Test
	void toString_givenNotEmptyTable_thenReturnEquals() {
		// given
		table.insert(new Card(3));

		// then
		assertEquals("[3]", table.toString());
	}
}
