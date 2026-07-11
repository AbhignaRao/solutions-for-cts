-- Scenario 1: CalculateAge
CREATE OR REPLACE FUNCTION CalculateAge(
    p_dob DATE
) RETURN NUMBER AS
    v_age NUMBER;
BEGIN
    v_age := TRUNC(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
    RETURN v_age;
END;
/

-- Test Scenario 1
SELECT Name, DOB, CalculateAge(DOB) AS Age FROM Customers;

-- Scenario 2: CalculateMonthlyInstallment
CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment(
    p_loan_amount  NUMBER,
    p_interest_rate NUMBER,
    p_years        NUMBER
) RETURN NUMBER AS
    v_monthly_rate NUMBER;
    v_months       NUMBER;
    v_installment  NUMBER;
BEGIN
    v_monthly_rate := p_interest_rate / (12 * 100);
    v_months       := p_years * 12;

    -- EMI formula: P * r * (1+r)^n / ((1+r)^n - 1)
    v_installment  := p_loan_amount * v_monthly_rate *
                      POWER(1 + v_monthly_rate, v_months) /
                      (POWER(1 + v_monthly_rate, v_months) - 1);

    RETURN ROUND(v_installment, 2);
END;
/

-- Test Scenario 2
SELECT LoanID, LoanAmount, InterestRate,
       CalculateMonthlyInstallment(LoanAmount, InterestRate, 5) AS MonthlyInstallment
FROM Loans;

-- Scenario 3: HasSufficientBalance
CREATE OR REPLACE FUNCTION HasSufficientBalance(
    p_account_id NUMBER,
    p_amount     NUMBER
) RETURN VARCHAR2 AS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance
    FROM Accounts
    WHERE AccountID = p_account_id;

    IF v_balance >= p_amount THEN
        RETURN 'TRUE';
    ELSE
        RETURN 'FALSE';
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN 'FALSE';
END;
/