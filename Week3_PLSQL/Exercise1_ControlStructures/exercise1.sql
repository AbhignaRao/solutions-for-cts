-- Scenario 1: Apply 1% discount to loan interest for customers above 60
BEGIN
    FOR rec IN (SELECT c.CustomerID, c.Name, c.DOB, l.LoanID, l.InterestRate
                FROM Customers c
                JOIN Loans l ON c.CustomerID = l.CustomerID)
    LOOP
        IF TRUNC(MONTHS_BETWEEN(SYSDATE, rec.DOB) / 12) > 60 THEN
            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE LoanID = rec.LoanID;
            DBMS_OUTPUT.PUT_LINE('Discount applied for: ' || rec.Name ||
                                 ' New Rate: ' || (rec.InterestRate - 1));
        END IF;
    END LOOP;
    COMMIT;
END;
/

-- Scenario 2: Set IsVIP flag for customers with balance over 10000
-- First add IsVIP column
ALTER TABLE Customers ADD IsVIP VARCHAR2(5) DEFAULT 'FALSE';

BEGIN
    FOR rec IN (SELECT CustomerID, Name, Balance FROM Customers)
    LOOP
        IF rec.Balance > 10000 THEN
            UPDATE Customers
            SET IsVIP = 'TRUE'
            WHERE CustomerID = rec.CustomerID;
            DBMS_OUTPUT.PUT_LINE(rec.Name || ' is now VIP');
        ELSE
            DBMS_OUTPUT.PUT_LINE(rec.Name || ' is not VIP');
        END IF;
    END LOOP;
    COMMIT;
END;
/

-- Scenario 3: Send reminders for loans due in next 30 days
BEGIN
    FOR rec IN (SELECT c.Name, l.LoanID, l.EndDate
                FROM Loans l
                JOIN Customers c ON l.CustomerID = c.CustomerID
                WHERE l.EndDate <= SYSDATE + 30)
    LOOP
        DBMS_OUTPUT.PUT_LINE('REMINDER: Dear ' || rec.Name ||
                             ', your Loan ID ' || rec.LoanID ||
                             ' is due on ' || TO_CHAR(rec.EndDate, 'YYYY-MM-DD'));
    END LOOP;
END;
/