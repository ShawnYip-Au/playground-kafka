package com.example.toto_jackpot;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Parameter;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/jackpot")
@RequiredArgsConstructor
public class JackpotAmountController {
    
    private final JackpotAmountService jackpotAmountService;

    @PostMapping("/publish")
    public void publishNewJackpot(
        @RequestParam @Parameter(description = "DD/MM/YYYY", example = "01/11/2025") String date,
        @RequestParam @Parameter(description = "1-7", example = "1") Integer group,
        @RequestParam @Parameter(description = "In Millions", example = "10") Integer amount
    ) {
        JackpotAmount jackpot = new JackpotAmount(date, group, amount);
        jackpotAmountService.publishNewJackpot(jackpot);
    }

}
