package hcmute.it.furnitureshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class FurnitureShopApplicationTests {

	@ParameterizedTest
	@NullSource
	void isNullTest(String input) {
		assertNull(input,"");
	}
	@ParameterizedTest
	@EmptySource
	void isEmptyTest(Integer[] input) {
		assertEquals(0, input.length);
	}
	@ParameterizedTest
	@EmptySource
	void isEmptyTest(List<String> input) {
		assertEquals(0, input.size());
	}
	@ParameterizedTest
	@EmptySource
	void isEmptyTest(Set<String> input) {
		assertEquals(0, input.size());
	}
	@ParameterizedTest
	@EmptySource
	void isEmptyTest(Map<String, Integer> input) {
		assertEquals(0, input.size());
	}

}
