drop table limite_conta;
drop table mc_internet_banking;

create table mc_internet_banking(
  cd_banco  number(20)    primary key,
  nm_banco  varchar2(60)  not null,
  cnpj      varchar2(19)  not null,
  saldo     number(15,2)  not null
);

create table limite_conta(
  cd_limite_conta number(20)    primary key,
  cd_banco        number(20)    not null,
  nr_conta        number(25)    not null,
  saldo           number(15,2)  not null,
  limite          number(15,2)  not null,
  constraint cd_banco_fk  foreign key(cd_banco) references mc_internet_banking(cd_banco)
);

insert into mc_internet_banking
                              (cd_banco,
                               nm_banco,
                               cnpj,
                               saldo)
values                        (1,
                              'MC Internet Banking',
                              '123.987.564/9886-88',
                              1200);
                              
insert into limite_conta(
                      cd_limite_conta,
                      cd_banco,
                      nr_conta,
                      saldo,
                      limite)  
values                (
                      1,
                      1,
                      1234567832,
                      500,
                      500
                      );
                                                          
create or replace
procedure efetuar_emprestimo(
                            nr_conta_p  in  number,
                            valor_p     in  number,
                            retorno_p   out number) is
retorno_v       number(20,2);
limite_v        number(20,2);
saldo_v         number(20,2);
saldo_banco_v   number(20,2);
cd_banco_v      number(20);
begin

select  max(limite)
into    limite_v
from    limite_conta
where   nr_conta = nr_conta_p;

select  max(saldo)
into    saldo_v
from    limite_conta
where   nr_conta = nr_conta_p;

select  max(ib.saldo)
into    saldo_banco_v
from    mc_internet_banking ib,
        limite_conta lc
where   lc.nr_conta = nr_conta_p
and     lc.cd_banco = ib.cd_banco;

select  max(cd_banco)
into    cd_banco_v
from    limite_conta
where   nr_conta = nr_conta_p;

if (valor_p > limite_v) then
  retorno_v := -1;
else if (valor_p > saldo_banco_v) then
  retorno_v := -2;
else
  saldo_banco_v := saldo_banco_v - valor_p;
  saldo_v := saldo_v - valor_p;
  
  update limite_conta
  set    saldo    = saldo_v
  where  nr_conta = nr_conta_p;
  
  update  mc_internet_banking
  set     saldo    = saldo_banco_v
  where   cd_banco = cd_banco_v;
  
  retorno_v := valor_p;
  
  end if;
  
end if;

commit;

retorno_p := retorno_v;

end efetuar_emprestimo;