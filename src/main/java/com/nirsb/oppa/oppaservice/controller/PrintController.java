package com.nirsb.oppa.oppaservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nirsb.oppa.oppaservice.dao.DocumentRepository;
import com.nirsb.oppa.oppaservice.model.Document;
import com.nirsb.oppa.oppaservice.model.DocumentDTO;
import com.nirsb.oppa.oppaservice.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
public class PrintController {

    @Autowired
    DocumentRepository documentRepository;
    ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping(value = "/print", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<com.nirsb.oppa.oppaservice.model.Response> printDocument(@RequestPart("document") String document,
            @RequestPart MultipartFile file) throws IOException {
        DocumentDTO documentDTO = new DocumentDTO();
        Document document1 = objectMapper.readValue(document, Document.class);
        documentDTO.setDocFormat(document1.getDocFormat());
        documentDTO.setDocTypes(document1.getDocTypes());
        documentDTO.setEmailAddress(document1.getEmailAddress());
        documentDTO.setMobileNumber(document1.getMobileNumber());
        documentDTO.setNoOfCopies(document1.getNoOfCopies());
        documentDTO.setFile(file.getBytes());
        DocumentDTO dto = documentRepository.save(documentDTO);
        Response response = new Response("Document Saved Successfully");
        Link selfLink = WebMvcLinkBuilder.linkTo(DocumentDTO.class)
                .slash("documents")
                .slash(dto.getId())
                .withSelfRel();
        response.add(selfLink);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("documents/{docId}")
    public ResponseEntity getDocument(@PathVariable long docId) throws IOException {
        Optional<com.nirsb.oppa.oppaservice.model.DocumentDTO> documentDTO = documentRepository.findById(docId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        if (documentDTO.isPresent()) {
            ByteArrayResource resource = new ByteArrayResource(documentDTO.get().getFile());
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(documentDTO.get().getFile().length)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        }
        byte[] bytes = new byte[0];
        ByteArrayResource resource = new ByteArrayResource(bytes);
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(0)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
