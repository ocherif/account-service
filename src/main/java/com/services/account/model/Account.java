package com.services.account.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Account extends Object {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    private String customerName;

    private BigDecimal position;

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || this.getClass() != obj.getClass()) return false;

        Account Account = (Account) obj;

        return (customerId == Account.customerId) &&
                (customerName.equals(Account.customerName))
                        && (position.compareTo((Account.position)) == 0);
    }

    @Override
    public int hashCode() {
        return customerId.hashCode() *  customerName.hashCode() * position.hashCode();
    }

    public static class AccountBuilder {

        private long customerId;

        @Column(name="customer_name", nullable = false, length = 128)
        private String customerName;

        private BigDecimal position;

        public AccountBuilder() {
        }

        public AccountBuilder(long customerId) {
            this.customerId = customerId;
        }

        public AccountBuilder withCustomerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public AccountBuilder withPosition(BigDecimal position) {
            this.position = position;
            return this;
        }

        public Account build() {
            Account Account = new Account();
            Account.setCustomerName(this.customerName);
            Account.setPosition(this.position);
            return Account;
        }
    }
}
