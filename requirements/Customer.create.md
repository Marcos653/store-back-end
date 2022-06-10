Caso de sucesso

[] - Validar se o cliente foi inserido corretamente.
[] - Verificar se já existe um cliente registrado com o CPF passado.
[] - Criar uma rota /POST para criar um novo cliente.
[] - Retorna 201 com os dados do cliente se tudo der certo.

Exceções

[] - Retorna 404 se a rota não for encontrada.
[] - Retorna 400 se o campo CPF não for inserido.
[] - Retorna 400 se o campo CPF for inválido.
[] - Retorna 500 se der erro ao tentar criar a conta do cliente.