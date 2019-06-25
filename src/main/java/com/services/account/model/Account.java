package com.services.account.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Account extends Object {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal position;

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || this.getClass() != obj.getClass()) return false;

        Account Account = (Account) obj;

        return (id == Account.id) &&
                (name.equals(Account.name))
                        && (position.compareTo((Account.position)) == 0);
    }

    @Override
    public int hashCode() {
        return id.hashCode() *  name.hashCode() * position.hashCode();
    }

    public static class AccountBuilder {

        private long id;

        private String name;

        private BigDecimal position;

        public AccountBuilder() {
        }

        public AccountBuilder(long id) {
            this.id = id;
        }

        public AccountBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public AccountBuilder withPosition(BigDecimal position) {
            this.position = position;
            return this;
        }

        public Account build() {
            Account Account = new Account();
            Account.setName(this.name);
            Account.setPosition(this.position);
            return Account;
        }
    }
}
