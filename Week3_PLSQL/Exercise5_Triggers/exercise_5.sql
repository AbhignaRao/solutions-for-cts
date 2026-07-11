-- Scenario 1: UpdateCustomerLastModified
CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    :NEW.LastModified := SYSDATE;
END;
/

-- Test Scenario 1
UPDATE Customers SET Balance = 2000 WHERE CustomerID = 1;
SELECT CustomerID, Name, Balance, LastModified FROM Customers;

-- Scenario 2: LogTransaction - create AuditLog table first
CREATE TABLE AuditLog (
    AuditID       NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    TransactionID NUMBER,
    AccountID     NUMBER,
    Amount        NUMBER,
    TransactionType VARCHAR2(10),
    LogDate       DATE
);

CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog(TransactionID, AccountID, Amount, TransactionType, LogDate)
    VALUES (:NEW.TransactionID, :NEW.AccountID, :NEW.Amount, 
            :NEW.TransactionType, SYSDATE);
END;
/

-- Test Scenario 2
INSERT INTO Transactions VALUES (3, 1, SYSDATE, 500, 'Deposit');
SELECT * FROM AuditLog;

-- Scenario 3: CheckTransactionRules
CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    v_balance NUMBER;
BEGIN
    -- check deposit is positive
    IF :NEW.TransactionType = 'Deposit' AND :NEW.Amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Deposit amount must be positive');
    END IF;

    -- check withdrawal does not exceed balance
    IF :NEW.TransactionType = 'Withdrawal' THEN
        SELECT Balance INTO v_balance
        FROM Accounts
        WHERE AccountID = :NEW.AccountID;

        IF :NEW.Amount > v_balance THEN
            RAISE_APPLICATION_ERROR(-20002, 
                'Withdrawal amount exceeds balance');
        END IF;
    END IF;
END;
/