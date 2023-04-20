create function public.address_by_surname(surname_parameter character varying)
    returns TABLE(card_number bigint, name character varying, surname character varying)
    language plpgsql
as
$$
begin
    RETURN QUERY
        select patients.card_number, patients.name, patients.surname from patients  where patients.surname = surname_parameter;
end;
$$;

alter function public.address_by_surname(varchar) owner to postgres;

