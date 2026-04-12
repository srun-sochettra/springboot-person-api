package com.example.springhomework001.controller;


import com.example.springhomework001.model.Person;
import com.example.springhomework001.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/v1/person")
@Tag(name = "Person API", description = "CRUD operations for Person")
public class PersonController {

    private final List<Person> personList = new ArrayList<>();
    private final AtomicInteger idCounter  = new AtomicInteger(1);

    // POST /api/v1/person
    @PostMapping
    @Operation(summary = "Insert a new person")
    public ResponseEntity<ApiResponse<Person>> createPerson(@RequestBody Person person) {

        person.setId(idCounter.getAndIncrement());
        personList.add(person);

        ApiResponse<Person> response = new ApiResponse<>(
                "Person created successfully",
                person,
                201
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // GET /api/v1/person
    @GetMapping
    @Operation(summary = "Get all persons")
    public ResponseEntity<ApiResponse<List<Person>>> getAllPersons() {

        ApiResponse<List<Person>> response = new ApiResponse<>(
                "All persons retrieved successfully",
                personList,
                "OK"
        );
        return ResponseEntity.ok(response);
    }

    // GET /api/v1/person/{id}
    @GetMapping("/{id}")
    @Operation(summary = "Get a person by ID")
    public ResponseEntity<ApiResponse<Person>> getPersonById(@PathVariable Integer id) {

        Person found = findById(id);

        if (found == null) {
            ApiResponse<Person> notFound = new ApiResponse<>(
                    "Person with id " + id + " not found",
                    null,
                    "NOT_FOUND"
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound);
        }

        ApiResponse<Person> response = new ApiResponse<>(
                "This person has found successfully",
                found,
                "OK"
        );
        return ResponseEntity.ok(response);
    }

    // GET /api/v1/person/search?name=
    @GetMapping("/search")
    @Operation(summary = "Get a person by name")
    public ResponseEntity<ApiResponse<Person>> getPersonByName(@RequestParam String name) {

        Person found = personList.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

        if (found == null) {
            ApiResponse<Person> notFound = new ApiResponse<>(
                    "Person with name \"" + name + "\" not found",
                    null,
                    "NOT_FOUND"
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound);
        }

        ApiResponse<Person> response = new ApiResponse<>(
                "This person has found successfully",
                found,
                "OK"
        );
        return ResponseEntity.ok(response);
    }

    // PUT /api/v1/person/{id}
    @PutMapping("/{id}")
    @Operation(summary = "Update a person by ID")
    public ResponseEntity<ApiResponse<Person>> updatePerson(
            @PathVariable Integer id,
            @RequestBody  Person  updated) {

        Person existing = findById(id);

        if (existing == null) {
            ApiResponse<Person> notFound = new ApiResponse<>(
                    "Person with id " + id + " not found",
                    null,
                    "NOT_FOUND"
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound);
        }

        existing.setName(updated.getName());
        existing.setGender(updated.getGender());
        existing.setAge(updated.getAge());

        ApiResponse<Person> response = new ApiResponse<>(
                "Person updated successfully",
                existing,
                200
        );
        return ResponseEntity.ok(response);
    }

    // DELETE /api/v1/person/{id}
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a person by ID")
    public ResponseEntity<ApiResponse<Person>> deletePerson(@PathVariable Integer id) {

        Person found = findById(id);

        if (found == null) {
            ApiResponse<Person> notFound = new ApiResponse<>(
                    "Person with id " + id + " not found",
                    null,
                    "NOT_FOUND"
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound);
        }

        personList.remove(found);

        ApiResponse<Person> response = new ApiResponse<>(
                "Person deleted successfully",
                found,
                200
        );
        return ResponseEntity.ok(response);
    }

    private Person findById(Integer id) {
        return personList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}