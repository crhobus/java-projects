struct params {
	string param1<10>;
	string param2<10>;
        string param3<20>;
        string param4<20>;
};

program SistemaBancarioRPC{
	version RPCInterface_v1 {
		string cadastrarCliente(string)=1;
                string getCliente(string)=2;
		string extrato(params)=3;
                string addHistoricoMovimentacao(string)=4;
	} = 1;
} = 2;