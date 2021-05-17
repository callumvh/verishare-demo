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
begin
    repoRate := 9.75;
    averageMonth := 30.43;
    averageYear := 365.25;

    years := DATE_PART('year', AGE(endDate, startDate));

    months := DATE_PART('month', AGE(endDate, startDate));

    days := DATE_PART('day', AGE(endDate, startDate));
--     based on average number of days in year and month respectively
    daysBetween := (years * 365.25) + (months * 30.43) + (days);

--     return daysBetween;
    if agreementType = 'mortgageAgreements' then
        return initialAmount * ((repoRate * 2.2) + 0.05) / 100 * daysBetween / averageYear;
--        return daysBetween;
    elsif agreementType = 'creditFacilities' then
        return initialAmount * ((repoRate * 2.2) + 0.1) / 100 * daysBetween / averageYear;

    elsif agreementType = 'unsecuredCredit' then
        return initialAmount * ((repoRate * 2.2) + 0.2) / 100 * daysBetween / averageYear;
    elsif agreementType = 'devCreditAgreementSmallBsnss' then
        return initialAmount * ((repoRate * 2.2) + 0.2) / 100 * daysBetween / averageYear;
    elsif agreementType = 'devCreditAgreementLowIncome' then
        return initialAmount * ((repoRate * 2.2) + 0.2) / 100 * daysBetween / averageYear;
    elsif agreementType = 'shortTermCredit' then
        return initialAmount * 0.05 * daysBetween / averageMonth;
--        return daysBetween;
    elsif agreementType = 'otherCreditAgreements' then
        return initialAmount * ((repoRate * 2.2) + 0.1) / 100 * daysBetween / averageYear;
    elsif agreementType = 'incidentalCredit' then
        return initialAmount * 0.02 * daysBetween / averageMonth;
    end if;

end
$$;

select calculatedInterest(4586, 'shortTermCredit', '2014-03-19', '2019-08-04');
