/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  org.spongepowered.api.Sponge
 *  org.spongepowered.api.entity.living.player.User
 *  org.spongepowered.api.event.cause.Cause
 *  org.spongepowered.api.event.cause.EventContext
 *  org.spongepowered.api.service.economy.account.Account
 *  org.spongepowered.api.service.economy.account.UniqueAccount
 *  org.spongepowered.api.service.economy.transaction.ResultType
 *  org.spongepowered.api.service.economy.transaction.TransactionResult
 *  org.spongepowered.api.service.economy.transaction.TransferResult
 */
package com.m1zark.m1utilities.api;

import com.m1zark.m1utilities.M1utilities;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.EventContext;
import org.spongepowered.api.service.economy.account.Account;
import org.spongepowered.api.service.economy.account.UniqueAccount;
import org.spongepowered.api.service.economy.transaction.ResultType;
import org.spongepowered.api.service.economy.transaction.TransactionResult;
import org.spongepowered.api.service.economy.transaction.TransferResult;

public class Money {
    public static boolean canPay(User user, int price) {
        UniqueAccount acc = M1utilities.getInstance().getEconomy().getOrCreateAccount(user.getUniqueId()).orElse(null);
        if (acc == null) {
            return false;
        }
        return acc.getBalance(M1utilities.getInstance().getEconomy().getDefaultCurrency()).compareTo(BigDecimal.valueOf(price)) >= 0;
    }

    public static boolean transfer(UUID buy, UUID sell, int price) {
        UniqueAccount buyer = M1utilities.getInstance().getEconomy().getOrCreateAccount(buy).orElse(null);
        UniqueAccount seller = M1utilities.getInstance().getEconomy().getOrCreateAccount(sell).orElse(null);
        if (buyer == null || seller == null) {
            return false;
        }
        TransferResult tr = buyer.transfer(seller, M1utilities.getInstance().getEconomy().getDefaultCurrency(), BigDecimal.valueOf(price), Sponge.getCauseStackManager().getCurrentCause());
        return tr.getResult().equals(ResultType.SUCCESS);
    }

    public static boolean withdrawn(User user, int price) {
        UniqueAccount acc = M1utilities.getInstance().getEconomy().getOrCreateAccount(user.getUniqueId()).orElse(null);
        if (acc == null) {
            return false;
        }
        TransactionResult tr = acc.withdraw(M1utilities.getInstance().getEconomy().getDefaultCurrency(), new BigDecimal(price), Cause.of(EventContext.empty(), M1utilities.getInstance()));
        return tr.getResult().equals(ResultType.SUCCESS);
    }

    public static double getBalance(User user) {
        Optional<UniqueAccount> acc = M1utilities.getInstance().getEconomy().getOrCreateAccount(user.getUniqueId());
        return acc.map(uniqueAccount -> uniqueAccount.getBalance(M1utilities.getInstance().getEconomy().getDefaultCurrency()).doubleValue()).orElse(0.0);
    }
}

