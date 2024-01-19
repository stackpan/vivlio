package com.ivanzkyanto.vivlio.controller;

import com.ivanzkyanto.vivlio.controller.dto.RootEntryPointResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class RootEntryPointController {

    @GetMapping
    public ResponseEntity<RootEntryPointResponse> getRoot() {
        RootEntryPointResponse response = new RootEntryPointResponse();
        response.add(linkTo(methodOn(BookController.class).getAll()).withRel("books"));

        return ResponseEntity.ok(response);
    }

}
