package com.volkonovskij.controller.rest.springdata;

import com.volkonovskij.domain.hibernate.HibernateRegion;
import com.volkonovskij.repository.springdata.RegionsDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/springdata/regions")
@RequiredArgsConstructor
public class RegionDataController {

    private final RegionsDataRepository regionsDataRepository;

    @GetMapping(value = "/{number}")
    public ResponseEntity<Object> findAllNumberRegion(@PathVariable Long number) {
        Map<String, List<HibernateRegion>> numberRegion = Collections
                .singletonMap("result", regionsDataRepository.findByNumber(number));

        return new ResponseEntity<>(numberRegion, HttpStatus.OK);
    }
}
