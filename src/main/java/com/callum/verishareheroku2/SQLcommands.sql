-- This is the stored procedure, it is technically a function in PostgreSQL because PostgreSQL does not support procedures with return values, although it is effectively the same.TEMPORARY
-- Please excuse all of the "double precision" type casts, I put them there while trying to fix issues I was having with the formulas.
-- Please also excuse all of the elsif comparisons, I know that a case statement would be much better but I was having trouble with getting it to work.
create or replace function calculatedInterest(
    initialAmount double precision, agreementType VARCHAR, startDate date, endDate date
)
    returns double precision
    language plpgsql
as
$$
declare
    repoRate     double precision;
    daysBetween  double precision;
    days         double precision;
    months       double precision;
    years        double precision;
    averageYear  double precision;
    averageMonth double precision;
    multiplier   double precision;
begin
    multiplier = 2.2;
    repoRate := 9.75;
    averageMonth := 30.43;
    averageYear := 365.25;

    years := DATE_PART('year', AGE(endDate, startDate));

    months := DATE_PART('month', AGE(endDate, startDate));

    days := DATE_PART('day', AGE(endDate, startDate));
--  Based on average number of days in year and month respectively
    daysBetween := (years * 365.25) + (months * 30.43) + (days);

    if agreementType = 'mortgageAgreements' then
        return initialAmount * ((repoRate * multiplier) + 0.05::double precision) / 100::double precision *
               daysBetween / averageYear;
    elsif agreementType = 'creditFacilities' then
        return initialAmount * ((repoRate * multiplier) + 0.1::double precision) / 100::double precision * daysBetween /
               averageYear;
    elsif agreementType = 'unsecuredCredit' then
        return initialAmount * ((repoRate * multiplier) + 0.2::double precision) / 100::double precision * daysBetween /
               averageYear;
    elsif agreementType = 'devCreditAgreementSmallBsnss' then
        return initialAmount * ((repoRate * multiplier) + 0.2::double precision) / 100::double precision * daysBetween /
               averageYear;
    elsif agreementType = 'devCreditAgreementLowIncome' then
        return initialAmount * ((repoRate * multiplier) + 0.2::double precision) / 100::double precision * daysBetween /
               averageYear;
    elsif agreementType = 'shortTermCredit' then
        return initialAmount * 0.05::double precision * daysBetween / averageMonth;
    elsif agreementType = 'otherCreditAgreements' then
        return initialAmount * ((repoRate * multiplier) + 0.1::double precision) / 100::double precision * daysBetween /
               averageYear;
    elsif agreementType = 'incidentalCredit' then
        return initialAmount * 0.02::double precision * daysBetween / averageMonth;
    end if;
end
$$;
