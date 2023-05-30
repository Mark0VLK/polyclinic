package com.volkonovskij.controller.rest;

import com.volkonovskij.controller.exceptions.IllegalRequestException;
import com.volkonovskij.controller.requests.patient.PatientCreateRequest;
import com.volkonovskij.controller.requests.patient.PatientUpdateRequest;
import com.volkonovskij.controller.requests.search.SearchByGenderAndRegion;
import com.volkonovskij.controller.requests.search.SearchCriteria;
import com.volkonovskij.domain.Patient;
import com.volkonovskij.domain.system.Gender;
import com.volkonovskij.repository.PatientsRepository;
import com.volkonovskij.service.PatientsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/rest/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientsRepository patientsRepository;

    private final PatientsService patientsService;

    private final ConversionService conversionService;

    @Operation(
            summary = "Find all patients",
            description = "find all patients without limitations",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded patients",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Patient.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<Object> getAllPatients() {
        List<Patient> patients = patientsRepository.findAll();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @Operation(
            summary = "Search for patients with output to the page",
            description = "load page by number with sort and offset params",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded patients",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PageImpl.class))
                    )
            }
    )
    @GetMapping("/page/{page}")
    public ResponseEntity<Object> page(@Parameter(name = "page", example = "1", required = true) @PathVariable int page) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                patientsRepository.findAll(PageRequest.of(page, 1))), HttpStatus.OK);
    }

    @Operation(
            summary = "Find the patient",
            description = "find the patient by his id",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded patient",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Patient.class))
                    )
            }
    )
    @GetMapping("/{cardNumber}")
    public ResponseEntity<Object> getPatientById(@Parameter(name = "cardNumber", example = "2", required = true) @PathVariable Long cardNumber) {

        Optional<Patient> patient = patientsRepository.findById(cardNumber);

        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @Operation(
            summary = "Search for all active patients",
            description = "search for all active patients",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded active patients",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PageImpl.class))
                    )
            }
    )
    @GetMapping("/active")
    public ResponseEntity<Object> findAllVisiblePatients() {

        Map<String, List<Patient>> patients = Collections.singletonMap("result", patientsRepository.findByIsDeletedIsFalse());

        return new ResponseEntity<>(patients, HttpStatus.OK);

    }


    @Operation(
            summary = "Find the patient's address and phone number",
            description = "find the address and phone number of the patient by first and last name",
            parameters = {
                    @Parameter(
                            name = "name",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "Mark",
                                    type = "string",
                                    description = "patient's name")
                    ),
                    @Parameter(
                            name = "surname",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "Volkonovskij",
                                    type = "string",
                                    description = "patient's last name"))
            },
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded address and phone number",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @GetMapping("find/phoneAndAddress")
    public ResponseEntity<Object> phoneNumberByNameAndSurname(@Parameter(hidden = true) @Valid @ModelAttribute SearchCriteria criteria, BindingResult result) {

        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }

        List<Object[]> phoneAndAddress = patientsService.phoneAndAddressByInitials(criteria.getName(), criteria.getSurname());

        return new ResponseEntity<>(Collections.singletonMap("phoneAndAddress", phoneAndAddress), HttpStatus.OK);
    }

    @Operation(
            summary = "Find a patient's medical history",
            description = "find a patient's medical history by his first and last name",
            parameters = {
                    @Parameter(
                            name = "name",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "Mark",
                                    type = "string",
                                    description = "patient's name")
                    ),
                    @Parameter(
                            name = "surname",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "Volkonovskij",
                                    type = "string",
                                    description = "patient's last name"))
            },
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded patient's medical history",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @GetMapping("/medicalHistory")
    public ResponseEntity<Object> medicalHistory(@Parameter(hidden = true) @Valid @ModelAttribute SearchCriteria criteria, BindingResult result) {

        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }

        List<Object[]> history = patientsService.medicalHistory(criteria.getName(), criteria.getSurname());

        return new ResponseEntity<>(Collections.singletonMap("history", history), HttpStatus.OK);
    }

    @Operation(
            summary = "Find all patients of a certain region older than the selected date",
            description = "find all patients of a certain region older than the selected date",
            parameters = {
                    @Parameter(
                            name = "data",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "2023-05-30 00:00:00",
                                    type = "string", format = "date-time",
                                    description = "birth date")
                    ),
                    @Parameter(
                            name = "number",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "4",
                                    type = "number",
                                    description = "number of the region the patient belongs to"))
            },
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded information",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @GetMapping("/search")
    public ResponseEntity<Object> searchByGenderAndBirthDate(@Parameter(hidden = true) @Valid @ModelAttribute SearchByGenderAndRegion criteria, BindingResult result) {

        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }

        List<Object[]> patients = patientsService.findByGenderAndRegionNumber(criteria.getData(), criteria.getNumber());

        return new ResponseEntity<>(Collections.singletonMap("patient", patients), HttpStatus.OK);
    }

    @Operation(
            summary = "Find out the total price of all scheduled visits",
            description = "find out the total cost of all scheduled visits by the patient's first and last name",
            parameters = {
                    @Parameter(
                            name = "name",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "Mark",
                                    type = "string",
                                    description = "patient's name")
                    ),
                    @Parameter(
                            name = "surname",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "Volkonovskij",
                                    type = "string",
                                    description = "patient's last name"))
            },
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded total price",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @GetMapping("/totalAmount")
    public ResponseEntity<Object> totalAmount(@Parameter(hidden = true) @Valid @ModelAttribute SearchCriteria criteria, BindingResult result) {

        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }

        Float total = patientsService.unpaidAmount(criteria.getName(), criteria.getSurname());

        return new ResponseEntity<>(Collections.singletonMap("totalAmount", total), HttpStatus.OK);
    }

    @Operation(
            summary = "Create a new patient",
            description = "create a new patient",
            parameters = {
                    @Parameter(
                            name = "name",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "Mark",
                                    type = "string",
                                    description = "patient's name")
                    ),
                    @Parameter(
                            name = "surname",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "Volkonovskij",
                                    type = "string",
                                    description = "patient's last name")
                    ),
                    @Parameter(
                            name = "gender",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "NOT_SELECTED",
                                    type = "Gender",
                                    implementation = Gender.class,
                                    description = "patient's gender")
                    ),
                    @Parameter(
                            name = "birthDate",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "2023-05-30 00:00:00",
                                    type = "string", format = "date-time",
                                    description = "patient's birth date")
                    ),
                    @Parameter(
                            name = "address",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "Ozheshko Street, apartment 15",
                                    type = "string",
                                    description = "patient's address")
                    ),
                    @Parameter(
                            name = "regionNumber",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "4",
                                    type = "number",
                                    description = "id of the region the patient belongs to")
                    ),
                    @Parameter(
                            name = "userId",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "14",
                                    type = "number",
                                    description = "id of the user the patient belongs to")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "CREATED",
                            description = "Successfully created patient",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Patient.class)
                            )
                    )
            }
    )
    @PostMapping
    public ResponseEntity<Object> savePatient(@Parameter(hidden = true) @Valid @ModelAttribute PatientCreateRequest request, BindingResult result) {

        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }

        Patient patient = conversionService.convert(request, Patient.class);

        patient = patientsRepository.save(patient);

        return new ResponseEntity<>(patient, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Edit an existing patient",
            description = "edit an existing patient",
            parameters = {
                    @Parameter(
                            name = "cardNumber",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "8",
                                    type = "number",
                                    description = "patient id")
                    ),
                    @Parameter(
                            name = "name",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "Mark",
                                    type = "string",
                                    description = "patient's name")
                    ),
                    @Parameter(
                            name = "surname",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "Volkonovskij",
                                    type = "string",
                                    description = "patient's last name")
                    ),
                    @Parameter(
                            name = "gender",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "NOT_SELECTED",
                                    type = "Gender",
                                    implementation = Gender.class,
                                    description = "patient's gender")
                    ),
                    @Parameter(
                            name = "birthDate",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "2023-05-30 00:00:00",
                                    type = "string", format = "date-time",
                                    description = "patient's birth date")
                    ),
                    @Parameter(
                            name = "address",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "Ozheshko Street, apartment 15",
                                    type = "string",
                                    description = "patient's address")
                    ),
                    @Parameter(
                            name = "regionNumber",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "4",
                                    type = "number",
                                    description = "id of the region the patient belongs to")
                    ),
                    @Parameter(
                            name = "userId",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "14",
                                    type = "number",
                                    description = "id of the user the patient belongs to")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully changed the patient information",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Patient.class)
                            )
                    )
            }
    )
    @PutMapping
    public ResponseEntity<Object> updatePatient(@Parameter(hidden = true) @Valid @ModelAttribute PatientUpdateRequest request) {

        Patient patient = conversionService.convert(request, Patient.class);

        patient = patientsRepository.save(patient);

        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete a patient by id",
            description = "delete a patient by id",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "The patient has been successfully removed",
                            content = @Content(
                                    mediaType = "application/json")
                    )
            }
    )
    @DeleteMapping("/{cardNumber}")
    public ResponseEntity<Object> deletePatient(@Parameter(name = "cardNumber", example = "2", required = true) @PathVariable Long cardNumber) {

        Optional<Patient> patient = patientsRepository.findById(cardNumber);

        patientsRepository.deleteById(cardNumber);

        return new ResponseEntity<>(patient, HttpStatus.OK);
    }
}
