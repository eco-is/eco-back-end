package com.eco.environet.finance.controller;

import com.eco.environet.finance.dto.FixedExpensesDto;
import com.eco.environet.finance.services.FixedExpensesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fixed-expenses")
@RequiredArgsConstructor
@Tag(name = "Fixed Expenses", description = "Manage Fixed Expenses")
public class FixedExpensesController {
    private final FixedExpensesService service;

    @Operation(summary = "Create new fixed expense")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New fixed expense created!",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FixedExpensesDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)
    })
    @PostMapping(value = "/create", consumes = "application/json")
    public ResponseEntity<FixedExpensesDto> createNewFixedExpense(@RequestBody FixedExpensesDto newExpenseDto) {
        var result = service.create(newExpenseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // TODO
    //finaAll

    @Operation(summary = "Get fixed expense")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetched fixed expense",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FixedExpensesDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)})
    @GetMapping(value="/get-expense/{id}")
    @PreAuthorize("hasAnyRole('BOARD_MEMBER', 'ACCOUNTANT')")
    public ResponseEntity<FixedExpensesDto> getFixedExpense(@PathVariable Long id){
        var result = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "Accountant updates fixed expense")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fixed expense updated!",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FixedExpensesDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)
    })
    @PutMapping(value = "/update")
    @PreAuthorize("hasRole('ACCOUNTANT')")
    public ResponseEntity<FixedExpensesDto> updateFixedExpense(@RequestBody FixedExpensesDto fixedExpensesDto) {
        // Check if authenticated user matches the author of fixed explense
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        if (!currentUsername.equals(fixedExpensesDto.getCreator().getUsername())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        var result = service.update(fixedExpensesDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "Accountant can delete fixed expense by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Fixed expense deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)
    })
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ACCOUNTANT')")
    public ResponseEntity<Void> deleteFixedExpense(@PathVariable Long id, @RequestBody FixedExpensesDto fixedExpenseDto) {
        // Check if authenticated user matches the author of fixed explense
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        if (!currentUsername.equals(fixedExpenseDto.getCreator().getUsername())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        service.delete(fixedExpenseDto.getId());
        return ResponseEntity.noContent().build();
    }
}