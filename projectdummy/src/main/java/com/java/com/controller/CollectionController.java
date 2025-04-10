package com.java.com.controller;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/collections")
public class CollectionController {

	private List<String> stringList = new ArrayList<>();
	private Set<Integer> integerSet = new HashSet<>();
	private Map<String, String> stringMap = new HashMap<>();

	// --- List Operations ---

	@GetMapping("/list")
	public ResponseEntity<List<String>> getAllStrings() {
		return ResponseEntity.ok(stringList);
	}

	@PostMapping("/list")
	public ResponseEntity<List<String>> addString(@RequestBody String newString) {
		stringList.add(newString);
		return ResponseEntity.status(HttpStatus.CREATED).body(stringList);
	}

	@GetMapping("/list/{index}")
	public ResponseEntity<?> getStringByIndex(@PathVariable int index) {
		if (index >= 0 && index < stringList.size()) {
			return ResponseEntity.ok(stringList.get(index));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Index out of bounds");
		}
	}

	@PutMapping("/list/{index}")
	public ResponseEntity<?> updateString(@PathVariable int index, @RequestBody String updatedString) {
		if (index >= 0 && index < stringList.size()) {
			stringList.set(index, updatedString);
			return ResponseEntity.ok(stringList);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Index out of bounds");
		}
	}

	@DeleteMapping("/list/{index}")
	public ResponseEntity<?> deleteString(@PathVariable int index) {
		if (index >= 0 && index < stringList.size()) {
			stringList.remove(index);
			return ResponseEntity.ok(stringList);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Index out of bounds");
		}
	}

	// --- Set Operations ---

	@GetMapping("/set")
	public ResponseEntity<Set<Integer>> getAllIntegers() {
		return ResponseEntity.ok(integerSet);
	}

	@PostMapping("/set")
	public ResponseEntity<Set<Integer>> addInteger(@RequestBody Integer newInteger) {
		integerSet.add(newInteger);
		return ResponseEntity.status(HttpStatus.CREATED).body(integerSet);
	}

	@GetMapping("/set/contains/{value}")
	public ResponseEntity<Boolean> containsInteger(@PathVariable int value) {
		return ResponseEntity.ok(integerSet.contains(value));
	}

	@DeleteMapping("/set/{value}")
	public ResponseEntity<Set<Integer>> deleteInteger(@PathVariable int value) {
		integerSet.remove(value);
		return ResponseEntity.ok(integerSet);
	}

	// --- Map Operations ---

	@GetMapping("/map")
	public ResponseEntity<Map<String, String>> getAllEntries() {
		return ResponseEntity.ok(stringMap);
	}

	@PostMapping("/map")
	public ResponseEntity<Map<String, String>> addEntry(@RequestBody Map<String, String> newEntry) {
		stringMap.putAll(newEntry);
		return ResponseEntity.status(HttpStatus.CREATED).body(stringMap);
	}

	@GetMapping("/map/{key}")
	public ResponseEntity<?> getValueByKey(@PathVariable String key) {
		if (stringMap.containsKey(key)) {
			return ResponseEntity.ok(stringMap.get(key));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Key not found");
		}
	}

	@PutMapping("/map/{key}")
	public ResponseEntity<Map<String, String>> updateValue(@PathVariable String key, @RequestBody String newValue) {
		if (stringMap.containsKey(key)) {
			stringMap.put(key, newValue);
			return ResponseEntity.ok(stringMap);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyMap());
		}
	}

	@DeleteMapping("/map/{key}")
	public ResponseEntity<Map<String, String>> deleteEntry(@PathVariable String key) {
		stringMap.remove(key);
		return ResponseEntity.ok(stringMap);
	}
}