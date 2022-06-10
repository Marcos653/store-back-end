Caso de sucesso

[] - Validar se a venda foi inserido corretamente.
[] - Verificar se já há um vendedor e produto para aquele venda.
[] - Criar uma rota /POST para criar uma nova venda.
[] - Retorna 201 com os dados da venda se tudo der certo.

Exceções

[] - Retorna 404 se a rota não for encontrada.
[] - Retorna 400 se os campos vendedor e produto não for inserido.
[] - Retorna 400 se os campos vendedor e produto for inválido.
[] - Retorna 500 se der erro ao tentar criar uma venda.