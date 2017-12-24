drop table movimentacao;
drop table cliente;

create table cliente(
  cd_cliente  number(20) primary key,
  nm_cliente  varchar2(60)  not null,
  cpf         varchar2(14)  not null,
  rg          varchar2(11)  not null,
  endereco    varchar2(80)  not null,
  bairro      varchar2(40)  not null,
  cep         varchar2(9)   not null,
  cidade      varchar2(60)  not null,
  estado      varchar2(50)  not null,
  senha       varchar2(15)  not null,
  sexo        varchar2(1)   not null,
  numero      number(10)    not null,
  nr_conta    number(25)    not null,
  dt_nasc     date          not null,
  saldo       number(15,2)  not null
);

create table movimentacao(
  cd_movimentacao number(20)    primary key,
  cd_cliente      number(20)    not null,
  valor           number(10,2)  not null,
  tp_operacao     varchar2(35)  not null,
  dt_operacao     date          not null,
  constraint cd_cliente_fk  foreign key(cd_cliente) references cliente(cd_cliente)
);

create or replace
procedure atualizar_historico_cliente(
                                      cd_cliente_p  in number,
                                      valor_p       in number,
                                      tp_operacao_p in varchar2) is
cd_movimentacao_v number(20);
saldo_v           number(20,2);
begin

  select  max(saldo)
  into    saldo_v
  from    cliente
  where   cd_cliente  = cd_cliente_p; 
  
  if (tp_operacao_p = 'Saque') then 
    saldo_v := saldo_v - valor_p;
  else
    saldo_v := saldo_v + valor_p;
  end if;
  
  update  cliente
  set     saldo = saldo_v
  where   cd_cliente  = cd_cliente_p;
  
  select  max(cd_movimentacao + 1)
  into    cd_movimentacao_v
  from    movimentacao;
  if (cd_movimentacao_v is null) then
    cd_movimentacao_v:= 1;
  end if;
  
  insert into movimentacao (
                            cd_movimentacao,
                            cd_cliente,
                            valor,
                            tp_operacao,
                            dt_operacao)
  values (
                            cd_movimentacao_v,
                            cd_cliente_p,
                            valor_p,
                            tp_operacao_p,
                            sysdate);
  commit;
  
end atualizar_historico_cliente;