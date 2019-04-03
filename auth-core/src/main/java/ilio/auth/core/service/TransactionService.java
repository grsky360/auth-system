package ilio.auth.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

    @FunctionalInterface
    public interface TransactionExecutor<T> {
        T execute();
    }

    @Transactional(rollbackFor = Exception.class)
    public <T> T run(TransactionExecutor<T> executor) {
        return executor.execute();
    }
}
