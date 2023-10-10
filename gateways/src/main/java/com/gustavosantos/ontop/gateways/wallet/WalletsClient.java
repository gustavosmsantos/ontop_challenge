package com.gustavosantos.ontop.gateways.wallet;

import com.gustavosantos.ontop.gateways.wallet.dto.WalletBalanceResponse;
import feign.Param;
import feign.RequestLine;

public interface WalletsClient {

    @RequestLine("GET /balance?user_id={user}")
    WalletBalanceResponse retrieveBalance(@Param("user") Long userId);

}
