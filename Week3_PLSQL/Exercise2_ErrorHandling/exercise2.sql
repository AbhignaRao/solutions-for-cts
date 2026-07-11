-- Scenario 1: SafeTransferFunds
CREATE OR REPLACE PROCEDURE SafeTransferFunds(
    p_from_account NUMBER,
    p_to_account   NUMBER,
    p_amount       NUMBER
) AS
    v_balance NUMBER;
BEGIN
    -- check balance
    SELECT Balance INTO v_balance
    FROM Accounts
    WHERE AccountID = p_from_account;

    IF v_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds');
    END IF;

    -- deduct from source
    UPDATE Accounts
    SET Balance = Balance - p_amount
    WHERE AccountID = p_from_account;

    -- add to destination
    UPDATE Accounts
    SET Balance = Balance + p_amount
    WHERE AccountID = p_to_account;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transfer successful: $' || p_amount);

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/

-- Test Scenario 1
EXEC SafeTransferFunds(1, 2, 500);  -- should fail insufficient funds
EXEC SafeTransferFunds(2, 1, 500);  -- should succeed

-- Scenario 2: UpdateSalary
CREATE OR REPLACE PROCEDURE UpdateSalary(
    p_employee_id NUMBER,
    p_percentage  NUMBER
) AS
    v_count NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_count
    FROM Employees
    WHERE EmployeeID = p_employee_id;

    IF v_count = 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Employee not found: ' || p_employee_id);
    END IF;

    UPDATE Employees
    SET Salary = Salary + (Salary * p_percentage / 100)
    WHERE EmployeeID = p_employee_id;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Salary updated for Employee ID: ' || p_employee_id);

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/

-- Test Scenario 2
EXEC UpdateSalary(1, 10);   -- should succeed
EXEC UpdateSalary(99, 10);  -- should fail employee not found

-- Scenario 3: AddNewCustomer
CREATE OR REPLACE PROCEDURE AddNewCustomer(
    p_customer_id NUMBER,
    p_name        VARCHAR2,
    p_dob         DATE,
    p_balance     NUMBER
) AS
BEGIN
    INSERT INTO Customers(CustomerID, Name, DOB, Balance, LastModified)
    VALUES (p_customer_id, p_name, p_dob, p_balance, SYSDATE);

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Customer added: ' || p_name);

EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        DBMS_OUTPUT.PUT_LINE('Error: Customer ID ' || p_customer_id || ' already exists');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/

-- Test Scenario 3
EXEC AddNewCustomer(4, 'Guna', TO_DATE('2000-01-01','YYYY-MM-DD'), 5000); -- success
EXEC AddNewCustomer(1, 'Duplicate', TO_DATE('2000-01-01','YYYY-MM-DD'), 100); -- fail duplicate