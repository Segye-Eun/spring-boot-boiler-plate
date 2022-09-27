package com.pref.krule.domain.account.repository;

import com.pref.krule.domain.account.entity.Account;
import com.pref.krule.domain.account.repository.extension.AccountRepositoryExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>, AccountRepositoryExtension {
    Account findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);
}
