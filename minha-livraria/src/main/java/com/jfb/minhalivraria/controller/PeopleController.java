package com.jfb.minhalivraria.controller;

import com.jfb.minhalivraria.People;
import com.jfb.minhalivraria.controller.request.PeopleRequest;
import com.jfb.minhalivraria.producer.PeopleProducer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/peoples")
@AllArgsConstructor
public class PeopleController {

    private final PeopleProducer peopleProducer;

    @PostMapping
    public ResponseEntity<Void> sendMessage(@RequestBody PeopleRequest dto) {
        var id = UUID.randomUUID().toString();

        var message = People.newBuilder()
                .setId(id)
                .setName(dto.getName())
                .setCpf(dto.getCpf())
                .setBooks(dto.getBooks().stream().map(p -> (CharSequence) p).collect(Collectors.toList())).build();
        peopleProducer.sendMessage(message);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
