# ufrj

Trabalho 2 de SO

b. Gerenciamento de Memória Virtual
-  Este exercício propõe a simulação de um gerenciamento de memória virtual.
- Simule a criação de um gerenciador de memória, através do seu algoritmo de
substituição de páginas LRU, onde o ambiente possui as seguintes características:
	- Cada thread de usuário possui um working set limit de até 4 (quatro) frames;
	- A memória é limitada em 64 frames dedicados para programas de usuário;
-  Mostre no relatório o conteúdo da console durante a execução.
c. Testes e execuções do programa
- Implementar o algoritmo de substituição de páginas LRU.
- Os testes devem ser realizados da seguinte forma:
	- Cada thread é criado a cada 3 segundos;
	- Cada thread criado solicita a alocação de uma página aleatória na memória a cada 3 segundos;
	- A cada solicitação de página o gerenciador da MV tem que apresentar a tabela de páginas virtuais do processo solicitante;
	- Testar para 20 threads com 50 páginas virtuais cada.
	- O processo pode ser “retirado” da memória (swap out) quando ele for o processo mais antigo.
- Não deixe de elaborar uma forma de monitoramento das ocorrências de criação dos threads, alocação de memória real de acordo com a solicitação das páginas, lista de substituição de páginas (LRU), swapping e execução dos diversos processos concorrentes, através de um esquema de visualização das informações em memória
