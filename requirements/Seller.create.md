Caso de sucesso

[] - Validar se o vendedor foi inserido corretamente.
[] - Verificar se o CNPJ do vendedor é valido.
[] - Criar uma rota /POST para criar um novo vendedor.
[] - Retorna 201 com os dados do vendedor se tudo der certo.

Exceções

[] - Retorna 404 se a rota não for encontrada.
[] - Retorna 400 se o campo CNPJ não for inserido.
[] - Retorna 400 se o campo CNPJ for inválido.
[] - Retorna 500 se der erro ao tentar criar um vendedor.