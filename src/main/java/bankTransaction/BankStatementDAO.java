package main.java.bankTransaction;

import java.time.LocalDate;
/*
 *  DAO(데이터 접근객체) : 테이블이나 특정 도메인 객체를 저장하는 데이터베이스와 상호작용할 때 흔히 볼수 있음.
 *  객체를 식별하는 일종의 ID가 필요하다. 기본적으로 DAO는 영구 저장 데이터베이스나 인메모리 데이터베이스 같은 데이터 소스로의 접근을 추상화하고 캡슐화 한다.
 */

public class BankStatementDAO {

    public BankTransaction create(final LocalDate date, final double amount, final String description) {
        // ...
        throw new UnsupportedOperationException();
    }

    public BankTransaction read(final long id) {
        // ...
        throw new UnsupportedOperationException();
    }

    public BankTransaction update(final long id) {
        // ...
        throw new UnsupportedOperationException();
    }

    public void delete(final BankTransaction bankTransaction) {
        // ...
        throw new UnsupportedOperationException();
    }
}