function qtdePizzas(valor){
	if (valor == "")
	{
		document.getElementById("itemPedido1").style.display = "none";
		document.getElementById("itemPedido2").style.display = "none";
		document.getElementById("itemPedido3").style.display = "none";
		document.getElementById("itemPedido4").style.display = "none";
		document.getElementById("itemPedido5").style.display = "none";
	}
	
	else if (valor == "1")
	{
		document.getElementById("itemPedido1").style.display = "block";
		document.getElementById("itemPedido2").style.display = "none";
		document.getElementById("itemPedido3").style.display = "none";
		document.getElementById("itemPedido4").style.display = "none";
		document.getElementById("itemPedido5").style.display = "none";
	}
	
	else if (valor == "2")
	{
		document.getElementById("itemPedido1").style.display = "block";
		document.getElementById("itemPedido2").style.display = "block";
		document.getElementById("itemPedido3").style.display = "none";
		document.getElementById("itemPedido4").style.display = "none";
		document.getElementById("itemPedido5").style.display = "none";
	}
	
	else if (valor == "3")
	{
		document.getElementById("itemPedido1").style.display = "block";
		document.getElementById("itemPedido2").style.display = "block";
		document.getElementById("itemPedido3").style.display = "block";
		document.getElementById("itemPedido4").style.display = "none";
		document.getElementById("itemPedido5").style.display = "none";
	}
	
	else if (valor == "4")
	{
		document.getElementById("itemPedido1").style.display = "block";
		document.getElementById("itemPedido2").style.display = "block";
		document.getElementById("itemPedido3").style.display = "block";
		document.getElementById("itemPedido4").style.display = "block";
		document.getElementById("itemPedido5").style.display = "none";
	}
	
	else if (valor == "5")
	{
		document.getElementById("itemPedido1").style.display = "block";
		document.getElementById("itemPedido2").style.display = "block";
		document.getElementById("itemPedido3").style.display = "block";
		document.getElementById("itemPedido4").style.display = "block";
		document.getElementById("itemPedido5").style.display = "block";
	}
 
}

function qtdeSabores(valor){
    
        if (valor == "pequena1")
	{
		document.getElementById("sabor1_1").style.display = "block";
		document.getElementById("sabor2_1").style.display = "none";
		document.getElementById("sabor3_1").style.display = "none";
		document.getElementById("sabor4_1").style.display = "none";
	}
	
	else if (valor == "media1")
	{
		document.getElementById("sabor1_1").style.display = "block";
		document.getElementById("sabor2_1").style.display = "block";
		document.getElementById("sabor3_1").style.display = "none";
		document.getElementById("sabor4_1").style.display = "none";
	}
	
        else if (valor == "grande1")
	{
		document.getElementById("sabor1_1").style.display = "block";
		document.getElementById("sabor2_1").style.display = "block";
		document.getElementById("sabor3_1").style.display = "block";
		document.getElementById("sabor4_1").style.display = "block";
	}
        
        else if (valor == "gigante1")
	{
		document.getElementById("sabor1_1").style.display = "block";
		document.getElementById("sabor2_1").style.display = "block";
		document.getElementById("sabor3_1").style.display = "block";
		document.getElementById("sabor4_1").style.display = "block";
	}
        
        if (valor == "pequena2")
	{
		document.getElementById("sabor1_2").style.display = "block";
		document.getElementById("sabor2_2").style.display = "none";
		document.getElementById("sabor3_2").style.display = "none";
		document.getElementById("sabor4_2").style.display = "none";
	}
	
	else if (valor == "media2")
	{
		document.getElementById("sabor1_2").style.display = "block";
		document.getElementById("sabor2_2").style.display = "block";
		document.getElementById("sabor3_2").style.display = "none";
		document.getElementById("sabor4_2").style.display = "none";
	}
	
        else if (valor == "grande2")
	{
		document.getElementById("sabor1_2").style.display = "block";
		document.getElementById("sabor2_2").style.display = "block";
		document.getElementById("sabor3_2").style.display = "block";
		document.getElementById("sabor4_2").style.display = "block";
	}
        
        else if (valor == "gigante2")
	{
		document.getElementById("sabor1_2").style.display = "block";
		document.getElementById("sabor2_2").style.display = "block";
		document.getElementById("sabor3_2").style.display = "block";
		document.getElementById("sabor4_2").style.display = "block";
	}
        
        if (valor == "pequena3")
	{
		document.getElementById("sabor1_3").style.display = "block";
		document.getElementById("sabor2_3").style.display = "none";
		document.getElementById("sabor3_3").style.display = "none";
		document.getElementById("sabor4_3").style.display = "none";
	}
	
	else if (valor == "media3")
	{
		document.getElementById("sabor1_3").style.display = "block";
		document.getElementById("sabor2_3").style.display = "block";
		document.getElementById("sabor3_3").style.display = "none";
		document.getElementById("sabor4_3").style.display = "none";
	}
	
        else if (valor == "grande3")
	{
		document.getElementById("sabor1_3").style.display = "block";
		document.getElementById("sabor2_3").style.display = "block";
		document.getElementById("sabor3_3").style.display = "block";
		document.getElementById("sabor4_3").style.display = "block";
	}
        
        else if (valor == "gigante3")
	{
		document.getElementById("sabor1_3").style.display = "block";
		document.getElementById("sabor2_3").style.display = "block";
		document.getElementById("sabor3_3").style.display = "block";
		document.getElementById("sabor4_3").style.display = "block";
	}
        
        if (valor == "pequena4")
	{
		document.getElementById("sabor1_4").style.display = "block";
		document.getElementById("sabor2_4").style.display = "none";
		document.getElementById("sabor3_4").style.display = "none";
		document.getElementById("sabor4_4").style.display = "none";
	}
	
	else if (valor == "media4")
	{
		document.getElementById("sabor1_4").style.display = "block";
		document.getElementById("sabor2_4").style.display = "block";
		document.getElementById("sabor3_4").style.display = "none";
		document.getElementById("sabor4_4").style.display = "none";
	}
	
        else if (valor == "grande4")
	{
		document.getElementById("sabor1_4").style.display = "block";
		document.getElementById("sabor2_4").style.display = "block";
		document.getElementById("sabor3_4").style.display = "block";
		document.getElementById("sabor4_4").style.display = "block";
	}
        
        else if (valor == "gigante4")
	{
		document.getElementById("sabor1_4").style.display = "block";
		document.getElementById("sabor2_4").style.display = "block";
		document.getElementById("sabor3_4").style.display = "block";
		document.getElementById("sabor4_4").style.display = "block";
	}
        
        
	if (valor == "pequena5")
	{
		document.getElementById("sabor1_5").style.display = "block";
		document.getElementById("sabor2_5").style.display = "none";
		document.getElementById("sabor3_5").style.display = "none";
		document.getElementById("sabor4_5").style.display = "none";
	}
	
	else if (valor == "media5")
	{
		document.getElementById("sabor1_5").style.display = "block";
		document.getElementById("sabor2_5").style.display = "block";
		document.getElementById("sabor3_5").style.display = "none";
		document.getElementById("sabor4_5").style.display = "none";
	}
	
        else if (valor == "grande5")
	{
		document.getElementById("sabor1_5").style.display = "block";
		document.getElementById("sabor2_5").style.display = "block";
		document.getElementById("sabor3_5").style.display = "block";
		document.getElementById("sabor4_5").style.display = "block";
	}
        
        else if (valor == "gigante5")
	{
		document.getElementById("sabor1_5").style.display = "block";
		document.getElementById("sabor2_5").style.display = "block";
		document.getElementById("sabor3_5").style.display = "block";
		document.getElementById("sabor4_5").style.display = "block";
	}
	
}