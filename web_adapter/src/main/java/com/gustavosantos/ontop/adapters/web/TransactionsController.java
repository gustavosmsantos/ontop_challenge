package com.gustavosantos.ontop.adapters.web;

import com.gustavosantos.ontop.core.exceptions.InsufficientFundsException;
import com.gustavosantos.ontop.core.usecases.TransferToBankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/transactions")
public class TransactionsController {

    @Autowired
    private TransferToBankAccount transferUseCase;

    @PostMapping
    @RequestMapping("/withdraw")
    public ResponseEntity<String> createWithdrawTransaction(@RequestBody TransactionRequest request) {
        try {
            transferUseCase.execute(request.userId(), request.amount());
        } catch (InsufficientFundsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.accepted().build();
    }

}