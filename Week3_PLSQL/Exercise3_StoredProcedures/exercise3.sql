-- Scenario 1: ProcessMonthlyInterest
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
BEGIN
    UPDATE Accounts
    SET Balance = Balance + (Balance * 0.01),
        LastModified = SYSDATE
    WHERE AccountType = 'Savings';

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Monthly interest applied to all savings accounts');
END;
/

-- Test Scenario 1
EXEC ProcessMonthlyInterest;

-- check updated balances
SELECT AccountID, AccountType, Balance FROM Accounts;

-- Scenario 2: UpdateEmployeeBonus
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    p_department   VARCHAR2,
    p_bonus_percent NUMBER
) AS
BEGIN
    UPDATE Employees
    SET Salary = Salary + (Salary * p_bonus_percent / 100)
    WHERE Department = p_department;

    IF SQL%ROWCOUNT = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No employees found in department: ' || p_department);
    ELSE
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Bonus applied to ' || SQL%ROWCOUNT ||
                             ' employees in ' || p_department);
    END IF;
END;
/

-- Test Scenario 2
EXEC UpdateEmployeeBonus('IT', 15);
EXEC UpdateEmployeeBonus('HR', 10);

-- check updated salaries
SELECT EmployeeID, Name, Department, Salary FROM Employees;

-- Scenario 3: TransferFunds
CREATE OR REPLACE PROCEDURE TransferFunds(
    p_from_account NUMBER,
    p_to_account   NUMBER,
    p_amount       NUMBER
) AS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance
    FROM Accounts
    WHERE AccountID = p_from_account;

    IF v_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient balance');
    END IF;

    UPDATE Accounts
    SET Balance = Balance - p_amount,
        LastModified = SYSDATE
    WHERE AccountID = p_from_account;

    UPDATE Accounts
    SET Balance = Balance + p_amount,
        LastModified = SYSDATE
    WHERE AccountID = p_to_account;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transferred $' || p_amount ||
                         ' from Account ' || p_from_account ||
                         ' to Account ' || p_to_account);
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Transfer failed: ' || SQLERRM);
END;
/