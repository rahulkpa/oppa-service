package com.nirsb.oppa.oppaservice.controller;
import com.nirsb.oppa.oppaservice.dao.DocumentRepository;
import com.nirsb.oppa.oppaservice.model.Document;
import com.nirsb.oppa.oppaservice.model.DocumentDTO;
import com.nirsb.oppa.oppaservice.model.Response;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class PrintController {

    @Autowired
    DocumentRepository documentRepository;

    @PostMapping(value = "/print",consumes = MediaType.ALL_VALUE)
    public ResponseEntity<com.nirsb.oppa.oppaservice.model.Response> printDocument(@ModelAttribute Document document) throws IOException {
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setDocFormat(document.getDocFormat());
        documentDTO.setDocTypes(document.getDocTypes());
        documentDTO.setEmailAddress(document.getEmailAddress());
        documentDTO.setMobileNumber(document.getMobileNumber());
        documentDTO.setNoOfCopies(document.getNoOfCopies());
        System.out.println(document.getFile().getContentType());
        documentDTO.setFile(document.getFile().getBytes());
        DocumentDTO dto = documentRepository.save(documentDTO);
        if(dto != null) {
            Response response = new Response("Document Saved Successfully");
            Link selfLink = ControllerLinkBuilder.linkTo(DocumentDTO.class)
                    .slash("documents")
                    .slash(dto.getDocId())
                    .withSelfRel();
            response.add(selfLink);
            return new ResponseEntity<com.nirsb.oppa.oppaservice.model.Response>(response, HttpStatus.CREATED);
        }
        Response response = new Response("Document insertion has some issue");
        return  new ResponseEntity<com.nirsb.oppa.oppaservice.model.Response>(response, HttpStatus.OK);
    }

    @GetMapping("documents/{docId}")
    public ResponseEntity getDocument(@PathVariable long docId) throws IOException {
        Optional<com.nirsb.oppa.oppaservice.model.DocumentDTO> documentDTO = documentRepository.findById(docId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        if(documentDTO.isPresent()) {
            ByteArrayResource resource = new ByteArrayResource(documentDTO.get().getFile());
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(documentDTO.get().getFile().length)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        }
        byte [] bytes = new byte[0];
        ByteArrayResource resource = new ByteArrayResource(bytes);
        return  ResponseEntity.ok()
                .headers(headers)
                .contentLength(0)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
