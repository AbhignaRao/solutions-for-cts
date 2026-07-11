-- Scenario 1: GenerateMonthlyStatements
DECLARE
    CURSOR GenerateMonthlyStatements IS
        SELECT c.Name, t.TransactionID, t.Amount, 
               t.TransactionType, t.TransactionDate
        FROM Transactions t
        JOIN Accounts a ON t.AccountID = a.AccountID
        JOIN Customers c ON a.CustomerID = c.CustomerID
        WHERE EXTRACT(MONTH FROM t.TransactionDate) = EXTRACT(MONTH FROM SYSDATE)
        AND EXTRACT(YEAR FROM t.TransactionDate) = EXTRACT(YEAR FROM SYSDATE);
    
    rec GenerateMonthlyStatements%ROWTYPE;
BEGIN
    OPEN GenerateMonthlyStatements;
    DBMS_OUTPUT.PUT_LINE('=== Monthly Statement ===');
    LOOP
        FETCH GenerateMonthlyStatements INTO rec;
        EXIT WHEN GenerateMonthlyStatements%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('Customer: ' || rec.Name ||
                             ' | TxnID: ' || rec.TransactionID ||
                             ' | Type: ' || rec.TransactionType ||
                             ' | Amount: $' || rec.Amount);
    END LOOP;
    CLOSE GenerateMonthlyStatements;
END;
/

-- Scenario 2: ApplyAnnualFee
DECLARE
    CURSOR ApplyAnnualFee IS
        SELECT AccountID, Balance FROM Accounts;
    
    v_account_id Accounts.AccountID%TYPE;
    v_balance    Accounts.Balance%TYPE;
    v_fee        NUMBER := 50;
BEGIN
    OPEN ApplyAnnualFee;
    DBMS_OUTPUT.PUT_LINE('=== Applying Annual Fee ===');
    LOOP
        FETCH ApplyAnnualFee INTO v_account_id, v_balance;
        EXIT WHEN ApplyAnnualFee%NOTFOUND;
        
        UPDATE Accounts
        SET Balance = Balance - v_fee
        WHERE AccountID = v_account_id;
        
        DBMS_OUTPUT.PUT_LINE('Account ' || v_account_id ||
                             ' | Old Balance: $' || v_balance ||
                             ' | New Balance: $' || (v_balance - v_fee));
    END LOOP;
    CLOSE ApplyAnnualFee;
    COMMIT;
END;
/

-- Scenario 3: UpdateLoanInterestRates
DECLARE
    CURSOR UpdateLoanInterestRates IS
        SELECT LoanID, InterestRate FROM Loans;
    
    v_loan_id      Loans.LoanID%TYPE;
    v_interest_rate Loans.InterestRate%TYPE;
    v_new_rate     NUMBER;
BEGIN
    OPEN UpdateLoanInterestRates;
    DBMS_OUTPUT.PUT_LINE('=== Updating Loan Interest Rates ===');
    LOOP
        FETCH UpdateLoanInterestRates INTO v_loan_id, v_interest_rate;
        EXIT WHEN UpdateLoanInterestRates%NOTFOUND;
        
        -- new policy: reduce rate by 0.5%
        v_new_rate := v_interest_rate - 0.5;
        
        UPDATE Loans
        SET InterestRate = v_new_rate
        WHERE LoanID = v_loan_id;
        
        DBMS_OUTPUT.PUT_LINE('Loan ' || v_loan_id ||
                             ' | Old Rate: ' || v_interest_rate ||
                             '% | New Rate: ' || v_new_rate || '%');
    END LOOP;
    CLOSE UpdateLoanInterestRates;
    COMMIT;
END;
/