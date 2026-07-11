-- Scenario 1: CustomerManagement Package
CREATE OR REPLACE PACKAGE CustomerManagement AS
    PROCEDURE AddCustomer(p_id NUMBER, p_name VARCHAR2, 
                          p_dob DATE, p_balance NUMBER);
    PROCEDURE UpdateCustomer(p_id NUMBER, p_name VARCHAR2, 
                             p_balance NUMBER);
    FUNCTION GetCustomerBalance(p_id NUMBER) RETURN NUMBER;
END CustomerManagement;
/

CREATE OR REPLACE PACKAGE BODY CustomerManagement AS
    PROCEDURE AddCustomer(p_id NUMBER, p_name VARCHAR2,
                          p_dob DATE, p_balance NUMBER) AS
    BEGIN
        INSERT INTO Customers(CustomerID, Name, DOB, Balance, LastModified)
        VALUES (p_id, p_name, p_dob, p_balance, SYSDATE);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Customer added: ' || p_name);
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('Error: Customer already exists');
    END AddCustomer;

    PROCEDURE UpdateCustomer(p_id NUMBER, p_name VARCHAR2,
                             p_balance NUMBER) AS
    BEGIN
        UPDATE Customers
        SET Name = p_name, Balance = p_balance, LastModified = SYSDATE
        WHERE CustomerID = p_id;
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Customer updated: ' || p_name);
    END UpdateCustomer;

    FUNCTION GetCustomerBalance(p_id NUMBER) RETURN NUMBER AS
        v_balance NUMBER;
    BEGIN
        SELECT Balance INTO v_balance
        FROM Customers WHERE CustomerID = p_id;
        RETURN v_balance;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN RETURN -1;
    END GetCustomerBalance;
END CustomerManagement;
/

-- Scenario 2: EmployeeManagement Package
CREATE OR REPLACE PACKAGE EmployeeManagement AS
    PROCEDURE HireEmployee(p_id NUMBER, p_name VARCHAR2, p_position VARCHAR2,
                           p_salary NUMBER, p_dept VARCHAR2, p_hiredate DATE);
    PROCEDURE UpdateEmployee(p_id NUMBER, p_position VARCHAR2, p_salary NUMBER);
    FUNCTION GetAnnualSalary(p_id NUMBER) RETURN NUMBER;
END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS
    PROCEDURE HireEmployee(p_id NUMBER, p_name VARCHAR2, p_position VARCHAR2,
                           p_salary NUMBER, p_dept VARCHAR2, p_hiredate DATE) AS
    BEGIN
        INSERT INTO Employees VALUES (p_id, p_name, p_position, 
                                      p_salary, p_dept, p_hiredate);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Employee hired: ' || p_name);
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('Error: Employee already exists');
    END HireEmployee;

    PROCEDURE UpdateEmployee(p_id NUMBER, p_position VARCHAR2, 
                             p_salary NUMBER) AS
    BEGIN
        UPDATE Employees
        SET Position = p_position, Salary = p_salary
        WHERE EmployeeID = p_id;
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Employee updated ID: ' || p_id);
    END UpdateEmployee;

    FUNCTION GetAnnualSalary(p_id NUMBER) RETURN NUMBER AS
        v_salary NUMBER;
    BEGIN
        SELECT Salary * 12 INTO v_salary
        FROM Employees WHERE EmployeeID = p_id;
        RETURN v_salary;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN RETURN -1;
    END GetAnnualSalary;
END EmployeeManagement;
/

-- Scenario 3: AccountOperations Package
CREATE OR REPLACE PACKAGE AccountOperations AS
    PROCEDURE OpenAccount(p_account_id NUMBER, p_customer_id NUMBER,
                          p_type VARCHAR2, p_balance NUMBER);
    PROCEDURE CloseAccount(p_account_id NUMBER);
    FUNCTION GetTotalBalance(p_customer_id NUMBER) RETURN NUMBER;
END AccountOperations;
/

CREATE OR REPLACE PACKAGE BODY AccountOperations AS
    PROCEDURE OpenAccount(p_account_id NUMBER, p_customer_id NUMBER,
                          p_type VARCHAR2, p_balance NUMBER) AS
    BEGIN
        INSERT INTO Accounts(AccountID, CustomerID, AccountType, 
                             Balance, LastModified)
        VALUES (p_account_id, p_customer_id, p_type, p_balance, SYSDATE);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Account opened: ' || p_account_id);
    END OpenAccount;

    PROCEDURE CloseAccount(p_account_id NUMBER) AS
    BEGIN
        DELETE FROM Accounts WHERE AccountID = p_account_id;
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Account closed: ' || p_account_id);
    END CloseAccount;

    FUNCTION GetTotalBalance(p_customer_id NUMBER) RETURN NUMBER AS
        v_total NUMBER;
    BEGIN
        SELECT SUM(Balance) INTO v_total
        FROM Accounts WHERE CustomerID = p_customer_id;
        RETURN NVL(v_total, 0);
    END GetTotalBalance;
END AccountOperations;
/