package com.gustaosantos.ontop.core.usecases;

import com.gustavosantos.ontop.core.domain.BankAccount;
import com.gustavosantos.ontop.core.domain.BankAccountBuilder;
import com.gustavosantos.ontop.core.domain.Transaction;
import com.gustavosantos.ontop.core.domain.TransactionBuilder;
import com.gustavosantos.ontop.core.domain.TransactionComponent;
import com.gustavosantos.ontop.core.domain.Wallet;
import com.gustavosantos.ontop.core.exceptions.InsufficientFundsException;
import com.gustavosantos.ontop.core.logic.TransactionsFactory;
import com.gustavosantos.ontop.core.ports.BankAccountRepository;
import com.gustavosantos.ontop.core.ports.TransactionsRepository;
import com.gustavosantos.ontop.core.ports.WalletsGateway;
import com.gustavosantos.ontop.core.usecases.TransferToBankAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;

import static com.gustavosantos.ontop.core.domain.TransactionComponent.Type.FEE;
import static com.gustavosantos.ontop.core.domain.TransactionComponent.Type.NET_AMOUNT;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransferToBankAccountTest {

    @Mock
    private BankAccountRepository bankAccountRepository;

    @Mock
    private TransactionsRepository transactionsRepository;

    @Mock
    private WalletsGateway walletsGateway;

    @Spy
    private TransactionsFactory transactionsFactory = new TransactionsFactory(new BigDecimal("0.1"));

    @InjectMocks
    private TransferToBankAccount usecase;

    @Test
    public void testSucceededTransfer() throws InsufficientFundsException {
        Long userId = 1000L;

        Wallet wallet = new Wallet(userId, new BigDecimal("100"));
        when(walletsGateway.retrieveWallet(userId)).thenReturn(wallet);

        BankAccount bankAccount = BankAccountBuilder.builder()
                .userId(userId)
                .accountNumber("12345")
                .bankName("Users bank")
                .build();
        when(bankAccountRepository.findByUserId(userId)).thenReturn(bankAccount);

        usecase.execute(userId, BigDecimal.TEN);

        ArgumentCaptor<Transaction> transactionCaptor = ArgumentCaptor.forClass(Transaction.class);
        verify(transactionsRepository).save(transactionCaptor.capture());
        verify(walletsGateway).withdraw(eq(wallet), eq(BigDecimal.TEN));

        Transaction expected = TransactionBuilder.builder()
                .userId(userId)
                .type(Transaction.Type.WITHDRAWAL)
                .destinationAccount(bankAccount)
                .components(Arrays.asList(
                        new TransactionComponent(NET_AMOUNT, new BigDecimal("9.0")),
                        new TransactionComponent(FEE, new BigDecimal("1.0"))
                ))
                .build();
        Assertions.assertEquals(expected, transactionCaptor.getValue());
    }

    @Test
    public void testWalletWithInsufficientFunds() {
        Long userId = 1000L;
        Wallet wallet = new Wallet(userId, BigDecimal.ONE);
        when(walletsGateway.retrieveWallet(userId)).thenReturn(wallet);

        Assertions.assertThrows(InsufficientFundsException.class, () -> {
            usecase.execute(userId, new BigDecimal("1.01"));
        });

        verifyNoInteractions(transactionsRepository);
        verifyNoMoreInteractions(walletsGateway);
    }

}
